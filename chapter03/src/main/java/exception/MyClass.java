package exception;

import java.io.IOException;

public class MyClass {
	
	public void danger() throws IOException, MyException {	// 메서드 내부에 throw가 있다면 메서드 외부는 throws로 감싸줘야 한다.
		System.out.println("some code1...");
		System.out.println("some code2...");
		
		if(1 - 1 == 0) {
			throw new MyException();
		}
		
		if(2 - 2 == 0) {			
			throw new IOException();	// 예외를 던지는 명령어 : throw 
		}
		
		System.out.println("some code3...");
		System.out.println("some code4...");
	
	}
}
