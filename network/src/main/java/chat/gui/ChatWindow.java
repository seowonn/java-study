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
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	public ChatWindow(String name) {
		// 소캣은 ChatWindow 호출 시에 생성한다.
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);

		// 클래스 이름을 특정하지 않고(따로 파일, 클래스로 빼지 않고) 바로 구현해버리는 것 : 익명 클래스
		// new 인터페이스명() { ... } 형태로 작성
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});
		
		// 위 코드를 아래와 같이 람다 형식으로 바꿀 수 있다. 
		/*
		buttonSend.addActionListener(
				(ActionEvent actionEvent) -> {}
		);
		*/

		// Textfield - 채팅 전송 문자 쓰는 곳
		textField.setColumns(80);
		// send 버튼 말고도 그냥 엔터 눌렀을 때 메시지를 전송하기 위한 기능 추가 
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if(keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
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
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		// main thread는 window를 띄우고 없어진다.

		// 1. 서버 연결 작업
		// 항상 통신은 보내고 받아야 한다. 즉, ACK 신호를 무조건 받는 작업을 넣어야 한다.
		// 2. IO Stream Set
		// 3. JOIN Protocol
		// 4. ChatClient Thread 생성
	}
	
	// window 종료 시를 quit 요청으로 생각하고 quit ack 신호를 응답으로 주자. 

	private void sendMessage() {
		String message = textField.getText();
		System.out.println("메세지를 보내는 프로토콜 구현!: " + message);
		
		textField.setText("");
		textField.requestFocus();
		
		// ChatClientThread에서 서버로 부터 받은 메시지가 있다고 치고~
		// 여기 마무리 하는 게 과제 
		updateTextArea("아무개:" + message);
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	// 서버가 죽으면 client도 꺼야 좋다. 그렇다고 client에서 바로 종료하면 서비스적이지 X.
	// 따라서 알려주고 꺼야 함. 
	private void finish() {
		// quit protocol 구현하고 quit:ok ack 신호가 왔을 때, 프로그램을 종료한다. 
		
		
		// 프로그램 종료 방법 : main에서 return으로 끝내기 또는 아래와 같이 실행 
		System.exit(0);
	}
	
	// 데이터를 보내는 것의 시뮬레이션은 우리 과제 
	// main 화면, 채팅 창을 닫았을 때, 어떤 순서로, 어떤 메모리의 스레드가 닫히는지 파악하기 
	
	// inner class로 선언하여 메시지를 스레드로부터 추출해온다. 
	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			updateTextArea("....");
		}
	}
}
