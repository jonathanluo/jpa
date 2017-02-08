package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;
import examples.model.Project;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 */
public class GroupBy {

    EntityManager em;

    public GroupBy() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * p.254 The GROUP BY and HAVING clauses
     *  SELECT e, COUNT(p)
        FROM Employee e JOIN e.projects p
        GROUP BY e
        HAVING COUNT(p) >= 2
     */
    public List<Object[]> groupBy() {

        System.out.println("orderBy()");
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<Employee> emp = c.from(Employee.class);
        Join<Employee,Project> project = emp.join("projects");
        c.multiselect(emp, cb.count(project))
         .groupBy(emp)
         .having(cb.ge(cb.count(project),2));

        TypedQuery<Object[]> q = em.createQuery(c);
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        GroupBy test = new GroupBy();
        ProJPAUtil.printResult(test.groupBy());
    }
}