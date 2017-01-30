package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.builder.*;

import examples.model.Department;
import examples.model.Employee;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 * Data created using projpa2/src/main/resources/examples/Chapter8/jpqlExamples/etc/sql/db.sql
 
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
    | 14 | John      |  51500 | 1999-07-26 |          2 |             2 |          9 |
    +----+-----------+--------+------------+------------+---------------+------------+

    six possible clauses to be used in a select query:
    SELECT, FROM, WHERE, ORDER BY, GROUP BY and HAVING

    Table 9-1.  Criteria API Select Query Clause Methods
    JP QL Clause    Criteria API Interface  Method
    SELECT          CriteriaQuery           select()
                    Subquery                select()
    FROM            AbstractQuery           from()
    WHERE           AbstractQuery           where()
    ORDER BY        CriteriaQuery           orderBy()
    GROUP BY        AbstractQuery           groupBy()
    HAVING          AbstractQuery           having()
 *
 */
public class CriteriaAPIExamples {

    EntityManager em;

    public CriteriaAPIExamples() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * p.235 Query Roots 
     */
    public List<Department> findDepartments() {

        // Calls to the from() method are additive. Each call adds another root to the query, resulting in a Cartesian
        // product when more than one root is defined if no further constraints are applied in the WHERE clause.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Department> c = cb.createQuery(Department.class);
        Root<Department> dept = c.from(Department.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(dept)
        .distinct(true)
        .where(cb.equal(dept, emp.get("department")));

        TypedQuery<Department> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     * p. 236 Path Expressions
     */
    public List<Employee> findEmployees() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp)
        .where(cb.equal(emp.get("address").get("city"), "New York"));

        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     * p. 237 SELECT clauses
     * select employee names including duplicates
     */
    public List<String> findEmployeeNames() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> c = cb.createQuery(String.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp.<String>get("name"));
        TypedQuery<String> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     * p. 237 SELECT clauses
     * select employee names excluding duplicates
     */
    public List<String> findEmployeeNamesUnique() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> c = cb.createQuery(String.class);
        Root<Employee> emp = c.from(Employee.class);
        //c.select(emp.<String>get("name")).distinct(true); // or
        c.select(emp.<String>get("name"));
        c.distinct(true);
        TypedQuery<String> q = em.createQuery(c);
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        CriteriaAPIExamples test = new CriteriaAPIExamples();
        test.printResult(test.findDepartments());
        test.printResult(test.findEmployees());
        test.printResult(test.findEmployeeNames());
        test.printResult(test.findEmployeeNamesUnique());
        System.out.print("");
    }

    // ================================================================================================= private methods

    private void printResult(Object result) throws Exception {
        if (result == null) {
            System.out.print("NULL");
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                printResult(row[i]);
            }
            System.out.print("]");
        } else if (result instanceof Long ||
            result instanceof Double ||
            result instanceof String) {
            System.out.print(result.getClass().getName() + ": " + result);
        } else {
            System.out.print(ReflectionToStringBuilder.toString(result,
            ToStringStyle.SHORT_PREFIX_STYLE));
        }
        System.out.println();
    }
}