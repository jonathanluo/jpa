package org.mw.jaxb.vogella;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * http://www.vogella.com/tutorials/JAXB/article.html
 *
 */
//This statement means that class "Bookstore.java" is the root-element of our example
@XmlRootElement(namespace = "org.mw.jaxb.vogella")
public class Bookstore {

    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "bookList")
    // XmlElement sets the name of the entities

    @XmlElement(name = "book")
    private ArrayList<Book> bookList;

    private String name;
    private String location;

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBooksList() {
        return bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}