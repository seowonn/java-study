package prob02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sol02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int[] intArray = new int[5];
		double sum = 0;
		
		List<Integer> numbers = new ArrayList<>();

		/* 코드 작성 */
		System.out.println("5개의 숫자를 입력하세요.");
		for(int i = 0; i < 5; i++) {
			intArray[i] = scanner.nextInt();
			sum += intArray[i];
			
			numbers.add(intArray[i]);
		}
		
		System.out.println("평균은 " + sum / (double) 5 + "입니다.");
		
		int streamAvg = numbers.stream().mapToInt(i -> i).sum();
		System.out.println("stream을 이요해 구한 평균은 " + streamAvg / (double) 5 + "입니다.");
		
		scanner.close();
	}
}
