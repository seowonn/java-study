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

	private BufferedReader bufferedReader;
	private Writer printWriter;
	private String nickName;
	private Socket socket;
	private List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
					true);

			while (true) {
				String request = bufferedReader.readLine();
				if (request == null) {
					ChatServer.log("클라이언트로부터 연결 끊김");
					doQuit(printWriter);
					break;
				}
				if (analyzeProtocol(request.split(":"))) {
					break;
				}
			}

		} catch (IOException e) {
			ChatServer.log("ChatServerThread 실행 중 에러 발생: " + e);
		} finally {
			closeResources();
		}
	}

	private boolean analyzeProtocol(String[] tokens) throws IOException {
		if (tokens.length < 1 || tokens[0].trim().isEmpty()) {            
	        ChatServer.log("잘못된 요청: 빈 프로토콜입니다.");
	        return false;
	    }

		if ("join".equals(tokens[0])) {
			doJoin(tokens[1], printWriter);
		} else if ("message".equals(tokens[0])) {
			if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
	            ChatServer.log("요청 메시지가 없습니다.");
	            return false;
	        }
			String decodeString = new String(Base64.getDecoder().decode(tokens[1]), StandardCharsets.UTF_8);
			System.out.println("서버가 받은 request: " + decodeString);
			doMessage(decodeString);
		} else if ("quit".equals(tokens[0])) {
			doQuit(printWriter);
			((PrintWriter) printWriter).println("quit:ok");
			printWriter.flush();
			return true;
		} else {
			ChatServer.log("error: 알 수 없는 요청 (" + tokens[0] + ")");
		}
		return false;
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);
		broadcast(nickName + "님이 퇴장 하였습니다.");
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
		broadcast(nickName + "님이 참여하였습니다.");
		addWriter(writer); // 특정 스레드의 사용자를 listWriters에 추가해야함

		PrintWriter printWriter = (PrintWriter) writer;
		printWriter.println("join:ok"); // ack 신호 전달
		printWriter.flush();
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void broadcast(String data) {
		System.out.println("[ChatServerThread] 서버가 client로 전달할 데이터: " + data);
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data); // println: 데이터를 출력 스트림에 써서 연결된 대상(소캣)으로 전송
				printWriter.flush();
			}
		}
	}

	private void closeResources() {
		try {
			if (bufferedReader != null)
				bufferedReader.close();
			if (printWriter != null)
				printWriter.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			ChatServer.log("ChatServerThread 자원 해제 중 에러 발생: " + e);
		}
	}

}
