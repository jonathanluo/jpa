package com.apress.projpa2.chap9;

import java.util.*;
import java.util.logging.Logger;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;
import examples.model.Project;

/**
 * Pro JPA 2 Chapter 9 Criteria API

    Root<Employee> emp = c.from(Employee.class);
    c.select(emp);
    Join<Employee,Project> project = emp.join("projects", JoinType.LEFT);

    Subquery<Integer> sq = c.subquery(Integer.class);
    Root<Project> project = sq.from(Project.class);
    Join<Project,Employee> sqEmp = project.join("employees");

    Subquery<Project> sq = c.subquery(Project.class);
    Root<Project> project = sq.from(Project.class);
    Join<Project,Employee> sqEmp = project.join("employees");

    Root object obtained by from() method only accepts a persistent class type
    Use correlate in sub query

    Subquery<Project> sq = c.subquery(Project.class);
    Root<Employee> sqEmp = sq.correlate(emp); // replace Root<Project> project = sq.from(Project.class);
    Join<Employee,Project> project = sqEmp.join("projects");

    CriteriaQuery<Project> c = cb.createQuery(Project.class);
    Root<Project> project = c.from(Project.class);
    Join<Project,Employee> emp = project.join("employees");
    Subquery<Double> sq = c.subquery(Double.class);
    Join<Project,Employee> sqEmp = sq.correlate(emp);
    Join<Employee,Employee> directs = sqEmp.join("directs");
 *
 */
public class SubqueryTest {

    private static final Logger LOGGER = Logger.getLogger(Predicates_9_5_subquery.class.getName());

    EntityManager em;

    public SubqueryTest() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    public List<Employee> findEmployees_Subquery_correlate_unique(String name, String projectName) {
        System.out.println("findEmployees_Subquery_correlate_unique('" + name + "%', " + projectName + ") - jon");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class); // c - criteria query definition
        Root<Employee> emp = c.from(Employee.class); // A root in a criteria query corresponds to an identification variable in JP QL
                                                     // Calls to the from() method are additive. Each call adds another root to the query
        c.select(emp);
        //c.distinct(true); // distinct no longer need by using sub query

        List<Predicate> criteria = new ArrayList<Predicate>();
        if (name != null) {
            ParameterExpression<String> pe1 = cb.parameter(String.class, "nameUpper");
            ParameterExpression<String> pe2 = cb.parameter(String.class, "nameLower");
            Predicate p1 = cb.like(emp.get("name"), pe1);
            Predicate p2 = cb.like(emp.get("name"), pe2);
            criteria.add(cb.or(p1, p2));
        }
        if (projectName != null) {
            /*
             * Root object obtained by from() method only accepts a persistent class type
             * Use correlate in sub query
             *
             * SELECT e FROM Employee e WHERE EXISTS 
             *      (SELECT p FROM e.projects p WHERE p.name = :name)
             */
            Subquery<Project> sq = c.subquery(Project.class);
            Root<Employee> sqEmp = sq.correlate(emp); // replace Root<Project> project = sq.from(Project.class);
            Join<Employee,Project> project = sqEmp.join("projects");
            sq.select(project)
              .where(cb.like(project.get("name"), cb.parameter(String.class,"project")));
            criteria.add(cb.exists(sq));
        }

        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Employee> q = em.createQuery(c);
        if (name != null) { 
            q.setParameter("nameUpper", name.toUpperCase() + "%");
            q.setParameter("nameLower", name.toLowerCase() + "%");
        }
        if (projectName != null) { 
            q.setParameter("project", projectName + "%");
        }
        return q.getResultList();
    }


    public static void main(String[] args) throws Exception {
        SubqueryTest test = new SubqueryTest();
        ProJPAUtil.printResult(test.findEmployees_Subquery_correlate_unique("", ""));
    }
}