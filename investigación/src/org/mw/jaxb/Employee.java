package org.mw.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * http://www.javatpoint.com/jaxb-tutorial
 *
 *   <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 *   <employee id="1">
 *       <name>Vimal Jaiswal</name>
 *       <salary>50000.0</salary>
 *   </employee>
 *
 */
@XmlRootElement  
public class Employee {
    private int id;
    private String name;
    private float salary;

    public Employee() {}
    public Employee(int id, String name, float salary) {
        super();
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
