package com.poscodx.paint.point;

public class ColorPoint extends Point {

	private String color;
	
	public ColorPoint(int x, int y, String color) {
		super(x, y); // setx, setY 대신 부모 생성자를 호출해서 사용한다.
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		System.out.printf("Point[x=%d, y=%d, color=%s]를 그렸습니다. %n", getX(), getY(), this.color);
	}

	@Override
	public void draw() {
		show();
	}
	
	
}
