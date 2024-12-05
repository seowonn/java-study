package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class ChatClient {

	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {

		Scanner scanner = null;
		Socket socket = null;

		try {
			// 1. 키보드 연결
			scanner = new Scanner(System.in);

			// 2. socket 생성
			socket = new Socket();

			// 3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));

			// 4. reader/writer 생성
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 5. join 프로토콜
			System.out.print("닉네임>> ");
			String nickName = scanner.nextLine();
			pw.println("join:" + nickName);
			pw.flush();

			// 6. ChatClientReceiveThread 시작
			ChatClientThread chatClientThread = new ChatClientThread(socket, br);
			chatClientThread.start();

			// 7. 키보드 입력 처리
			while (true) {
				String input = scanner.nextLine();

				if ("quit".equals(input)) {
					// 8. quit 프로토콜 처리
					pw.println("quit:");
					pw.flush();
					
					try {
						chatClientThread.join();
					} catch (InterruptedException e) {
						log("error:" + e);
					}
					break;
				} else {
					// 9. 메시지 처리
					String endcodedInput = Base64.getEncoder().encodeToString(input.getBytes("UTF-8"));
					pw.println("message:" + endcodedInput);
					pw.flush();
				}
			}

		} catch (IOException e) {
			log("error:" + e);
		} finally {
			// 10. 자원 정리
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				log("error:" + e);
			}
		}
	}

	public static void log(String message) {
		System.out.println("[ChatClient]: " + message);
	}

}
