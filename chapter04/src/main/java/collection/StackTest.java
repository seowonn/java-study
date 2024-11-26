package collection;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		
		stack.push("둘리");
		stack.push("마이콜");
		stack.push("또치");
		
		while(!stack.isEmpty()) {
			String str = stack.pop();
			System.out.println(str);
		}
		
		stack.push("둘리");
		stack.push("마이콜");
		stack.push("또치");
		
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		
	}

}
