package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 * Listing 9-2. Employee Search Using Criteria API    - p.229
 * See also Predicates_9_3
 
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
public class Predicates_Or {

    EntityManager em;

    public Predicates_Or() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * Listing 9-2. Employee Search Using Criteria API    p.230
     *
     * Compare JPQLQuery#findEmployees
     *
     * @param name
     * @param deptName
     * @param projectName
     * @param city
     * @return
     */
    public List<Employee> findEmployees(String name) {
        System.out.println("findEmployees('" + name + "%')");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class); // c - criteria query definition
        Root<Employee> emp = c.from(Employee.class); // A root in a criteria query corresponds to an identification variable in JP QL
                                                     // Calls to the from() method are additive. Each call adds another root to the query
        c.select(emp);
        c.distinct(true);

        List<Predicate> criteria = new ArrayList<Predicate>();

        if (name != null) {
            ParameterExpression<String> pe1 = cb.parameter(String.class, "nameUpper");
            ParameterExpression<String> pe2 = cb.parameter(String.class, "nameLower");
            Predicate p1 = cb.like(emp.get("name"), pe1);
            Predicate p2 = cb.like(emp.get("name"), pe2);
            criteria.add(cb.or(p1, p2));
        }

        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            // notice odd invocation of the and() method. Unfortunately, the designers of the Collection.toArray() method decided that,
            // in order to avoid casting the return type, an array to be populated should also be passed in as an argument or 
            // an empty array in the case where we want the collection to create the array for us
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Employee> q = em.createQuery(c);
        if (name != null) { 
            q.setParameter("nameUpper", name.toUpperCase() + "%");
            q.setParameter("nameLower", name.toLowerCase() + "%");
        }
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        Predicates_Or test = new Predicates_Or();
        String name = "Jo";
        List<Employee> retList = test.findEmployees(name);
        ProJPAUtil.printResult(retList);

        name = "St";
        retList = test.findEmployees(name);
        ProJPAUtil.printResult(retList);

        name = "Ma";
        retList = test.findEmployees(name);
        ProJPAUtil.printResult(retList);
    }
}