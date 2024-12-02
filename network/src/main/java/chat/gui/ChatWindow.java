package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;

import chat.ChatServer;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	Socket socket = null;
	PrintWriter printWriter = null;
	BufferedReader bufferedReader = null;
	ChatClientThread chatClientThread = null;
	private static final String SERVER_IP = "192.168.1.163";

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {

		// 1. 서버 연결 작업 - 소캣과 ChatClientReceiveThread는 ChatWindow 호출 시에 생성 및 시작한다.
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// JOIN protocol 생성
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			printWriter.println("join:" + frame.getTitle());
			printWriter.flush();

			chatClientThread = new ChatClientThread(bufferedReader);
			chatClientThread.start();

		} catch (IOException e) {
			log("error : " + e);
		}

		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);

		// 클래스 이름을 특정하지 않고(따로 파일, 클래스로 빼지 않고) 바로 구현해버리는 것 : 익명 클래스
		// new 인터페이스명() { ... } 형태로 작성
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage(printWriter);
			}
		});

		// 위 코드를 아래와 같이 람다 형식으로 바꿀 수 있다.
		/*
		 * buttonSend.addActionListener( (ActionEvent actionEvent) -> {} );
		 */

		textField.setColumns(80); // Textfield - 채팅 전송 문자 쓰는 곳
		// send 버튼 말고도 그냥 엔터 눌렀을 때 메시지를 전송하기 위한 기능 추가
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (keyChar == KeyEvent.VK_ENTER) {
					sendMessage(printWriter);
				}
			}
		});

		// Pannel - 보조 프레임
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			// window 종료 시를 quit 요청으로 생각하고 서버에서 quit ack 신호를 응답으로 주기
			public void windowClosing(WindowEvent e) {
				finish(bufferedReader, printWriter);
			}
		});
		frame.setVisible(true);
		frame.pack();
	}

	private void sendMessage(PrintWriter printWriter) {

		if (printWriter == null) {
			log("error: PrintWriter is null.");
			return;
		}

		String message = textField.getText();
		System.out.println("메세지를 보내는 프로토콜 구현!: " + message);

		textField.setText("");
		textField.requestFocus();

		try {
			String endcodedInput = Base64.getEncoder().encodeToString(message.getBytes("UTF-8"));
			printWriter.println("message:" + endcodedInput);
			printWriter.flush();
		} catch (IOException e) {
			log("error : " + e);
		}

	}

	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	// 서버가 죽으면 client도 꺼야 좋다. 그렇다고 client에서 바로 종료하면 서비스적이지 X서 알려주고 꺼야 함.
	private void finish(BufferedReader bufferedReader, PrintWriter printWriter) {
		try {
			// QUIT protocol 구현
			printWriter.println("quit:" + frame.getTitle());
			printWriter.flush();

			try {
				chatClientThread.join();
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (InterruptedException e) {
				log("error:" + e);
			}

		} catch (IOException e) {
			log("error: " + e);
		}
		System.exit(0);
	}

	// inner class로 선언하여 메시지를 스레드로부터 추출해온다.
	private class ChatClientThread extends Thread {

		private BufferedReader bufferedReader;

		private ChatClientThread(BufferedReader br) {
			this.bufferedReader = br;
		}

		@Override
		public void run() {
			while (true) {
				try {
					String message = bufferedReader.readLine();

					// ChatClientThread는 Main thread와 달리 항상 서버의 응답을 기다리고 있기 때문에
					// 서버의 ack 응답을 받는 부분이 구현되어야 한다.
					if ("quit:ok".equals(message)) {
						break;
					} else if ("join:ok".equals(message)) {
						continue;
					} else {
						updateTextArea(message);
					}
				} catch (IOException e) {
					log("error : " + e);
				}
			}
		}
	}

	public static void log(String message) {
		System.out.println("[ChatWindow]: " + message);
	}
}
