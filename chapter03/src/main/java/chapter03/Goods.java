package chapter03;

public class Goods {
	
	public static int countOfGoods = 0;
	
	private String name;
	private int price;
	private int countStock;
	private int countSold;
	
	public Goods() {
		System.out.println(Math.PI);
		countOfGoods++;
	}
	
	public void printInfo() {
		System.out.println("상품이름: " + name + 
				", 가격: " + price +
				", 판매량: " + countSold +
				", 재고량: " + countStock);
	}
	
	public int calcDiscountPrice(float discountRate) {
		int result = price - (int) (price * discountRate);
		return result;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	
	
}
