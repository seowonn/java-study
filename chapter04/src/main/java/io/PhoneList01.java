package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {

		BufferedReader br = null;
		
		try {			
	//		File 클래스는 스트림이 아니다. 즉, File은 io와 상관이 없다. 
			File file = new File("./phone.txt");
			
	//		이 경우 try catch 보단 if문으로 처리하는게 성능상 더 좋다. 
			if(!file.exists()) {
				System.out.println("File Not Found");
				return;
			}
			
			System.out.println("== 파일정보 ==");
			System.out.println(file.getAbsolutePath());
			
			// file.length()는 차지하는 파일의 크기를 바이트로 반환한다.
			System.out.println(file.length() + "bytes");
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));
			
			System.out.println("== 전화번호 ==");
			
			// 1. 기반 스트림
			FileInputStream fis = new FileInputStream(file);

			// 2. 보조 스트림1
			InputStreamReader isr = new InputStreamReader(fis);
			
			// 3. 보조 스트림2
			// 여기선 왜 굳이 보조 스트림이 어떤 이유로 2개나 쓰였는지?
			br = new BufferedReader(isr);
			
			// 4. 처리
			String line = null;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "\t ");	// 분리자를 두번째 인자에 넣어줌
				
				int index = 0; // 이거 왜 필요하다고/
				// iterator다 보니까 
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					
					if(index == 0) {	// 이름
						System.out.print(token + ":");
					} else if (index == 1) {	// 전화번호1
						System.out.print(token + "-");
					} else if (index == 2) {	// 전화번호2
						System.out.print(token + "-");
					} else {	// 전화번호3
						System.out.print(token + "\n");
					}
					
					index++;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {					
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
