package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 * See also Predicates_9_2
 *
 * Listing 9-4. Creating Parameter Expressions    - p.245

    six possible clauses to be used in a select query:
    SELECT, FROM, WHERE, ORDER BY, GROUP BY and HAVING

    Table 9-1.  Criteria API Select Query Clause Methods           p.234
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
public class Predicates_9_4 {

    EntityManager em;

    public Predicates_9_4() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * Listing 9-4. Creating Parameter Expressions    - p.245
     *
     * Compare JPQLQuery#findEmployees
     */
    public List<Employee> findEmployees(String name, String deptName, String projectName, String city) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class); // c - criteria query definition
        Root<Employee> emp = c.from(Employee.class); // A root in a criteria query corresponds to an identification variable in JP QL
                                                     // Calls to the from() method are additive. Each call adds another root to the query
        c.select(emp);
        ParameterExpression<String> deptParam = cb.parameter(String.class, "deptName");
        c.where(cb.equal(emp.get("department").get("name"), deptParam));

        TypedQuery<Employee> q = em.createQuery(c);
        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("deptName", deptName); }
        if (projectName != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return q.getResultList();
    }

    /**
     * Listing 9-4. Creating Parameter Expressions    - p.245
     * If the parameter will not be reused in other parts of the query, it can be embedded directly in the predicate expression
     * to make the overall query definition more concise. The following code revises the Listing 9-4 to use this technique:
     */
    public List<Employee> findEmployees2(String name, String deptName, String projectName, String city) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class); // c - criteria query definition
        Root<Employee> emp = c.from(Employee.class); // A root in a criteria query corresponds to an identification variable in JP QL
                                                     // Calls to the from() method are additive. Each call adds another root to the query
        c.select(emp)
        .where(cb.equal(emp.get("department").get("name"), cb.parameter(String.class, "deptName")));

        TypedQuery<Employee> q = em.createQuery(c);
        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("deptName", deptName); }
        if (projectName != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        Predicates_9_4 test = new Predicates_9_4();
        String name = "John";
        String deptName = "QA";
        String projectName = null;
        String city = null;
        List<Employee> retList = test.findEmployees(name, deptName, projectName, city);
        ProJPAUtil.printResult(retList);

        name = "Stephanie";
        retList = test.findEmployees(name, deptName, projectName, city);
        ProJPAUtil.printResult(retList);

        name = "Marcus";
        retList = test.findEmployees2(name, deptName, projectName, city);
        ProJPAUtil.printResult(retList);
    }
}