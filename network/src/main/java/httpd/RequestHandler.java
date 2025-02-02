package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private Socket socket;
	private final String DOCUMENT_ROOT = "./webapp";

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":"
					+ inetSocketAddress.getPort());
			
			String request = null;
			
			while(true) {
				String line = br.readLine();
				
				// 브라우저가 연결을 끊으면...
				if(line == null) {
					break;
				}
				
				// SimpleHttpServer는 HTTP Header만 읽는다.
				if("".equals(line)) {
					break;
				}
				
				// Request Header의 첫 줄만 읽음
				if(request == null) {
					request = line;
					break;
				}
			}
			
			consoleLog(request);
			String[] tokens = request.split(" ");
			if("GET".equals(tokens[0])) {
				responseStaticResources(outputStream, tokens[1], tokens[2]);
			} else { 
				// methods: POST, DELETE, PUT, HEAD, CONNECT, ...
				// SimpleHttpServer에서는 무시(400 Bad Request) 
				response400Error(outputStream, tokens[2]);
			}
			
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
//			outputStream.write("HTTP/1.1 200 OK\n".getBytes("UTF-8"));
//			outputStream.write("Content-Type:text/html; charset=utf-8\n".getBytes("UTF-8"));
//			outputStream.write("\n".getBytes());
//			outputStream.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes("UTF-8"));

		} catch (Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}

			} catch (IOException ex) {
				consoleLog("error:" + ex);
			}
		}
	}
	
	private void response400Error(OutputStream outputStream, String string) {
		/*
		 * HTTP/1.1 400 Bas Request\n
		 * Content-Type: text/html; charset=utf-8\n
		 */
	}

	private void responseStaticResources(OutputStream os, String url, String protocol) throws IOException{
		// default (welcome file)
		if("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + url);
		if(!file.exists()) {
			// 404 response 채우기 과제 
			response404Error(os, protocol);
			
			file = new File("./webapp/error/404.html");
			byte[] body = Files.readAllBytes(file.toPath());
			String contentType = Files.probeContentType(file.toPath());
			
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
			os.write("HTTP/1.1 404 Not Found\n".getBytes("UTF-8"));
			os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
			os.write("\n".getBytes());
			os.write(body);
			return;
		}
		
		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
		os.write((protocol + " 200 OK\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write("\n".getBytes());
		os.write(body);
		
	}

	private void response404Error(OutputStream os, String protocol) {

		/*
		 * HTTP/1.1 404 Not Found \n
		 * Content-Type: text/html; charset=utf-8\n
		 */
	}

	public void consoleLog(String message) {
		// 스레드별 id를 출력하는 코드
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}
