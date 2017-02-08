package com.apress.projpa2.chap9;

import java.util.*;
import javax.persistence.*;

import com.apress.projpa2.ProJPAUtil;

import examples.model.Employee;

/**
 * Pro JPA 2 Chapter 9
 * Listing 9-1.  Employee Search Using Dynamic JP QL Query - p.229
 * public class SearchService
 */
public class JPQLQuery {
 
    EntityManager em;

    public JPQLQuery() {
        String unitName = "jpqlExamples"; // = args[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        em = emf.createEntityManager();
    }

    /**
     * Compare CriteriaAPI#findEmployees
     * @param name
     * @param deptName
     * @param projectName
     * @param city
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Employee> findEmployees(String name, String deptName, String projectName, String city) {

        StringBuffer query = new StringBuffer();
        query.append("SELECT DISTINCT e ");
        query.append("FROM Employee e LEFT JOIN e.projects p ");

        query.append("WHERE ");
        List<String> criteria = new ArrayList<String>();
        if (name != null) { criteria.add("e.name = :name"); }
        if (deptName != null) { criteria.add("e.dept.name = :dept"); }
        if (projectName != null) { criteria.add("p.name = :project"); }
        if (city != null) { criteria.add("e.address.city = :city"); }
        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        }
        for (int i = 0; i < criteria.size(); i++) {
            if (i > 0) { query.append(" AND "); }
            query.append(criteria.get(i));
        }

        Query q = em.createQuery(query.toString());
        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("dept", deptName); }
        if (projectName != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return (List<Employee>)q.getResultList();
    }

    public static void main(String[] args) throws Exception {
        JPQLQuery test = new JPQLQuery();
        String name = "John";
        String deptName = null;
        String projectName = null;
        String city = null;
        List<Employee> retList = test.findEmployees(name, deptName, projectName, city);
        ProJPAUtil.printResult(retList);
    }
}