package org.mw.java8.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/examples/LambdaExamples01.zip
 */
public class Person {

    private String givenName;
    private String surName;
    private int age;
    private Gender gender;
    private String eMail;
    private String phone;
    private String address;

    public Person(String firstname, String lastname) {
        this.givenName = firstname;
        this.surName = lastname;
    }

    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public static List<Person>  createShortList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Usain", "Bolt"));
        list.add(new Person("Wayde", "VAN NIEKERK"));
        list.add(new Person("David", "RUDISHA"));
        list.add(new Person("NGENY", "NGENY"));
        list.add(new Person("Hicham", "GUERROUJ"));
        list.add(new Person("Kenenisa", "BEKELE"));
        list.add(new Person("Leonard", "KOMON"));
        list.add(new Person("Haile", "GEBRSELASSIE"));
        list.add(new Person("Zersenay", "TADESE"));
        list.add(new Person("Karl", "Lewis"));
        return list;
    }
    public void printName() {
        System.out.println(this.givenName + " " + surName);
    }
}
