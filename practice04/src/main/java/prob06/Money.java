package prob06;

public class Money {

	private int amount;
	
	public Money(int amount) {
		this.amount = amount;
	}

	public Object add(Money money) {
		return new Money(amount + money.amount);
	}

	public Object minus(Money money) {
		return new Money(amount - money.amount);
	}

	public Object multiply(Money money) {
		return new Money(amount * money.amount);
	}

	public Object divide(Money money) {
		return new Money(amount / money.amount);
	}
}
