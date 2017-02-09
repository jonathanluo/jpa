package com.apress.projpa2.chap9;

import java.util.*;
import java.util.logging.Logger;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;
import examples.model.Phone;

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
public class SubqueryPhone {

    private static final Logger LOGGER = Logger.getLogger(SubqueryProject.class.getName());

    EntityManager em;

    public SubqueryPhone() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * TODO - sort by fields in Phones or Projects
     */
    public List<Employee> findEmployees_Subquery_Phone(String name, String projectName) {
        System.out.println("findEmployees_Subquery_correlate_unique('" + name + "%', " + projectName + ") - jon");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
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
        Join<Employee,Phone> phone = null; 
        Root<Employee> sqEmp = null;
        if (projectName != null) {
            Subquery<Phone> sq = c.subquery(Phone.class);
            sqEmp = sq.correlate(emp); // replace Root<Project> project = sq.from(Project.class);
//          Join<Employee,Project> project = sqEmp.join("projects"); // or
//            Join<Employee,Project> project = sqEmp.join("projects", JoinType.LEFT); // or
            phone = sqEmp.join("phones", JoinType.INNER);
            sq.select(phone);
//          sq.where(cb.like(phone.get("name"), cb.parameter(String.class,"project")));
            criteria.add(cb.exists(sq));
        }

        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }
        /*
        c.orderBy(cb.asc(phone.get("number"))); // error
        QueryException Exception Description: The query has not been defined correctly, the expression builder is missing.  
        For sub and parallel queries ensure the queries builder is always on the left.
        Query: ReadAllQuery(referenceClass=Employee )
        */
        c.orderBy(cb.asc(emp.get("id")));//ok
//        c.orderBy(cb.asc(emp.get("name")));

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


    /**
     * Simulate audit UI sorting
     * c.orderBy(cb.asc(emp.get("id")));
     * SELECT t1.ID, t1.NAME, t1.SALARY, t1.STARTDATE, t1.DEPARTMENT_ID, t1.MANAGER_ID, t1.ADDRESS_ID
     * FROM PHONE t0, EMPLOYEE t1 WHERE (t0.NUMBER LIKE ? AND (t0.EMPLOYEE_ID = t1.ID)) ORDER BY t1.ID ASC
     * 
     * c.orderBy(cb.asc(emp.get("id")));
     * c.orderBy(cb.asc(join.get("number")));
     * SELECT t1.ID, t1.NAME, t1.SALARY, t1.STARTDATE, t1.DEPARTMENT_ID, t1.MANAGER_ID, t1.ADDRESS_ID FROM PHONE t0, 
     * EMPLOYEE t1 WHERE (t0.NUMBER LIKE ? AND (t0.EMPLOYEE_ID = t1.ID)) ORDER BY t0.NUMBER ASC
     * 
     * @param phoneType 'Home', 'Office', 'Cell', 'All'
     * @return
     */
    public List<Employee> findEmployees_join_Phone(String phoneType) {
        System.out.println("findEmployees_join_Phone('" + phoneType + "')");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class); // A root in a criteria query corresponds to an identification variable in JP QL
                                                     // Calls to the from() method are additive. Each call adds another root to the query
        c.select(emp);
        //c.distinct(true); // distinct no longer need by using sub query

        List<Predicate> criteria = new ArrayList<Predicate>();

        Join<Employee, Phone> join = emp.join("phones");
        Expression<String> path = join.get("number");
        criteria.add(cb.like(path, "%"));
        // filtered by phone type
        if (!"All".equals(phoneType)) {
            criteria.add(cb.like(join.get("type"), phoneType + "%"));
        }

        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        c.orderBy(cb.asc(emp.get("id")));
        c.orderBy(cb.asc(join.get("number")));
        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        SubqueryPhone test = new SubqueryPhone();
        ProJPAUtil.printResult(test.findEmployees_Subquery_Phone("", ""));
        ProJPAUtil.printResult(test.findEmployees_join_Phone("Home"));
        ProJPAUtil.printResult(test.findEmployees_join_Phone("Office"));
        ProJPAUtil.printResult(test.findEmployees_join_Phone("Cell"));
        ProJPAUtil.printResult(test.findEmployees_join_Phone("All"));
    }
}