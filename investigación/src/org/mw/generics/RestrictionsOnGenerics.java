package org.mw.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html
 */
public class RestrictionsOnGenerics {

    public static void main(String[] argv) {
        // 1) Cannot Instantiate Generic Types with Primitive Types
        Pair<Integer, String> p = new Pair<>(8, "a");
//      Pair<int, char> p = new Pair<>(8, 'a');  // compile-time error
        Pair<Integer, Character> p2 = new Pair<>(8, 'a');
        test1();
        test2();
    }

    // 2) Cannot Create Instances of Type Parameters
    public static <E> void append(List<E> list) {
        E elem = null;
//        elem = new E();  // compile-time error - cannot instantiate the type E
        list.add(elem);
    }

    // As a workaround, you can create an object of a type parameter through reflection:
    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();   // OK
        list.add(elem);
    }

    // You can invoke the append method as follows:
    public static void test1() {
        try {
            List<String> ls = new ArrayList<>();
            append(ls, String.class);
        } catch (Exception e) {
            
        }
    }

    // 4) Cannot Use Casts or instanceof with Parameterized Types
    // Because the Java compiler erases all type parameters in generic code, you cannot verify which parameterized type 
    // for a generic type is being used at runtime:
    public static <E> void rtti(List<E> list) {
    // Cannot perform instanceof check against parameterized type ArrayList<Integer>. Use the form ArrayList<?> instead 
    // since further generic type information will be erased at runtime
//        if (list instanceof ArrayList<Integer>) {  // compile-time error
//        }
    }

    //5) Cannot Create Arrays of Parameterized Types
    public static void test2() {
//      List<Integer>[] arrayOfLists = new ArrayList<Integer>[2];  // compile-time error
        List<Integer> Lists = new ArrayList<Integer>();

        // The following code illustrates what happens when different types are inserted into an array:
        try {
            Object[] strings = new String[2];
            strings[0] = "hi";   // OK
            strings[1] = 100;    // java.lang.ArrayStoreException: java.lang.Integer

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Pair<K, V> {

    private K key;
    private V value;

    // A class's static field is a class-level variable shared by all non-static objects of the class. Hence, static 
    // fields of type parameters are not allowed.

    // 3) Cannot Declare Static Fields Whose Types are Type Parameters
    // static K key1;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    // ...
}

//6) Cannot Create, Catch, or Throw Objects of Parameterized Types
//Extends Throwable indirectly
//class MathException<T> extends Exception { /* ... */ }    // compile-time error

//Extends Throwable directly
//class QueueFullException<T> extends Throwable { /* ... */ // compile-time error


/**
 * 7) Cannot Overload a Method Where the Formal Parameter Types of Each Overload Erase to the Same Raw Type
 * A class cannot have two overloaded methods that will have the same signature after type erasure.
 */

class Example {
    public void print(Set<String> strSet) { }
//    public void print(Set<Integer> intSet) { }
}
