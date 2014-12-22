package org.moonwave.jpa.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 2007 JPA 101
 * @author jonathan
 * 
 * Use persistence-employee.xml
 * 	<persistence-unit name="jpa-mysql" transaction-type="RESOURCE_LOCAL">
 */
@Entity
@Table(name="employees")
public class Employees2 {
	

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long emp_no;
	
	Date birth_date;

	String first_name;

	String last_name;

	Date hire_date;

	
	
	public Long getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Long emp_no) {
		this.emp_no = emp_no;
	}

	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
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

	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("empNo: ").append(emp_no);
		sb.append(", birthDate: ").append(birth_date);
		sb.append(", firstName: ").append(first_name);
		sb.append(", lastName: ").append(last_name);
		sb.append(", hireDate: ").append(hire_date);
		
		return sb.toString();
	}
}

