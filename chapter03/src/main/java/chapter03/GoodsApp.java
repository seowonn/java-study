package chapter03;

public class GoodsApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Goods goods = new Goods();
		
		// default 접근 제어자로 동일 패키지 데이터는 접근 가능
		goods.setName("nikon");
		goods.setPrice(400000);
		goods.setCountSold(10);
		goods.setCountStock(20);
		
		goods.printInfo();
		
		// 정보 은닉(객체의 상태를 보호)
		
		// static 변수 예제
		System.out.println(Goods.countOfGoods);
		
		goods.setPrice(400000);
		System.out.println(goods.calcDiscountPrice(0.5f));
	}

}
