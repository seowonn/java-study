package behavioral.strategy;

public class Client {
	
	// client에게 전략을 위임하면 안된다. client는 sql문 등 재료만 넣게 해야 한다. 
	public static void main(String[] args) {
		CalculateContext cc = new CalculateContext();
		cc.operation(new CalculateStrategy() {
			
			@Override
			public int calculate(int val1, int val2) {
				return val1 + val2;
			}
		});
		
		cc.operation((val1, val2) -> val1 - val2);
		cc.operation((val1, val2) -> val1 * val2);
		cc.operation((val1, val2) -> val1 / val2);
	}
	
}
