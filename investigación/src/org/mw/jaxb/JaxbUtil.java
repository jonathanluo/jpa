package org.mw.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * http://fahdshariff.blogspot.com/2013/05/jaxb-marshallingunmarshalling-example.html
 *
 */
public class JaxbUtil<T> {

    /**
     * Unmarshalling: To convert an XML string into an object of class Book:
     *
     * @param t
     * @param xml
     * @return
     * @throws JAXBException
     */
	@SuppressWarnings("unchecked")
    public T unmarshal(Class clazz, final String xml) throws JAXBException {
        return (T) JAXBContext.newInstance(clazz)
                   .createUnmarshaller()
                   .unmarshal(new StringReader(xml));
    }

    /**
     * Marshalling: To convert a Book object into an XML string:
     *
     * @param t
     * @return
     * @throws JAXBException
     */
    public String marshal(T t) throws JAXBException {
        final Marshaller m = JAXBContext.newInstance(t.getClass()).createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        final StringWriter w = new StringWriter();
        m.marshal(t, w);
        return w.toString();
    }
}
