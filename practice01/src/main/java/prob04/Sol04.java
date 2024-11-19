package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {
		
		/* 코드 작성 */
		System.out.print("문자열을 입력하세요: ");
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		
		String result = "";
		
		for(int i = 0; i < str.length(); i++) {
			result += str.charAt(i);
			System.out.println(result);
		}
		
		scanner.close();
	}
}