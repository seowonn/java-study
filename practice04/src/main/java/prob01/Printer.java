package prob01;

public class Printer {
	
//	public void println(int num) {
//		System.out.println(num);
//	}
//	
//	public void println(boolean flag) {
//		System.out.println(flag);
//	}
//	
//	public void println(double doubleNum) {
//		System.out.println(doubleNum);
//	}
//	
//	public void println(String str) {
//		System.out.println(str);
//	}
	
	// 제네릭으로 써야 되는 것을 의미하는 표시 
	public <T> void println(T t) {
		System.out.println(t);
	}
	
	public <T> void println(T... ts) {
		for(T t : ts) {
			System.out.println(t + " ");
		}
		
		System.out.println("\n");
	}
	
	public int sum(Integer... nums) {
		int s = 0;
		for(Integer n : nums) {
			s += n;
		}
		return s;
	}

}
