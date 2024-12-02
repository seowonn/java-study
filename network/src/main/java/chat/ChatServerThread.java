package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class ChatServerThread extends Thread {

	private String nickName;
	private Socket socket;
	private List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		// 1. Remote Host Information

		BufferedReader bufferedReader = null;
		Writer printWriter = null;

		try {
			// 2. 스트림 얻기
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
					true);

			// 3. 요청 처리
			while (true) {
				String request = bufferedReader.readLine();
				if (request == null) {
					ChatServer.log("클라이언트로부터 연결 끊김");
					doQuit(printWriter);
					break;
				}

				// 4. 프로토콜 분석
				String[] tokens = request.split(":");
				if(tokens.length == 0) continue;
				
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], printWriter);
				} else if ("message".equals(tokens[0])) {
					String decodeString = new String(Base64.getDecoder().decode(tokens[1]), StandardCharsets.UTF_8);
					System.out.println("이거 서버가 받은 request: " + decodeString);
					doMessage(decodeString);
				} else if ("quit".equals(tokens[0])) {
					doQuit(printWriter);
					((PrintWriter) printWriter).println("quit:ok");
					printWriter.flush();
					break;
				} else {
					ChatServer.log("error: 알 수 없는 요청 (" + tokens[0] + ")");
				}
			}

		} catch (IOException e) {
			ChatServer.log("error: " + e);
		}
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);
		String data = nickName + "님이 퇴장 하였습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		synchronized (writer) {			
			listWriters.remove(writer);
		}
	}

	private void doMessage(String message) {
		broadcast(nickName + " : " + message);
	}

	private void doJoin(String nickName, Writer writer) {
		this.nickName = nickName;

		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);

		addWriter(writer);

		// ack
		PrintWriter printWriter = (PrintWriter) writer;
		printWriter.println("join:ok");
		printWriter.flush();
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void broadcast(String data) {
		System.out.println("[ChatServerThread] 서버가 client로 전달할 데이터  :" + data);
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				// println 메서드 역할 : 데이터를 출력 스트림에 써서 연결된 대상(소캣)으로 전송
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

}
