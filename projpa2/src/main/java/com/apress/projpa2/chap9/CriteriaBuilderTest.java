package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.builder.*;

import examples.model.Employee;

/**
 * Pro JPA 2 Chapter 9 p.228 
 */
public class CriteriaBuilderTest {
 
    public void simpleQuery() throws Exception {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        EntityManager em = emf.createEntityManager();
 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp).where(cb.equal(emp.get("name"), "John"));

        // run search query
        TypedQuery<Employee> query = em.createQuery(c);
        List<Employee> retList = query.getResultList();
        printResult(retList);
    }

    public void pathExpression() throws Exception {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        EntityManager em = emf.createEntityManager();
 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp).where(cb.equal(emp.get("name"), "John"));

        // run search query

        // http://www.objectdb.com/java/jpa/query/jpql/string
        // http://www.objectdb.com/java/jpa/query/jpql/path#Navigation_through_Path_Expressions_
        // Path Expressions
        //   FROM variable, 
        //,Type Expressions 
        List<Expression<?>> elike = new ArrayList<>();
        Expression<String> path = emp.get("name");
        Expression<String> param = cb.parameter(String.class);
         
          // str [NOT] LIKE pattern
        Predicate l1 = cb.like(path, param);
        Predicate l2 = cb.like(path, "%Jo%");
        // run search query
        TypedQuery<Employee> query = em.createQuery(c);
        List<Employee> retList = query.getResultList();
        printResult(retList);

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
        CriteriaBuilderTest test = new CriteriaBuilderTest();
        test.simpleQuery();
        test.pathExpression();
    }
}