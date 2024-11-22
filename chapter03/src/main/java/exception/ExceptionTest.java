package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 0;
		
		System.out.println("some code1...");
		
		try {			
			
			System.out.println("some code2...");
			
			int result = a / b;
			
			System.out.println("some code3...");
		
		} catch (ArithmeticException e) {
			
			// 1. 로깅
			System.out.println("error: " + e);
			
			// 2. 사과
			System.out.println("미안합니다...");
			
			// 3. 정상 종료
			return;
		
		} finally {	// 예외 발생 여부와 상관없이 항상 실행되는 곳 
			System.out.println("자원 정리 : ex) close file, socket, db connection close");
		}
		
		// 정상 동작 시 실행되는 코드의 마무리 단
		System.out.println("some code4...");
	}

}
