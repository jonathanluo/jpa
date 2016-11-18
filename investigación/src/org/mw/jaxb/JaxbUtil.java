package org.mw.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


/**
 * http://fahdshariff.blogspot.com/2013/05/jaxb-marshallingunmarshalling-example.html
 *
 */
public class JaxbUtil<T> {

    /**
     * Marshal: Convert a Java object to XML string
     *
     * Example:
     *   Employee emp=new Employee(102,"Jason Smith", 49997);
     *   JaxbUtil jaxbUtil = new JaxbUtil<Employee>();
     *   String xmlString = jaxbUtil.marshal(emp);
     * @param t
     * @return
     * @throws JAXBException
     */
    public String marshal(T t) throws JAXBException {
        final Marshaller m = JAXBContext.newInstance(t.getClass()).createMarshaller();
        m.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        final StringWriter w = new StringWriter();
        m.marshal(t, w);
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
        return (T) JAXBContext.newInstance(clazz)
                   .createUnmarshaller()
                   .unmarshal(new StringReader(xml));
    }

	public static <T> T unmarshal(final String xml, Class<T> t) throws JAXBException, XMLStreamException {
        JAXBContext jaxbContext = JAXBContext.newInstance(t);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        XMLInputFactory xmlif = XMLInputFactory.newFactory();
        xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        xmlif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new StringReader(xml));
        return t.cast(unmarshaller.unmarshal(xmlsr));
    }

	public static <T> T unmarshal(Class<T> t, Class subClazz, final String xml) throws JAXBException, XMLStreamException {
        JAXBContext jaxbContext = JAXBContext.newInstance(t, subClazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        XMLInputFactory xmlif = XMLInputFactory.newFactory();
        xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        xmlif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new StringReader(xml));
        return t.cast(unmarshaller.unmarshal(xmlsr));
    }
}
