package structural.proxy.context;

import structural.proxy.Subject;

public class Context {
	
	private Subject realSubject;
	
	public Context() {
		// 람다식: (매개변수) -> {실행문}
		this(() -> {System.out.println("Subject.doAction in Context done");});
	}
	
	public Context(Subject subject) {
		realSubject = subject;
	}

	Subject getSubject() {
		return realSubject;
	}

}
