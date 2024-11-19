package prob03;

import java.util.Scanner;

public class Sol03 {
	
	public static void main(String[] args) {

		/* 코드 작성 */
		while(true) {
			System.out.print("수를 입력하세요: ");
			Scanner sc = new Scanner(System.in);
			String str = sc.next();
			if(str.equals("_")) {
				break;
			}
			
			int num = Integer.parseInt(str);
			
			int sum = 0;
			boolean even = num % 2 == 0;
			for(int i = 1; i <= num; i++) {
				sum += even && i % 2 == 0 ? i : 0;
				sum += !even && i % 2 != 0 ? i : 0;
			}
			
			System.out.println("결과값: " + sum);
		}
	}
}
