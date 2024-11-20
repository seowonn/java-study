package prob03;

public class Sol03 {
	public static void main(String args[]) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.' };

		// 원래 배열 원소 출력
		printCharArray(c);

		// 공백 문자 바꾸기
		replaceSpace(c);

		// 수정된 배열 원소 출력
		printCharArray(c);
	}
	
	static void printCharArray(char[] c) {
		for(int i = 0; i < c.length - 1; i++) {
			System.out.print(c[i]);
		}
		System.out.println();
	}
	
	static void replaceSpace(char[] c) {
		for(int i = 0; i < c.length - 1; i++) {
			c[i] = c[i] == ' ' ? ',' : c[i];
		}
	}
}