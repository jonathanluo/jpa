package org.moonwave.jpa.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 2007 JPA 101
 * @author jonathan
 * 
 * Use persistence-employee.xml
 * 	<persistence-unit name="jpa-mysql" transaction-type="RESOURCE_LOCAL">
 */
@Entity
public class Employees4 {
	
	@Id
	Long emp_no;
	String first_name;
	String last_name;

	
	public Long getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Long emp_no) {
		this.emp_no = emp_no;
	}

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("empNo: ").append(emp_no);
		sb.append(", firstName: ").append(first_name);
		sb.append(", lastName: ").append(last_name);
		
		return sb.toString();
	}
}
