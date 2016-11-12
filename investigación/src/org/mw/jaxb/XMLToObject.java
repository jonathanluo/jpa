package org.mw.jaxb;

import java.io.File;  
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller;

import org.mw.util.FileUtil;  
  
/**
 * http://www.javatpoint.com/jaxb-tutorial
 *
 */
public class XMLToObject {

    public static void main(String[] args) {
        try {
            File file = new File("employee.xml");
            FileUtil.printFile("employee.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Employee e=(Employee) jaxbUnmarshaller.unmarshal(file);
            System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary());

            // =================================================================
            // http://fahdshariff.blogspot.com/2013/05/jaxb-marshallingunmarshalling-example.html
            String xmlString = FileUtil.getContent("employee.xml");
            JaxbUtil jaxbUtil = new JaxbUtil<Employee>();
            Employee e2 = (Employee) jaxbUtil.unmarshal(Employee.class, xmlString);
            System.out.println("\n============================================");
            System.out.println(e2.getId()+" "+e2.getName()+" "+e2.getSalary());
        } catch (JAXBException e) {
            e.printStackTrace(); 
        }
    }
}  