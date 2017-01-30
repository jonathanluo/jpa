package com.apress.projpa2.chap8;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import org.apache.commons.lang.builder.*;

/**
 * Pro JPA 2 P. 195 
 * JP QL> SELECT p FROM Phone p WHERE p.type NOT IN ('office', 'home')
 * JP QL> SELECT d.name, AVG(e.salary) FROM Department d JOIN d.employees e GROUP BY d.name
 *
 * JP QL> SELECT e FROM Employee e
 * JP QL> SELECT d FROM Department d
 * JP QL> SELECT a FROM Address a
 * JP QL> SELECT d FROM DesignProject d
 * JP QL> SELECT p FROM Phone p
 * JP QL> SELECT p FROM Project p -- error
 * JP QL> SELECT q FROM QualityProject q
 *
 * JP QL> SELECT a.* FROM Address a -- error
 * JP QL> SELECT * FROM Address -- error
 * JP QL> quit
 */
public class QueryTester {
 
    public static void main(String[] args) throws Exception {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf =
        Persistence.createEntityManagerFactory(unitName);
        EntityManager em = emf.createEntityManager();
        BufferedReader reader =
        new BufferedReader(new InputStreamReader(System.in));
     
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