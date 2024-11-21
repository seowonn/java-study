package chapter03;

public class StaticMethod {
	int n;
	static int m;
	
	void f1() {
		// 인스턴스 메서드에서는 당연히 인스턴스 변수에 접근할 수 있다. 
		// 단, 해당 객체의 n 값만 변경이 되는 것이다. 
		n = 10;
	}
	
	void f2() {
		
		// 같은 클래스의 static 변수 접근에서 클래스 이름을 생략할 수 있다. 
		StaticMethod.m = 10;
		m = 20;
	}
	
	void f3() {
		f2();
	}
	
	void f4() {
		// 같은 클래스의 static 변수 접근에서 클래스 이름을 생략할 수 있다. 
		s1();
	}
	
	static void s1() {
		// 인스턴스 생성 후에 할당되는 인스턴스 변수를 static 메서드에서는 접근 불가능하다. 
//		n = 10;
	}
	
	static void s2() {
		// 위와 같은 맥락으로, 인스턴스 메서드를 static에서 호출할 수 없다. 
//		f1();
	}
	
	static void s3() {
		StaticMethod.m = 10;
		m = 20;
	}
	
	static void s4() {
		StaticMethod.s1();
		s1();
	}
}
