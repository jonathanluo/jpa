package org.mw.generics;

/**
 * Type Inference
 * 
 * https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html
 * 
 * @param <X> type parameter X
 */

class MyClass<X> {   // X is class type parameter
    <T> MyClass(T t) { // T is formal type parameter for constructor
    	System.out.println(t);
    	System.out.println("exiting...");
    }
}

public class TypeInference<X> {
    <T> TypeInference(T t) {
        // ...
    }

    public static void main(String[] argv) {
		//Consider the following instantiation of the class MyClass:
		new MyClass<Integer>(""); // X - Integer, T - String
		MyClass<Integer> myObject = new MyClass<>("test"); // use type inference - Java 7 or later 
    }
}

