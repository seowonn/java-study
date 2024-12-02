package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class TCPClient {

	public static void main(String[] args) {

		Socket socket = null;

		try {
			// 1. 소캣 생성
			socket = new Socket();

			// 1-1. 소캣의 버퍼 사이즈 확인
			int rcvBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			System.out.println(rcvBufferSize + " : " + sendBufferSize);
			
			// 1-2. 소켓버퍼 사이즈 변경
			socket.setReceiveBufferSize(1024 * 10);
			socket.setSendBufferSize(1024 * 10);
			
			rcvBufferSize = socket.getReceiveBufferSize();
			sendBufferSize = socket.getSendBufferSize();
			System.out.println(rcvBufferSize + " : " + sendBufferSize);
			
			// 1-3. SO_NODELAY(Nagle Algorithm OFF)
			socket.setTcpNoDelay(true); 	// ack 수신 안 받고 응답을 보내는 경우
			
			// 1-4. SO_TIMEOUT
			socket.setSoTimeout(3);
			
			// 2. 서버 연결
			socket.connect(new InetSocketAddress("192.168.0.10", 5000));

			// 3. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 4. 쓰기
			String data = "Hello World";
			os.write(data.getBytes("utf-8"));

			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);	//     갑자기 끊어진 경우 발생 가능 
			if (readByteCount == -1) {
				System.out.println("[client] closed by server");
				return;
			}

			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received: " + data);

		} catch (SocketTimeoutException e) {
			System.out.println("[client] SocketTimeout Exception: " + e);
		} catch (SocketException e) {
			System.out.println("[client] Socket Exception: " + e);
		} catch (IOException e) {
			System.out.println("[client] error:" + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
