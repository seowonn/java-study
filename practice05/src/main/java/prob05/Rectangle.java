package prob05;

public class Rectangle extends Shape implements Resizable {
	
	public Rectangle(int width, int height) {
		super(width, height);
	}

	@Override
	public double getArea() {
		return (double) width * height;
	}

	@Override
	public double getPerimeter() {
		return (double) (width + height) * 2;
	}

	@Override
	public void resize(double rate) {
		width *= rate;
		height *= rate;
	}

}
