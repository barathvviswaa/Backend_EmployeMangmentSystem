package EmpAllDetails;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EmployeeBean implements Serializable {
	private String id,name,des,exp;
	private float salary;

	public EmployeeBean() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des ;
	}
	
	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp ;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;

	}
}
