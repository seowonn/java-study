package structural.proxy.context;

import structural.proxy.Subject;

public class Proxy implements Subject {

	@Override
	public void doAction() {
		new Context().getSubject().doAction();
	}

}
