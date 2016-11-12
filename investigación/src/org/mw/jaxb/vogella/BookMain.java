package org.mw.jaxb.vogella;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * http://www.vogella.com/tutorials/JAXB/article.html
 *
 */
public class BookMain {

    private static final String BOOKSTORE_XML = "./bookstore-jaxb.xml";

    public static void main(String[] args) throws JAXBException, IOException {

        ArrayList<Book> bookList = new ArrayList<Book>();

        // create books
        Book book1 = new Book();
        book1.setIsbn("978-0060554736");
        book1.setName("The Game");
        book1.setAuthor("Neil Strauss");
        book1.setPublisher("Harpercollins");
        bookList.add(book1);

        Book book2 = new Book();
        book2.setIsbn("978-3832180577");
        book2.setName("Feuchtgebiete");
        book2.setAuthor("Charlotte Roche");
        book2.setPublisher("Dumont Buchverlag");
        bookList.add(book2);

        // create bookstore, assigning book
        Bookstore bookstore = new Bookstore();
        bookstore.setName("Fraport Bookstore");
        bookstore.setLocation("Frankfurt Airport");
        bookstore.setBookList(bookList);

        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(Bookstore.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        System.out.println("===== m.marshal(bookstore, System.out) =====");
        m.marshal(bookstore, System.out);

        // http://stackoverflow.com/questions/26959343/convert-java-object-to-xml-string
        StringWriter sw = new StringWriter();
        m.marshal(bookstore, sw);
        String xmlString = sw.toString();
        System.out.println("\n===== from xmlString =====");
        System.out.println(xmlString);

        // Write to File
        m.marshal(bookstore, new File(BOOKSTORE_XML));

        // =====================================================================
        // get variables from our xml file, created before
        System.out.println();
        System.out.println("Output from our XML File: ");
        Unmarshaller um = context.createUnmarshaller();
        Bookstore bookstore2 = (Bookstore) um.unmarshal(new FileReader(BOOKSTORE_XML));

        // http://stackoverflow.com/questions/5458833/use-jaxb-to-create-object-from-xml-string
        Bookstore bookstore3 = (Bookstore) um.unmarshal(new StringReader(xmlString));
        Bookstore bookstore4 = JAXB.unmarshal(new StringReader(xmlString), Bookstore.class);

        System.out.println("\nbookstore2: ");
        ArrayList<Book> list = bookstore2.getBooksList();
        for (Book book : list) {
            System.out.println("Book: " + book.getName() + " from " + book.getAuthor());
        }
        System.out.println("\nbookstore3: ");
        for (Book book : bookstore3.getBooksList()) {
            System.out.println("Book: " + book.getName() + " from " + book.getAuthor());
        }
        System.out.println("\nbookstore4: ");
        for (Book book : bookstore4.getBooksList()) {
            System.out.println("Book: " + book.getName() + " from " + book.getAuthor());
        }
        
    }
}