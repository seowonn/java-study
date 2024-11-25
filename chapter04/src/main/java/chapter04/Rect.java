package chapter04;

import java.util.Objects;

public class Rect {

	private int w;
	private int h;
	
	public Rect (int w, int h) {
		this.w = w;
		this.h = h;
	}

	@Override
	public String toString() {
		return "Rect [w=" + w + ", h=" + h + "]";
	}

	// 이걸 override 해줘야 동일한 내용의 객체가 중복으로 들어가지 않게 되며, 
	// 동일한 hashcode 값을 같게 한다. 
	@Override
	public int hashCode() {
		return Objects.hash(h, w);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rect other = (Rect) obj;
		return h == other.h && w == other.w;
	}
		
}
