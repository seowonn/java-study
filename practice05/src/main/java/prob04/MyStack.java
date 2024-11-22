package prob04;

public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		this.buffer = new String[capacity];
	}

	public void push(String s) {

		if(this.top >= this.buffer.length) {
			resize();
		} 
		this.buffer[this.top++] = s;
	}

	public String pop() throws MyStackException {
		
		if(this.top < 1) {
			throw new MyStackException();
		}
		
		String word = this.buffer[this.top - 1];
		this.top--;
		return word;
	}

	public boolean isEmpty() {
		return this.top == 0;
	}

	private void resize() {
		String[] original = new String[this.top];
		System.arraycopy(this.buffer, 0, original, 0, this.top);

		this.buffer = new String[this.top + 1];
		System.arraycopy(original, 0, this.buffer, 0, this.top);
	}	
}