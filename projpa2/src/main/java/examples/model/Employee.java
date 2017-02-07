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
        @ManyToMany(mappedBy="sidebs") // SideA does not own the relationship
        Set<SideA> sideas;
    }

    http://stackoverflow.com/questions/2749689/what-is-the-owning-side-in-an-orm-mapping
    You can imagine that the owning side is the entity that has the reference to the other one. In your excerpt, you have
    an one-to-one relationship. Since it's a symmetric relation, you'll end up having that if object A is in relation 
    with object B then also the vice-versa is true.

    This means that saving into object A a reference to object B and saving in object B a reference to object A will be 
    redundant: that's why you choose which object "owns" the other having the reference to it.

    When you have got an one-to-many relationship, the objects related to the "many" part will be the owning side, 
    otherwise you would have to store many references from a single object to a multitude. To avoid that, every object 
    in the second class will have a pointer to the single one they refer to (so they are the owning side).

    In conclusion the owning side is the entity that has the reference to the other.

    <Angular University>

    Why is the notion of a owning side necessary:

    The idea of a owning side of a bidirectional relation comes from the fact that in relational databases there are no 
    bidirectional relations like in the case of objects. In databases we only have unidirectional relations - foreign keys.

    What is the reason for the name 'owning side'?

    The owning side of the relation tracked by Hibernate is the side of the relation that owns the foreign key in the 
    database.

    What is the problem that the notion of owning side solves?

    Take an example of two entities mapped without declaring a owning side:

    @Entity
    @Table(name="PERSONS")
    public class Person {
        @OneToMany
        private List<IdDocument>  idDocuments;
    }

    @Entity
    @Table(name="ID_DOCUMENTS")
    public class IdDocument {
        @ManyToOne
        private Person person;
    }

    02/07/17
    http://www.javaworld.com/article/2077819/java-se/understanding-jpa-part-2-relationships-the-jpa-way.html
    Bidirectional one-to-one relationships

    Every relationship has two sides:

    The owning side is responsible for propagating the update of the relationship to the database. Usually this is the 
    side with the foreign key.

    The inverse side maps to the owning side.

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
    private Collection<Phone> phones = new ArrayList<Phone>(); // employee (inverse side w/ primary key) 1:<==>m: phones (owing side w/ foreign key)

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

     The owning side is responsible for propagating the update of the relationship to the database. Usually this is the 
     side with the foreign key. The following example, Project is the owning side with foreign key

     http://www.javaworld.com/article/2077819/java-se/understanding-jpa-part-2-relationships-the-jpa-way.html
     In short,
     mappedBy= side entity (Employee) is the inverse side, has the primary key
     The entity below mappedBy, Project, is the owner side, has the following key, is responsible for propagating the update of the relationship to the database 
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
