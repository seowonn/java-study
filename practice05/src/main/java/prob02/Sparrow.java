package prob02;

public class Sparrow extends Bird{

	@Override
	public void fly() {
		System.out.printf("참새(%s)가 날아다닙니다. %n", this.name.substring(0,2));	
	}

	@Override
	public void sing() {
		System.out.printf("참새(%s)가 소리내어 웁니다. %n", this.name.substring(0,2));	
	}
	
	@Override
	public String toString() {
		return "참새의 이름은 " + this.name.substring(0,2) + " 입니다.";
	}

}
