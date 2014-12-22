package org.moonwave.jpa.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.moonwave.jpa.model.pojo.Employees4;


/**
 * EmployeeQuery
 *
 */
public class Employee4NativeQuery 
{
	
	private EntityManager em;
	private EntityManagerFactory emf;
	
	public void setUp() throws Exception{
		emf=Persistence.createEntityManagerFactory("jpa-mysql");
		em=emf.createEntityManager();
	}
	
	public void tearDown()throws Exception{
		em.close();
		emf.close();
	}

	public void testQuery(){
		Query query = em.createNativeQuery("select emp_no, first_name, last_name from employees", Employees4.class);
		query.setMaxResults(30);
		List<Object> list = query.getResultList();
		int i = 0;
		for (Object emp : list) {
			System.out.println(++i + ": " + emp.toString());
		}
	}
	
    public static void main( String[] args )
    {
    	Employee4NativeQuery test = new Employee4NativeQuery();
    	try {
    		test.setUp();
    		test.testQuery();
    		test.tearDown();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
