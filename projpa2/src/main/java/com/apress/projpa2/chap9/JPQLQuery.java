package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;

import org.apache.commons.lang.builder.*;

import examples.model.Employee;

/**
 * Pro JPA 2 Chapter 9
 * Listing 9-1.  Employee Search Using Dynamic JP QL Query - p.229
 * public class SearchService

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
    13 rows in set (0.00 sec)

 */
public class JPQLQuery {
 
    EntityManager em;

    public JPQLQuery() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * Compare CriteriaAPI#findEmployees
     * @param name
     * @param deptName
     * @param projectName
     * @param city
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Employee> findEmployees(String name, String deptName, String projectName, String city) {

        StringBuffer query = new StringBuffer();
        query.append("SELECT DISTINCT e ");
        query.append("FROM Employee e LEFT JOIN e.projects p ");

        query.append("WHERE ");
        List<String> criteria = new ArrayList<String>();
        if (name != null) { criteria.add("e.name = :name"); }
        if (deptName != null) { criteria.add("e.dept.name = :dept"); }
        if (projectName != null) { criteria.add("p.name = :project"); }
        if (city != null) { criteria.add("e.address.city = :city"); }
        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        }
        for (int i = 0; i < criteria.size(); i++) {
            if (i > 0) { query.append(" AND "); }
            query.append(criteria.get(i));
        }

        Query q = em.createQuery(query.toString());
        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("dept", deptName); }
        if (projectName != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return (List<Employee>)q.getResultList();
    }
    
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

    public static void main(String[] args) throws Exception {
        JPQLQuery test = new JPQLQuery();
        String name = "John";
        String deptName = null;
        String projectName = null;
        String city = null;
        List<Employee> retList = test.findEmployees(name, deptName, projectName, city);
        test.printResult(retList);
    }
}