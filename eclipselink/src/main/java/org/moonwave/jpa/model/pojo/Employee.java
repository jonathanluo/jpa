package org.moonwave.jpa.model.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A POJO used as pseudo entity to retrieve result from native query
 * This entity does not map to any physical table
 *
 * Minimum annotations are @Enity and @Id
 * 
 * @Id can be used on any field of your choice, @Id field can have duplicate keys nut not null
 * 
 * Optional @Column annotation can be used to map queried column names to field names
 * @Column(name="column_name") where column_name is case in-sensitive
 * 
 */

@Entity
public class Employee {
	
	@Id
	@Column(name="emp_no")
	protected Long empNo;

	protected String firstName;
	protected String lastName;
	protected String title;
	
	public Long getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("empNo: ").append(empNo);
		sb.append(", firstName: ").append(firstName);
		sb.append(", lastName: ").append(lastName);
		sb.append(", title: ").append(title);
		
		return sb.toString();
	}
}
