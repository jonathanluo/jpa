package org.mw.java8.lambda;

/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section1
 * Lambda expressions address the bulkiness of anonymous inner classes by converting five lines of code into a single
 * statement. This simple horizontal solution solves the "vertical problem" presented by inner classes.
 *
 */
public class RunnableTest {
   public static void main(String[] args) {

     System.out.println("=== RunnableTest ===");

     // Anonymous Runnable
     Runnable r1 = new Runnable(){
       @Override
       public void run(){
         System.out.println("Hello world one!");
       }
     };

     // Lambda Runnable
     Runnable r2 = () -> System.out.println("Hello world two!");

     // Run em!
     r1.run();
     r2.run();
   }
}