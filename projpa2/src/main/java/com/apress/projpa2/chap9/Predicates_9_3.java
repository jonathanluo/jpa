package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;
import examples.model.Project;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 * See also Predicates_9_2
 *
 * Listing 9-3. Predicate Construction Using Conjunction    - p.244
 * 
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
public class Predicates_9_3 {

    EntityManager em;

    public Predicates_9_3() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * Listing 9-3. Predicate Construction Using Conjunction    - p.244
     *
     * Compare JPQLQuery#findEmployees
     *
     * The conjunction() and disjunction() methods of the CriteriaBuilder interface create Predicate objects that always
     * resolve to true and false respectively.
     *
     * @param name
     * @param deptName
     * @param projectName
     * @param city
     * @return
     */
    public List<Employee> findEmployees(String name, String deptName, String projectName, String city) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class); // c - criteria query definition
        Root<Employee> emp = c.from(Employee.class); // A root in a criteria query corresponds to an identification variable in JP QL
                                                     // Calls to the from() method are additive. Each call adds another root to the query
        c.select(emp);
        c.distinct(true);
        Join<Employee,Project> project = emp.join("projects", JoinType.LEFT);

        Predicate criteria = cb.conjunction();
        if (name != null) {
            // ParameterExpression created with the correct type that can be used in conditional expressions
            // The parameter method requires a class type (to set the type of the ParameterExpression object) and an optional name for use with named parameters
            ParameterExpression<String> p = cb.parameter(String.class, "name");
            criteria = cb.and(criteria, cb.equal(emp.get("name"), p));
        }
        if (deptName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "dept"); // create a named parameter with String data type
            criteria = cb.and(criteria, cb.equal(emp.get("dept").get("name"), p));
        }
        if (projectName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "project");
            criteria = cb.and(criteria, cb.equal(project.get("name"), p));
        }
        if (city != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "city");
            criteria = cb.and(criteria, cb.equal(emp.get("address").get("city"), p));
        }
        if (criteria.getExpressions().size() == 0) {
            throw new RuntimeException("no criteria");
        }

        TypedQuery<Employee> q = em.createQuery(c);
        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("dept", deptName); }
        if (projectName != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        Predicates_9_3 test = new Predicates_9_3();
        String name = "John";
        String deptName = null;
        String projectName = null;
        String city = null;
        List<Employee> retList = test.findEmployees(name, deptName, projectName, city);
        ProJPAUtil.printResult(retList);

        name = "Stephanie";
        retList = test.findEmployees(name, deptName, projectName, city);
        ProJPAUtil.printResult(retList);

        name = "Marcus";
        retList = test.findEmployees(name, deptName, projectName, city);
        ProJPAUtil.printResult(retList);
    }
}