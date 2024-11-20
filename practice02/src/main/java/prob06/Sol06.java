package prob06;

import java.util.Random;
import java.util.Scanner;

public class Sol06 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("수를 결정하였습니다. 맞추어보세요:");
			System.out.println("1-100");
			
			int askCnt = 1;

			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt(100) + 1;
			System.out.println(correctNumber);
			
			while(true) {
				System.out.print(askCnt++ + ">>");
				int num = scanner.nextInt();
				
				if (num == correctNumber) {
                    System.out.println("맞췄습니다.");
                    break;
                }
                System.out.println(num > correctNumber ? "더 낮게" : "더 높게");
			}

			
			//새 게임 여부 확인하기
			System.out.print("다시 하겠습니까(y/n)>>");
			String answer = scanner.next();
			if("y".equals(answer) == false) {
				break;
			}
		}
		
		scanner.close();
	}
}