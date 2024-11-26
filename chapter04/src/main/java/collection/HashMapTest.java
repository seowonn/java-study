package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		
		map.put("one", 1); // auto boxing
		map.put("two", 2); // auto boxing
		map.put("three", 3); // auto boxing
		
		int i = map.get("one");
		int j = map.get("two");
		int k = map.get("three");
		
		// 순회
		Set<String> keySet = map.keySet();
		for(String key : keySet) {
			System.out.println(map.get(key));
		}
	}

}
