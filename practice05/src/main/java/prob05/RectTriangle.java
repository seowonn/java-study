package prob05;

public class RectTriangle extends Shape {
	
	public RectTriangle(int width, int height) {
		super(width, height);
	}

	@Override
	public double getArea() {
		return width * height * 0.5;
	}

	@Override
	public double getPerimeter() {
		return width * height + Math.sqrt(width * width + height * height);
	}

}
