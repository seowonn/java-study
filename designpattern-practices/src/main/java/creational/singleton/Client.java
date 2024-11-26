package creational.singleton;

public class Client {

	public static void main(String[] args) {

		// 하나의 인스턴스를 유지하는 것이 목적
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
		
	}

}
