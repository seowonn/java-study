package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ChatClientThread extends Thread {
	
	private Socket socket;
	private BufferedReader bufferedReader;

	public ChatClientThread(Socket socket, BufferedReader br) {
		this.socket = socket;
		this.bufferedReader = br;
	}

	@Override
	public void run() {
		try {
			while(true) {				
				String message = bufferedReader.readLine();
				if("quit:ok".equals(message)) {
					break;
				}
				System.out.println(message);
			}
		} catch (IOException e) {
			ChatClient.log("error: " + e);
		}
	}
	
}