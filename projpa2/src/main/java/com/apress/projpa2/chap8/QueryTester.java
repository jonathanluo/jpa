package com.apress.projpa2.chap8;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import org.apache.commons.lang.builder.*;

/**
 * Pro JPA 2 Chapter 8 p.195 
 * JP QL> 
 * SELECT p FROM Phone p WHERE p.type NOT IN ('office', 'home')
 * SELECT d.name, AVG(e.salary) FROM Department d JOIN d.employees e GROUP BY d.name
 *
 * SELECT e FROM Employee e
 * SELECT d FROM Department d
 * SELECT OBJECT(d) FROM Department d
 * SELECT a FROM Address a
 * SELECT d FROM DesignProject d
 * SELECT p FROM Phone p
 * SELECT p FROM Project p -- error
 * SELECT q FROM QualityProject q
 * SELECT a.street, a.city FROM Address a
 * SELECT e.department FROM Employee e
 * SELECT DISTINCT e.department FROM Employee e -- error
 * SELECT e.name FROM Employee e
 *
 * SELECT e.name, e.salary, e.department.name FROM Employee e
 * -- p.200 Constructor Expressions, The result object type must be referred to by using the fully qualified name of the object.
 * SELECT NEW examples.model.EmployeeDetails(e.name, e.salary, e.department.name) FROM Employee e
 *
 * SELECT DISTINCT e.name FROM Employee e
 * SELECT a.* FROM Address a -- error
 * SELECT * FROM Address -- error
 * JP QL> quit
 *
 * P. 197
 * SELECT e FROM Employee e
 * As in SQL, it has been aliased to the identifier e. This aliased value is known as an 
 * identification variable. Unlike queries in SQL, where a table alias is optional, the 
 * use of identification variables is mandatory in JP QL
 */
public class QueryTester {
 
    public static void main(String[] args) throws Exception {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        EntityManager em = emf.createEntityManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;) {
            System.out.print("JP QL> ");
            String query = reader.readLine();
            if (query.equals("quit")) {
                break;
            }
            if (query.length() == 0) {
                continue;
            }

            try {
                List result = em.createQuery(query).getResultList();
                if (result.size() > 0) {
                    int count = 0;
                    for (Object o : result) {
                        System.out.print(++count + " ");
                        printResult(o);
                    }
                } else {
                    System.out.println("0 results returned");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printResult(Object result) throws Exception {
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