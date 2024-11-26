package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class FileReaderTest {

	public static void main(String[] args) {
		Reader withChar = null;
		InputStream withByte = null;
		
		try {
			// 문자 기반 스트림으로 읽은 경우 
			withChar = new FileReader("text.txt");
			
			// 바이트 기반 스트림으로 읽은 경우 
			withByte = new FileInputStream("text.txt");
			
			int count = 0;
			
			int data = -1;
			System.out.println("문자 기반 스트림으로 읽은 경우 ");
			while((data = withChar.read()) != -1) {
				System.out.print((char) data);
				count++;
			}
			
			System.out.println("");
			System.out.println("count:" + count);
			System.out.println("=========================");
			
			count = 0;
			data = -1;
			System.out.println("바이트 기반 스트림으로 읽은 경우 ");
			while((data = withByte.read()) != -1) {
				System.out.print((char) data);
				count++;
			}
			
			System.out.println("");
			System.out.println("count:" + count);
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(withChar != null) {					
					withChar.close();
				}
				if(withByte != null) {					
					withByte.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
