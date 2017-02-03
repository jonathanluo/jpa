package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Department;
import examples.model.Employee;
import examples.model.EmployeeInfo;

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
        System.out.println("p.235 Query Roots - findDepartments()");

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
        System.out.println("p. 236 Path Expressions - findEmployees()");

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
        System.out.println("p. 237 SELECT clauses - findEmployeeNames()");

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
        System.out.println("p. 237 SELECT clauses - findEmployeeNamesUnique()");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> c = cb.createQuery(String.class);
        Root<Employee> emp = c.from(Employee.class);
        //c.select(emp.<String>get("name")).distinct(true); // or
        c.select(emp.<String>get("name"));
        c.distinct(true);
        TypedQuery<String> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     * p. 237 Selecting Multiple Expressions - Tuple
     */
    public List<Tuple> tuple() {
        System.out.println("p. 237 Selecting Multiple Expressions - tuple()");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> c= cb.createTupleQuery();
        Root<Employee> emp = c.from(Employee.class);
        c.select(cb.tuple(emp.get("id"), emp.get("name"))); // select with tuple

        TypedQuery<Tuple> q = em.createQuery(c);
        List<Tuple> retList = q.getResultList();
        System.out.println("id\tname");
        System.out.println("==\t===========");
        for (Tuple item : retList) {
            Object id = item.get(0);
            Object name = item.get(1);
            System.out.println(id + "\t" + name );
        }
        System.out.println();
        return retList;
    }

    /**
     * p. 238 Selecting Multiple Expressions - multiselect
     */
    public List<Object[]> multiselect() {
        System.out.println("p. 238 Selecting Multiple Expressions - multiselect()");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<Employee> emp = c.from(Employee.class);
        c.multiselect(emp.get("id"), emp.get("name")); // multiselect, Object[] returned

        TypedQuery<Object[]> q = em.createQuery(c);
        List<Object[]> retList = q.getResultList();
        System.out.println("id\tname");
        System.out.println("==\t===========");
        for (Object[] item : retList) {
            Object id = item[0];
            Object name = item[1];
            System.out.println(id + "\t" + name );
        }
        System.out.println();
        return retList;
    }

    /**
     * p. 238 Selecting Multiple Expressions - multiselect w/ tuple
     */
    public List<Tuple> multiselectTuple() {
        System.out.println("p. 238 Selecting Multiple Expressions - multiselectTuple()");
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> c = cb.createTupleQuery();
        Root<Employee> emp = c.from(Employee.class);
        c.multiselect(emp.get("id"), emp.get("name")); // multiselect, implicit tuple returned

        TypedQuery<Tuple> q = em.createQuery(c);
        List<Tuple> retList = q.getResultList();
        System.out.println("id\tname");
        System.out.println("==\t===========");
        for (Tuple item : retList) {
            Object id = item.get(0);
            Object name = item.get(1);
            System.out.println(id + "\t" + name );
        }
        System.out.println();
        return retList;
    }

    /**
     * p. 238 Selecting Multiple Constructor Expressions
     */
    public List<EmployeeInfo> multiselectExpressions() {
        System.out.println("p. 238 Selecting Multiple Constructor Expressions - multiselectExpressions()");
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<EmployeeInfo> c = cb.createQuery(EmployeeInfo.class);
        Root<Employee> emp = c.from(Employee.class);
        c.multiselect(emp.get("id"), emp.get("name")); // <==> c.select(cb.construct(EmployeeInfo.class, emp.get("id"), emp.get("name")));

        TypedQuery<EmployeeInfo> q = em.createQuery(c);
        List<EmployeeInfo> retList = q.getResultList();
        System.out.println("id\tname");
        System.out.println("==\t===========");
        for (EmployeeInfo item : retList) {
            System.out.println(item);
        }
        System.out.println();
        return retList;
    }

    /**
     * p. 239 Selecting Multiple using Aliases
     */
    public List<Tuple> multiselectAlias() {
        System.out.println("p. 239 Selecting Multiple using Aliases - multiselectAlias()");
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> c= cb.createTupleQuery();
        Root<Employee> emp = c.from(Employee.class);
        c.multiselect(emp.get("id").alias("id"), emp.get("name").alias("fullName")); // alias

        TypedQuery<Tuple> q = em.createQuery(c);
        List<Tuple> retList = q.getResultList();
        System.out.println("id\tname");
        System.out.println("==\t===========");
        for (Tuple t : q.getResultList()) {
            Integer id = t.get("id", Integer.class);
            String fullName = t.get("fullName", String.class);
            System.out.println(id + "\t" + fullName );
        }
        System.out.println();
        return retList;
    }

    public static void main(String[] args) throws Exception {
        CriteriaAPIExamples test = new CriteriaAPIExamples();
        ProJPAUtil.printResult(test.findDepartments());
        ProJPAUtil.printResult(test.findEmployees());
        ProJPAUtil.printResult(test.findEmployeeNames());
        ProJPAUtil.printResult(test.findEmployeeNamesUnique());

        test.tuple();
        test.multiselect();
        test.multiselectTuple();
        test.multiselectExpressions();
        test.multiselectAlias();
        System.out.print("");
    }
}