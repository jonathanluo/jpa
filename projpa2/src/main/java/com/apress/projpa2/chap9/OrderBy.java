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
    mysql> use projpa2
    mysql> select * from EMPLOYEE;
    +----+-----------+--------+------------+------------+---------------+------------+
    | ID | NAME      | SALARY | STARTDATE  | ADDRESS_ID | DEPARTMENT_ID | MANAGER_ID |
    +----+-----------+--------+------------+------------+---------------+------------+
    |  1 | John      |  55000 | 2001-01-01 |          1 |             2 |          9 |
    |  2 | Rob       |  53000 | 2001-05-23 |          2 |             2 |          9 |
    |  3 | Peter     |  40000 | 2002-08-06 |          3 |             2 |          9 |
    |  4 | Frank     |  41000 | 2003-02-17 |          4 |             1 |         10 |
    |  5 | Scott     |  60000 | 2004-11-14 |          5 |             1 |         10 |
    |  6 | Sue       |  62000 | 2005-08-18 |          6 |             1 |         10 |
    |  7 | Stephanie |  54000 | 2006-06-07 |          7 |             1 |         10 |
    |  8 | Jennifer  |  45000 | 1999-08-11 |          8 |             1 |       NULL |
    |  9 | Sarah     |  52000 | 2002-04-26 |          9 |             2 |         10 |
    | 10 | Joan      |  59000 | 2003-04-16 |         10 |             1 |       NULL |
    | 11 | Marcus    |  35000 | 1995-07-22 |       NULL |          NULL |       NULL |
    | 12 | Joe       |  36000 | 1995-07-22 |       NULL |             3 |         11 |
    | 13 | Jack      |  43000 | 1995-07-22 |       NULL |             3 |       NULL |
    +----+-----------+--------+------------+------------+---------------+------------+

    mysql> select * from DEPARTMENT;
    +----+-------------+
    | ID | NAME        |
    +----+-------------+
    |  1 | Engineering |
    |  2 | QA          |
    |  3 | Accounting  |
    |  4 | CAEngOtt    |
    |  5 | USEngCal    |
    |  6 | CADocOtt    |
    |  7 | QA_East     |
    |  8 | QANorth     |
    +----+-------------+

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