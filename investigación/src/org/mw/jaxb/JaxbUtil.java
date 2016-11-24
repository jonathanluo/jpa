package org.mw.jaxb;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;


/**
 * http://fahdshariff.blogspot.com/2013/05/jaxb-marshallingunmarshalling-example.html
 *
 */
public class JaxbUtil<T> {

    private static final Logger LOGGER = Logger.getLogger(JaxbUtil.class.getName());

    /**
     * Marshal: Convert a Java object to XML string
     *
     * Example:
     *   Employee emp=new Employee(102,"Jason Smith", 49997);
     *   JaxbUtil jaxbUtil = new JaxbUtil<Employee>();
     *   String xmlString = jaxbUtil.marshal(emp);
     * @param t
     * @return
     */
    public String marshal(T t) {
        if (t == null) {
            return null;
        }
        final StringWriter w = new StringWriter();
        try {
            final Marshaller m = JAXBContext.newInstance(t.getClass()).createMarshaller();
            m.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(t, w);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, null, e);
        }
        return w.toString();
    }

    /**
     * Unmarshal: convert a XML string into a Java object
     *
     * Example:
     *   String xmlString = FileUtil.getContent("employee.xml");
     *   JaxbUtil jaxbUtil = new JaxbUtil<Employee>();
     *   Employee e = (Employee) jaxbUtil.unmarshal(Employee.class, xmlString);
     * @param t
     * @param xml
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public T unmarshal(Class<T> clazz, final String xml) throws JAXBException {
        try {
            return (T) JAXBContext.newInstance(clazz)
                       .createUnmarshaller()
                       .unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static <T> T unmarshal(final String xml, Class<T> t) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XMLInputFactory xmlif = XMLInputFactory.newFactory();
            xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
            xmlif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new StringReader(xml));
            return t.cast(unmarshaller.unmarshal(xmlsr));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static <T> T unmarshal(Class<T> t, Class subClazz, final String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t, subClazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XMLInputFactory xmlif = XMLInputFactory.newFactory();
            xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
            xmlif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new StringReader(xml));
            return t.cast(unmarshaller.unmarshal(xmlsr));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, null, e);
        }
        return null;
    }
}
