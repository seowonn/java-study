package prob02;

public class Sol02 {
	public static void main(String[] args) {
		
		String baseNums = "1 2 3 4 5 6 7 8 9 ";
		
		/* 코드 작성 */
		for(int j = 10; j <= 18; j++) {
			System.out.print(baseNums);
			for(int k = 10; k <= j; k++) {
				System.out.print(k + (k < j ? " " : "\n"));
			}
		}

	
	}
}