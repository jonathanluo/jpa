package examples.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**

    http://stackoverflow.com/questions/37243159/mappedby-in-bi-directional-manytomany-what-is-the-reason

    While this is saying that each Entity "owns" its ManyToMany relationship, the extra join table is redundant in the 
    typical use case, and the Javadoc says you need a mappedBy annotation. If I decide to have SideA "own" the 
    relationship, then I add the mappedBy= element to the SideB entity to specify that it doesn't own the relationship:

    @Entity
    public class SideA {    // SideA is owner side
        @ManyToMany
        Set<SideB> sidebs;
    }
    @Entity
    public class SideB {    // SideB is inverse side
        @ManyToMany(mappedBy="sidebs")
        Set<SideA> sideas;
    }

 */
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy="employee") // Phone contains employee collection
    private Collection<Phone> phones = new ArrayList<Phone>(); // employee 1:<==>m: phones

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy="manager") // Employee contains manager field
    private Collection<Employee> directs = new ArrayList<Employee>(); // manager (owner side) 1:<==>m: employees (inverse side)

    /*
     http://stackoverflow.com/questions/14111607/manytomanymappedby-foo

     If the association is bidirectional, one side has to be the owner and one side has to be the inverse end (ie. it will
     be ignored when updating the relationship values in the association table).
     So, the side which has the mappedBy attribute is the inverse side. The side which doesn't have the mappedBy 
     attribute is the owner.
    */
    @ManyToMany(mappedBy="employees") // Project (owner side) contains employees collection (inverse side) 
    private Collection<Project> projects = new ArrayList<Project>(); // employees (inverse side) m:<==>m: projects (owner side)

    public int getId() {
        return id;
    }

    public void setId(int empNo) {
        this.id = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Collection<Phone> getPhones() {
        return phones;
    }
    
    public void addPhone(Phone phone) {
        if (!getPhones().contains(phone)) {
            getPhones().add(phone);
            if (phone.getEmployee() != null) {
                phone.getEmployee().getPhones().remove(phone);
            }
            phone.setEmployee(this);
        }
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        if (this.department != null) {
            this.department.getEmployees().remove(this);
        }
        this.department = department;
        this.department.getEmployees().add(this);
    }
    
    public Collection<Employee> getDirects() {
        return directs;
    }
    
    public void addDirect(Employee employee) {
        if (!getDirects().contains(employee)) {
            getDirects().add(employee);
            if (employee.getManager() != null) {
                employee.getManager().getDirects().remove(employee);
            }
            employee.setManager(this);
        }
    }
    
    public Employee getManager() {
        return manager;
    }
    
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Collection<Project> getProjects() {
        return projects;
    }
    
    public void addProject(Project project) {
        if (!getProjects().contains(project)) {
            getProjects().add(project);
        }
        if (!project.getEmployees().contains(this)) {
            project.getEmployees().add(this);
        }
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address; 
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary() +
               ", phones: " + getPhones() +
               ", managerNo: " + ((getManager() == null) ? null : getManager().getId()) +
               ", deptNo: " + ((getDepartment() == null) ? null : getDepartment().getId());
    }

}
