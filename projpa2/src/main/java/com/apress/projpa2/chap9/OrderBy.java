package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Department;
import examples.model.Employee;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 *
 */
public class OrderBy {

    EntityManager em;

    public OrderBy() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * p.253 The ORDER BY clause
     *  SELECT d.name, e.name
        FROM Employee e JOIN e.dept d
        ORDER BY d.name DESC, e.name 
     */
    public List<Tuple> orderBy() {

        System.out.println("orderBy()");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> c = cb.createQuery(Tuple.class);
        Root<Employee> emp = c.from(Employee.class);
        Join<Employee,Department> dept = emp.join("department");
        c.multiselect(dept.get("name"), emp.get("name"));
        c.orderBy(cb.desc(dept.get("name")), cb.asc(emp.get("name")));

        TypedQuery<Tuple> q = em.createQuery(c);
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        OrderBy test = new OrderBy();
        ProJPAUtil.printResult(test.orderBy());
    }
}