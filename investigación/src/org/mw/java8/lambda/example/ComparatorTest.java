package org.mw.java8.lambda.example;


import java.util.Collections;
import java.util.List;
import java.util.Comparator;

/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section1
 *
 */
public class ComparatorTest {

  public static void main(String[] args) {
   
    List<Person> personList = Person.createShortList();
  
     System.out.println("=== Sort with Inner Class ===");
    // Sort with Inner Class
    Collections.sort(personList, new Comparator<Person>(){
      public int compare(Person p1, Person p2){
        return p1.getSurName().compareTo(p2.getSurName());
      }
    });
    
     System.out.println("\n=== Sorted Asc SurName ===");
    for(Person p:personList){
      p.printName();
    }
    
     System.out.println("\n=== Sort Using Lambda instead ===");
    // Use Lambda instead
    
    // Print Asc
     System.out.println("\n=== Sorted Asc SurName ===");
    Collections.sort(personList, (Person p1, Person p2) -> p1.getSurName().compareTo(p2.getSurName()));

    for(Person p:personList){
      p.printName();
    }
    
    // Print Desc
     System.out.println("\n=== Sorted Desc SurName ===");
    Collections.sort(personList, (p1,  p2) -> p2.getSurName().compareTo(p1.getSurName()));

    for(Person p:personList){
      p.printName();
    }
    
  }
}