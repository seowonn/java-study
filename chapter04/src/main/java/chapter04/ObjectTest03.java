package chapter04;

public class ObjectTest03 {

	public static void main(String[] args) {
		
		String str1 = "hello";
		String str2 = new String("hello");

		System.out.println(str1 == str2); // false (참조 비교: 다른 객체)
		System.out.println(str1.equals(str2)); // true (내용 비교: 동일한 문자)
		System.out.println(str1.hashCode() + " : " + str2.hashCode());
		
		String s1 = new String("hello");
		String s2 = new String("hello");
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1.hashCode() + " : " + s2.hashCode());
		System.out.println(System.identityHashCode(s1) + " : " + System.identityHashCode(s2));
		
		System.out.println("===========================");
		
		String s3 = "hello";
		String s4 = "hello";
		
		System.out.println(s3 == s4);
		System.out.println(s3.equals(s4));
		System.out.println(s3.hashCode() + " : " + s4.hashCode());
		System.out.println(System.identityHashCode(s3) + " : " +  System.identityHashCode(s4));
		
	}

}
