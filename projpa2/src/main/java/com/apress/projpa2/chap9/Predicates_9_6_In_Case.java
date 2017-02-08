package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Department;
import examples.model.DesignProject;
import examples.model.Employee;
import examples.model.Project;
import examples.model.QualityProject;

/**
 * Pro JPA 2 Chapter 9 Criteria API
 *
 * Listing 9-6. In Expressions    - p.249
 *
 */
public class Predicates_9_6_In_Case {

    EntityManager em;

    public Predicates_9_6_In_Case() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * Listing 9-6. In Expressions    - p.249
     *  SELECT e
        FROM Employee e
        WHERE e.address.state IN ('NY', 'CA')

        Note the chained invocation of the value() method in order to set multiple values into the IN expression.
        The argument to in() is the expression to search for against the list of values provided via the value() method.
     */
    public List<Employee> findEmployees(String city1, String city2) {

        System.out.println("findEmployees('" + city1 + "', '" + city2 + "')");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class); // c - criteria query definition
        Root<Employee> emp = c.from(Employee.class);

        c.select(emp)
         .where(cb.in(emp.get("address").get("state")).value(city1).value(city2));

        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     *  p.249
        In cases where there are a large number of value() calls to chain together that are all of the same type, the
        Expression interface offers a shortcut for creating IN expressions. The in() methods of this interface allow one or
        more values to be set in a single call.
     */
    public List<Employee> findEmployees2(String city1, String city2) {

        System.out.println("findEmployees2('" + city1 + "', '" + city2 + "')");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class); // c - criteria query definition
        Root<Employee> emp = c.from(Employee.class);

        c.select(emp)
         .where(emp.get("address").get("state").in(city1, city2));

        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     *  p.249
            SELECT e
            FROM Employee e
            WHERE e.department IN
            (SELECT DISTINCT d
            FROM Department d JOIN d.employees de JOIN de.project p
            WHERE p.name LIKE 'QA%')

            SELECT e FROM Employee e WHERE e.dept.id IN
                (SELECT DISTINCT d.id FROM Department d JOIN d.employees emp JOIN emp.projects empProj
                 WHERE empProj.name LIKE 'QA%'

        p.250 Listing 9-6. IN Expression Using a Subquery, code from below
        ~/projpa2/src/main/resources/examples/Chapter9/06-canonicalMetamodelQuery/src/model/examples/stateless/SearchService.java
        SearchService#getEmployeesUsingStringBasedQuery()
     */
    public List<Employee> findEmployees_9_6() {

        System.out.println("findEmployees_9_6()");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Integer> sq = c.subquery(Integer.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee,Project> project = dept.join("employees").join("projects"); //dept.join(Department_.employees).join(Employee_.projects);

        sq.select(dept.<Integer>get("id"))
          .distinct(true)
          .where(cb.like(project.<String>get("name"), "QA%"));

        c.select(emp)
         .where(cb.in(emp.get("department").get("id")).value(sq)); // dept --> department

        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
        ~/projpa2/src/main/resources/examples/Chapter9/06-canonicalMetamodelQuery/src/model/examples/stateless/SearchService.java
        SearchService#getEmployeesUsingCanonicalMetamodelQuery()
     */
    /*
    public List<Employee> getEmployeesUsingCanonicalMetamodelQuery() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Integer> sq = c.subquery(Integer.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee,Project> project = 
            dept.join(Department_.employees).join(Employee_.projects);
        sq.select(dept.get(Department_.id))
          .distinct(true)
          .where(cb.like(project.get(Project_.name), "QA%"));
        c.select(emp)
         .where(cb.in(emp.get(Employee_.dept).get(Department_.id)).value(sq));

        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }
    */

    /**
     *  p.250
        SELECT p.name,
        CASE WHEN TYPE(p) = DesignProject THEN 'Development'
             WHEN TYPE(p) = QualityProject THEN 'QA'
             ELSE 'Non-Development'
        END
        FROM Project p
        WHERE p.employees IS NOT EMPTY
    */
    public List<Object[]> findEmployees_Case() {

        System.out.println("findEmployees_Case()");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<Project> project = c.from(Project.class);
        c.multiselect(project.get("name"),
            cb.selectCase()
              .when(cb.equal(project.type(), DesignProject.class),  "Development")
              .when(cb.equal(project.type(), QualityProject.class), "QA")
              .otherwise("Non-Development"))
        .where(cb.isNotEmpty(project.<List<Employee>>get("employees")));

        TypedQuery<Object[]> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     *  p.251
        SELECT p.name,
        CASE TYPE(p)
            WHEN DesignProject THEN 'Development'
            WHEN QualityProject THEN 'QA'
            ELSE 'Non-Development'
        END
        FROM Project p
        WHERE p.employees IS NOT EMPTY
     */
    public List<Object[]> findEmployees_Case2() {
        System.out.println("findEmployees_Case2()");
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<Project> project = c.from(Project.class);
        c.multiselect(project.get("name"),
        cb.selectCase(project.type())
            .when(DesignProject.class, "Development")
            .when(QualityProject.class, "QA")
            .otherwise("Non-Development"))
        .where(cb.isNotEmpty(project.<List<Employee>>get("employees")));

        TypedQuery<Object[]> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     * Example using JP QL COALESCE expression    p.251
        SELECT COALESCE(d.name, d.id)
        FROM Department d
     */
    public List<Object> find_Coalesce(boolean case1) {
        System.out.println("findEmployees_Coalesce(" + case1 + ")");
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Object> c = cb.createQuery();
        Root<Department> dept = c.from(Department.class);
        if (case1) {
            c.select(cb.coalesce()
             .value(dept.get("name"))
             .value(dept.get("id")));
        } else {
            c.select(cb.coalesce(dept.get("name"), dept.get("id")));
        }
        TypedQuery<Object> q = em.createQuery(c);
        return q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        Predicates_9_6_In_Case test = new Predicates_9_6_In_Case();
        ProJPAUtil.printResult(test.findEmployees("NY", "CA"));
        ProJPAUtil.printResult(test.findEmployees("NY", "NJ"));
        ProJPAUtil.printResult(test.findEmployees2("NY", "NJ"));
        ProJPAUtil.printResult(test.findEmployees_9_6());
        ProJPAUtil.printResult(test.findEmployees_Case());
        ProJPAUtil.printResult(test.findEmployees_Case2());
        ProJPAUtil.printResult(test.find_Coalesce(true));
        ProJPAUtil.printResult(test.find_Coalesce(false));
    }
}