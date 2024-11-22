package com.poscodx.paint.main;

import com.poscodx.paint.i.Drawable;
import com.poscodx.paint.point.ColorPoint;
import com.poscodx.paint.point.Point;
import com.poscodx.paint.shape.Circle;
import com.poscodx.paint.shape.Rectangle;
import com.poscodx.paint.shape.Shape;
import com.poscodx.paint.shape.Triangle;
import com.poscodx.paint.text.GraphicText;

public class PaintApp {

	public static void main(String[] args) {
		Point point1 = new Point();
		point1.setX(10);
		point1.setY(20);
		
//		point.show();
		draw(point1);

		Point point2 = new Point(100, 200);
//		point2.show(true);
		draw(point2);
		point2.show(false);
		
		ColorPoint point3 = new ColorPoint(50, 100, "red");
		draw(point3);
//		drawColorPoint(point3);
		
//		drawShape(new Triangle());
//		drawShape(new Rectangle());
//		drawShape(new Circle());
		draw(new Triangle());
		draw(new Rectangle());
		draw(new Circle());
		
		draw(new GraphicText("안녕"));
		
		
		// instanceof 연산자
		Circle c = new Circle();
		
		System.out.println(c instanceof Circle);
		System.out.println(c instanceof Shape);
		System.out.println(c instanceof Object);
		
		// 오류: 연산자 우측항이 클래스인 경우
		// 좌측항의 레퍼런스 타입의 hierachy의 상하위만 사용할 수 있다. 
//		System.out.println(c instanceof Rectangle);
		
		Shape shape = new Circle();
		System.out.println(shape instanceof Shape);
		System.out.println(shape instanceof Circle);
		System.out.println(shape instanceof Rectangle); // false
		
		// 연산자 우측항이 인터페이스인 경우
		// hierachy 상관없이 연산자를 사용할 수 있다.
		System.out.println(c instanceof Drawable);
		System.out.println(c instanceof Runnable);
		
	}
	
	public static void draw(Drawable drawable) {
		drawable.draw();
	}
	
	// 아래 코드는 추가할 필요가 없었음. 이유: 자식 클래스인 ColorPoint에서 show()를 Override 했기 때문에
	// 어차피 호출 시 자식 클래스에서 재정의한 메서드가 불리기 때문이다!
	// 그래서 상속은 확장성 측면? 재사용 측면에서 이점이 있다. 
//	public static void drawColorPoint(ColorPoint colorPoint) {
//		colorPoint.show();
//	}
	
//	public static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
//	
//	public static void drawRectangle(Rectangle rectangle) {
//		rectangle.draw();
//	}

}
