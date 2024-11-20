package prob05;

import java.util.Arrays;

public class Sol05 {
	public static void main(String[] arg) {

		int array[] = {5, 9, 3, 8, 60, 20, 1};
		int count = array.length;

		System.out.println("Before Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();

		boolean changed = false;

		for(int j = 1; j < array.length; j++) {
			System.out.println("\n" + j + "회" + (j == array.length - 1 ? " 마지막" : ""));
			
			for(int i = 0; i < count - j; i++) {
				
				changed = false;
				
				if(array[i] < array[i + 1]) {
					changed = true;
					
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					
				}
				
				System.out.print(Arrays.toString(array));
				if(changed) {
					System.out.println(" " + array[i + 1] + ", " + array[i] + " 바꾼다");
				} else {
					System.out.println(" " + array[i] + ", " + array[i + 1] + " 제자리");
				}
			}
		}

		System.out.println("\nAfter Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}
	}
}