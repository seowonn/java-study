package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	public static final int PORT = 8080;
	public static final String SERVER_IP = "127.0.0.1";
	public static final String DEFAULT_HOST = "0.0.0.0";

	private static List<Writer> listWriters = new ArrayList<>();
	private static ServerSocket serverSocket;

	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket();
//			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(DEFAULT_HOST, PORT));
//			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			log("연결 기다림 " + DEFAULT_HOST + ":" + PORT);

			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			closeServerResource();
		}

	}

	private static void closeServerResource() {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				log("서버 소켓 닫는 중 에러 발생: " + e);
			}
		}
	}

	public static void log(String message) {
		System.out.println("[ChatServer]: " + message);
	}

}
