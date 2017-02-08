package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;
import examples.model.Phone;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 * Data created using projpa2/src/main/resources/examples/Chapter8/jpqlExamples/etc/sql/db.sql
 
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
        ProJPAUtil.printResult(test.fetchJoin());
        ProJPAUtil.printResult(test.fetchJoin2());
        System.out.print("");
    }
}