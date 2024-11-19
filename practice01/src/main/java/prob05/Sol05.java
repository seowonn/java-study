package prob05;

import java.util.Arrays;
import java.util.List;

public class Sol05 {
	public static void main(String[] args) {

		/* 코드 작성 */
		for(int num = 1; num < 100; num++) {
			String strNum = String.valueOf(num);
			
			String result = "";
			boolean flag = false;
			String[] candidates = {"3", "6", "9"};
			List<String> candidateList = Arrays.asList(candidates);
			
			for(int i = 0; i < strNum.length(); i++) {
				if (candidateList.contains(String.valueOf(strNum.charAt(i)))) {
					flag = true;
					result += "짝";
				}
			}
			
			if(flag) {
				System.out.println(num + " " + result);
			}
		}
		
		
	}
}
