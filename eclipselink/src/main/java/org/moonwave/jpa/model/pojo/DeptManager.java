package org.moonwave.jpa.model.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 2007 JPA 101
 * @author jonathan
 * 
 * Use persistence-employee.xml
 * 	<persistence-unit name="jpa-mysql" transaction-type="RESOURCE_LOCAL">
 */

/**
 * A POJO used as pseudo entity to retrieve result from native query
 * This entity does not map to any physical table
 * To ensure proper class loading, it is suggested that this class resides within
 * acp-ejb project
 *
 * Minimum annotations are @Enity and @Id
 * 
 * @Id can be used on any field of your choice, @Id field can have duplicate keys (for example, deptNo)
 * 
 * Optional @Column annotation can be used to map queried column names to field names
 * @Column(name="column_name") where column_name is case in-sensitive
 * 
 * @author Jonathan Luo
 * Created 12/18/14
 */

@Entity
public class DeptManager {

//	@Column(name="emp_no")
//	Long empNo;
	protected Long employeeNo;

	@Column(name="birth_date")
	protected Date birthDate;

//	@Column(name="first_name")
	protected String firstName;

//	@Column(name="last_name")
	protected String lastName;

	@Column(name="hire_date")
	protected Date hireDate;

	@Id
	@Column(name="dept_no")
	protected String deptNo;

	public Long getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(Long empNo) {
		this.employeeNo = empNo;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("empNo: ").append(employeeNo);
		sb.append(", birthDate: ").append(birthDate);
		sb.append(", firstName: ").append(firstName);
		sb.append(", lastName: ").append(lastName);
		sb.append(", hireDate: ").append(hireDate);
		sb.append(", deptNo: ").append(deptNo);
		
		return sb.toString();
	}
}