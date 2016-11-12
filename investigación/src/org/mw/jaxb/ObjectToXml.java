package org.mw.jaxb;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * http://www.javatpoint.com/jaxb-tutorial
 *
 */
public class ObjectToXml {

    public static void main(String[] args) throws Exception {
        JAXBContext contextObj = JAXBContext.newInstance(Employee.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Employee emp1=new Employee(1, "Vimal Jaiswal", 50000);

        marshallerObj.marshal(emp1, new FileOutputStream("employee.xml"));

        // =====================================================================
        // http://fahdshariff.blogspot.com/2013/05/jaxb-marshallingunmarshalling-example.html
        Employee emp2=new Employee(102,"Joson Smith", 49997);
        JaxbUtil jaxbUtil = new JaxbUtil<Employee>();
        String xmlString = jaxbUtil.marshal(emp2);
        System.out.println( xmlString);
    }
}
