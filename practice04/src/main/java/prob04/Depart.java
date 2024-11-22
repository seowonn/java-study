package prob04;

public class Depart extends Employee {

	private String department;
	
	public Depart(String name, int salary, String department) {
		super(name, salary);
		this.department = department;
	}
	
	@Override
	public void print() {
		System.out.println( "이름:" + getName() + " 연봉:" + (getSalary() + 2000) + " 부서:" + this.department);
	}
}
