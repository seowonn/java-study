package chapter03;

import myPackage.Goods2;

public class Goods2App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Goods2 goods2 = new Goods2();
		
		// public은 접근 제한이 없다.
		goods2.name = "camera";
		
		// protected는 같은 패키지에서 접근이 가능하다.
		// 더 중요한 것은 자식에서 접근이 가능하다. 
//		goods2.price = 10000;
		
		// default는 같은 패키지에서 접근이 가능하다. 
//		goods2.countStock = 10;
		
		// private은 내부에서만 접근이 가능하다. 
//		goods2.countSold = 20;

	}

}
