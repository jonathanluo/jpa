package org.moonwave.jpa.model;

import java.sql.Date;

import javax.persistence.Column;
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
public class Employees {
	

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="emp_no")
	Long empNo;
	
	@Column(name="birth_date")
	Date birthDate;

	@Column(name="first_name")
	String firstName;

	@Column(name="last_name")
	String lastName;

	@Column(name="hire_date")
	Date hireDate;

	public Long getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public Date getBirthDay() {
		return birthDate;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDate = birthDay;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("empNo: ").append(empNo);
		sb.append(", birthDate: ").append(birthDate);
		sb.append(", firstName: ").append(firstName);
		sb.append(", lastName: ").append(lastName);
		sb.append(", hireDate: ").append(hireDate);
		
		return sb.toString();
	}
	
}

