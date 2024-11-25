package chapter04;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<Rect> set = new HashSet<>();
		
		Rect r1 = new Rect(10, 20);
		Rect r2 = new Rect(10, 20);
		Rect r3 = new Rect(4, 50);

		// 내용은 동일하지만, r1과 r2의 참조값은 다르기 때문에 set에 각 hashcode 검사시, 
		// 다른 값으로 인식되어 둘 다 추가가 된다. 
		set.add(r1);
		set.add(r2);
		set.add(r3);
		
		for(Rect r : set) {
			System.out.println(r);
		}
	}

}
