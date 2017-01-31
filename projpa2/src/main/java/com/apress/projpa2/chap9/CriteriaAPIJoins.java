package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.lang.builder.*;

import examples.model.Department;
import examples.model.Employee;
import examples.model.EmployeeInfo;
import examples.model.Phone;

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
public class CriteriaAPIJoins {

    EntityManager em;

    public CriteriaAPIJoins() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * p.240 Inner and Outer Joins
     * search MapJoin against *.java to get the source code
     */
    public Collection<Object> executeQueryUsingMetamodel() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> c = cb.createQuery();
        Root<Employee> emp = c.from(Employee.class);

        EntityType<Employee> emp_ = emp.getModel();

        MapJoin<Employee,String,Phone> phone = emp.join(emp_.getMap("phones", String.class, Phone.class));
        c.multiselect(emp.get(emp_.getSingularAttribute("name", String.class)),
                              phone.key(), 
                              phone.value());
        TypedQuery<Object> q = em.createQuery(c);
        return q.getResultList();
    }


    /**
     * p.241 Fetch Joins
     * This demonstrates fetch joins of single-valued relationships:
     * SELECT e FROM Employee e JOIN FETCH e.address
     * Note that when using the fetch() method the return type is Fetch, not Join. Fetch objects are not paths and may
     * not be extended or referenced anywhere else in the query.
     * element count = 11, three employees have null ADDRESS_ID
     */
    public Collection<Employee> fetchJoin() {
        System.out.println("p.241 Fetch Joins - fetchJoin");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        emp.fetch("address");
        c.select(emp);

        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     * p.241 Fetch Joins #2
     * Collection-valued fetch joins are also supported and use similar syntax. In the following example, we demonstrate
     * how to fetch the Phone entities associated with each Employee, using an outer join to prevent Employee entities 
     * from being skipped if they don’t have any associated Phone entities. We use the distinct() setting to remove
     * any duplicates.
     * element count = 14
     */
    public Collection<Employee> fetchJoin2() {
        System.out.println("p.241 Fetch Joins - fetchJoin2");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        emp.fetch("phones", JoinType.LEFT);
        c.select(emp)
        .distinct(true); 
        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        CriteriaAPIJoins test = new CriteriaAPIJoins();
//        test.printResult(test.executeQueryUsingMetamodel());
        test.printResult(test.fetchJoin());
        test.printResult(test.fetchJoin2());
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
        } else { // list goes here
            System.out.print(ReflectionToStringBuilder.toString(result, ToStringStyle.SHORT_PREFIX_STYLE));
        }
        System.out.println();
        System.out.println();
    }
}