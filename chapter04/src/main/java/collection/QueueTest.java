package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<>();
		
		queue.offer("둘리");
		queue.offer("마이콜");
		queue.offer("도우너");
		
		while(!queue.isEmpty()) {
			String s = queue.poll();
			System.out.println(s);
		}
	}

}
