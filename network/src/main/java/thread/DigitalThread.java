package thread;

public class DigitalThread extends Thread {

	@Override
	public void run() {
		for(int i = 0; i <= 9; i++) {
			System.out.print(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
