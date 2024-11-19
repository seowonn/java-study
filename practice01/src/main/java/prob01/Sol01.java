package prob01;

import java.util.Scanner;

public class Sol01 {
	public static void main(String[] args) {
		
		/* 코드 작성 */
		Scanner scanner = new Scanner(System.in);

		int num = scanner.nextInt();
		System.out.println(num % 3 == 0 ? "3의 배수입니다." : "");
		
		scanner.close();
	}
}