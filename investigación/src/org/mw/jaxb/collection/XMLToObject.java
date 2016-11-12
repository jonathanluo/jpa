package org.mw.jaxb.collection;

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
            File file = new File("question.xml");
            FileUtil.printFile("question.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Question e=(Question) jaxbUnmarshaller.unmarshal(file);

            System.out.println(e);

        } catch (JAXBException e) {
            e.printStackTrace(); 
        }
    }
}  