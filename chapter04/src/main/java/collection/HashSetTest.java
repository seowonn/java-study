package collection;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		
		// 이렇게 넣어도 이제 contains에서 equals로 값을 비교하여 출력하기 때문에 contains는 true를 반환하게 된다 .
		String str = new String("마이콜");
	
		set.add("둘리");
		set.add("마이콜");
		set.add("도우너");
		set.add(str);
		
		System.out.println(set.size());
		System.out.println(set.contains("마이콜")); // 객체가 아니라 값 자체의 포함 여부를 출력한다.
		
		// 순회
		for(String str2 : set) {
			System.out.println(str2);
		}
	}

}
