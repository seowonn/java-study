package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPServer {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소캣 객체 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩 (binding)
			// IPAddress: 0.0.0.0: 으로 설정함으로써 특정 호스트에만 국한하여 IP를 바인딩하지 않는다. 
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));
			Socket socket = serverSocket.accept();
			
			try {
				// 원래 SocketAddress 받는데 down casting함
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();
				
				System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");
				
				// 3. accept
				// blocking 단계 - 클라이언트로부터 소캣 연결이 올 때까지 대기(accept)해서 
				System.out.println("연결 성공");
				
				// 4. IO Stream 받아오기 
				InputStream is = socket.getInputStream(); // InputStream() : 클라이언트가 서버로 보내는 스트림
				OutputStream os = socket.getOutputStream(); // OutputStream : 서버가 클라이언트로 보내는 스트림 

				while(true) {					
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking 
					if (readByteCount == -1) {
						// close by client
						System.out.println("[server] closed by client");
						break;
					}
					
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received: " + data);

					// 6. 데이터 쓰기 - 클라이언트로 데이터 전송
					os.write(data.getBytes("utf-8")); // write는 blocking이 아님 
				}

				
			} catch (IOException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if(socket != null && socket.isClosed()) {						
						socket.close();
					}
				} catch (IOException e) {
					System.out.println("error:" + e);
				}
			}
			
		} catch (IOException e) {
			System.out.println("[server] error:" + e);
		} finally {
			try {
				if(serverSocket != null && serverSocket.isClosed()) {					
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
