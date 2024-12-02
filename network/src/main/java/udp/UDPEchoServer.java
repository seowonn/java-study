package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEchoServer {
	// UDP는 thread 모델이 필요없다. 
	// 이유 : client와 server 간에 연결이 없기 때문?이다. 

	public static final int PORT = 50000;
	public static final int BUFFER_SIZE = 256;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			// 1. 소켓 생성
			socket = new DatagramSocket(PORT);
			
			while(true) {
				// 2. 데이터 수신
				// 통신에서는 blocking 때문에 whlie문 쓰는 데에 부담이 적다. 
				// BUFFER_SIZE는 server와 client 사이에 약속을 미리 해놓아야 한다. 
				DatagramPacket rcvPacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(rcvPacket);
				
				byte[] rcvData = rcvPacket.getData();
				int offset = rcvPacket.getLength(); // 버퍼 기준 차 있는 데이터 양 
				
				String message = new String(rcvData, 0, offset, "UTF-8");
				System.out.println("[UDP Echo Server] received: " + message);
				
				// 3. 데이터 송신
				byte[] sndData = message.getBytes("UTF-8");
				DatagramPacket sndPacket = new DatagramPacket(
						sndData,
						sndData.length,
						rcvPacket.getAddress(),
						rcvPacket.getPort());
				
				socket.send(sndPacket);
			}
			
		} catch (SocketException e) {
			System.out.println("[UDP Echo Server] error: " + e);
		} catch (IOException e) {
			System.out.println("[UDP Echo Server] error: " + e);
		} finally {
			if(socket != null && !socket.isClosed()) {				
				socket.close();
			}
		}
		
	}
}
