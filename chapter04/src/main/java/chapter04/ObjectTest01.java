package chapter04;

public class ObjectTest01 {

	public static void main(String[] args) {
		
		Point point = new Point();
		Class cls = point.getClass();	// reflection 
		System.out.println(cls);
		
		System.out.println(point.hashCode());	// 객체의 address 기반의 해시코드,
		// 객체가 갖는 유일한 값. address는 X. reference도 X 
		
		System.out.println(point.toString());	// getClass().toString() + "@" + hashcofr)
		System.out.println(point);
		
	}

}
