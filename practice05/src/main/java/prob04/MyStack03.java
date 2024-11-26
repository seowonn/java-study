package prob04;

public class MyStack03<E> {
	
	private int top;
	private T[] buffer;

	@SuppressWarnings("unchecked")
	public MyStack03(int capacity) {
		top = -1;
		buffer = (T[]) new Object[capacity];
	}

	public void push(T s) {

		if(top == buffer.length - 1) {
			resize();
		} 
		buffer[++top] = s;
	}

	public T pop() throws MyStackException {
		
		if(isEmpty()) {
			throw new MyStackException();
		}
		
		T word = buffer[top];
		this.top--;
		return word;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	private void resize() {
		T[] temp = (T[]) new Object[buffer.length * 2];
		for(int i = 0; i <= top; i++) {
			temp[i] = buffer[i];
		}
	}	
}
