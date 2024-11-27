package io;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PhoneList02 {

	public static void main(String[] args) {

		Scanner sc = null;
		
		try {			
	//		File 클래스는 스트림이 아니다. 즉, File은 io와 상관이 없다. 
			File file = new File("./phone.txt");
			
	//		이 경우 try catch 보단 if문으로 처리하는게 성능상 더 좋다. 뭔말?
			if(!file.exists()) {
				System.out.println("File Not Found");
				return;
			}
			
			System.out.println("== 파일정보 ==");
			System.out.println(file.getAbsolutePath());
			
			// File이 byte 단위로 읽어와서 그런가?
			System.out.println(file.length() + "bytes");
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));
			
			System.out.println("== 전화번호 ==");
			
			sc = new Scanner(file);
			
			// 4. 처리
			while(sc.hasNextLine()) {
				String name = sc.next(); // 스페이스, 탭 개행을 한다.
				String phone01 = sc.next();
				String phone02 = sc.next();
				String phone03 = sc.next();
				
				System.out.println(name + ":" + phone01 + "-" + phone02 + "-" + phone03);
			}
			
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			sc.close();
		}
	}

}
