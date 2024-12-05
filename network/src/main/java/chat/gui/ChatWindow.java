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

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		connectToServer();
		sendJoinProtocol();
		initializeGUI();
		registerMessageSendingEventHandler();
		frame.setVisible(true);
		frame.pack();

		chatClientThread = new ChatClientThread(bufferedReader);
		chatClientThread.start();
	}

	private void connectToServer() {
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(ChatServer.SERVER_IP, ChatServer.PORT));
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
		} catch (IOException e) {
			log("서버 연결 작업 도중 에러 발생: " + e);
		}
	}

	private void sendJoinProtocol() {
		if (printWriter != null) {
			printWriter.println("join:" + frame.getTitle());
			printWriter.flush();
		}
	}

	private void initializeGUI() {
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);

		textField.setColumns(80);

		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);
	}

	private void registerMessageSendingEventHandler() {
		buttonSend.addActionListener((actionEvent) -> sendMessage());

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		frame.addWindowListener(new WindowAdapter() {	// 화면 종료 시 채팅 종료
			@Override
			public void windowClosing(WindowEvent e) {
				finish(bufferedReader);
			}
		});
	}

	private void sendMessage() {

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
	private void finish(BufferedReader bufferedReader) {
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
					if (message == null) {
		                continue;
		            }

					// ChatClientThread는 항상 서버의 응답을 기다리고 있음. ack 신호 처리 구현 필요
					if(!continueMessage(message)) {
						break;
					}
				} catch (IOException e) {
					log("error : " + e);
				}
			}
		}

		private boolean continueMessage(String message) {
			if ("quit:ok".equals(message)) {
				return false;
			} else if ("join:ok".equals(message)) {
				updateTextArea("환영합니다. 채팅을 시작할 수 있습니다.");
			} else {
				updateTextArea(message);
			}
			return true;
		}
	}

	public static void log(String message) {
		System.out.println("[ChatWindow]: " + message);
	}
}
