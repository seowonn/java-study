package structural.decorator;

// Decorator에 기본 생성자가 없어, 에러가 뜸.
public class ParenthesesDecorator extends Decorator {

	public ParenthesesDecorator(Component component) {
		super(component);
	}
	
	@Override
	public String operation() {
		String text = component.operation();
		return "(" + text + ")";
	}

}
