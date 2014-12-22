package org.moonwave.jpa.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.moonwave.jpa.model.pojo.DeptManagerLong;


/**
 * Department Manager Native Query
 *
 */
public class DeptManagerLongNativeQuery 
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

	/**
	 * Native query can have more fields or less fields than mapped entity (DeptManager.class)
	 * For those fields not matched, the value will be ignored.  
	 */
	@SuppressWarnings("unchecked")
	public void testQuery(){
//		Query query = em.createNativeQuery("select e.emp_no as employeeNO, e.birth_date, e.hire_date, e.first_name as FIRSTNAME, " + 
//					 "e.last_name as lastName, dm.dept_no from employees e, dept_manager dm where e.emp_no = dm.emp_no order by e.first_name", DeptManager.class);
		Query query = em.createNativeQuery("select e.emp_no as employeeNO, e.birth_date, e.hire_date, e.first_name as FIRSTNAME, " + 
				 "e.last_name as LastNAME, dm.dept_no from employees e, dept_manager dm where e.emp_no = dm.emp_no order by dm.dept_no", DeptManagerLong.class);
		query.setMaxResults(30);
		List<DeptManagerLong> list = (List<DeptManagerLong>) query.getResultList();
		int i = 0;
		for (Object emp : list) {
			System.out.println(++i + ": " +emp.toString());
		}
	}
	
    public static void main( String[] args )
    {
    	DeptManagerLongNativeQuery test = new DeptManagerLongNativeQuery();
    	try {
    		test.setUp();
    		test.testQuery();
    		test.tearDown();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
