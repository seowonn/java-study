package prob02;

public class Goods {
	
	private String name;
	private int price;
	private int quantity;
	
	public Goods(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void printInfo() {
		System.out.printf("%s(가격:%d원)이 %d개 입고 되었습니다. %n", this.name, this.price, this.quantity);
	}
}