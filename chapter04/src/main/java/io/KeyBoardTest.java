package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyBoardTest {

	public static void main(String[] args) {
		
		BufferedReader br = null;
		
		try {
			// 1. 기반 스트림(표준입력, stdin, System.in)
			// JVM에 자동으로 올라가는 스트림들 
			
			// 2. 보조 스트림 (byte|byte|byte -> char)
			InputStreamReader isr = new InputStreamReader(System.in, "utf-8");
			
			// 3. 보조 스트림(char1|char2|char3|\n -> "char1chR2chars);
			br = new BufferedReader(isr);
		
			String line = null;
			while((line = br.readLine()) != null) {
				if("quit".equals(line)) {
					break;
				}
				System.out.println(line);
			}
			
		} catch (IOException e) {
			System.out.println("error:" + e);
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
