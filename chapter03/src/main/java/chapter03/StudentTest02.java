package chapter03;

public class StudentTest02 {

	public static void main(String[] args) {
		
		Student s1 = new Student();
		Person p1 = s1; // upcasting ( 암시작)
		
		Student s2 = (Student) p1; // 부모에서 자식으로 자동 할당 안됨. (자식의 후보가 많을 경우, 고를 수 없기 때문) 
	}

}
