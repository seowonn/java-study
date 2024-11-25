package chapter04;

public class StringTest03 {

	public static void main(String[] args) {
		String s1 = "Hello " + "World " + "Java" + 21;
		
		String s2 = new StringBuilder("Hello ")
						.append("World ")
						.append("Java")
						.append(21)
						.toString();
		
		String s3 = new StringBuffer("Hello ")
						.append("World ")
						.append("Java")
						.append(21)
						.toString();
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		
		
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < 1000000; i++) {
			sb.append("h");
		}
		
		String str = sb.toString();
		
		// String method들...
		String s4 = "abcABCabcAbc";
		System.out.println(s4.length());
		System.out.println(s4.charAt(2));
		System.out.println(s4.indexOf("abc")); // 검색 문자 발견되는 첫번째 인덱스가 출력됨
		System.out.println(s4.indexOf("abc", 7));
		System.out.println(s4.substring(3));
		System.out.println(s4.substring(3, 5));
		
		String s5 = "       ab      cd    ";
		String s6 = "abc,def,ghi";
		
		System.out.println(s5.trim()); // 양 가장자리의 여백만 거둬냄
		
		System.out.println("---" + s5.trim() + "---");
		String[] tokens = s6.split(",");
		for(String s: tokens) {
			System.out.println(s);
		}
		
	}

}
