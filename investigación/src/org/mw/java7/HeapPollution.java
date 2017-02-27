package org.mw.java7;

import java.util.ArrayList;
import java.util.List;

/**
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/enhancements.html#javase7
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/non-reifiable-varargs.html
 * 
 * line 20, in detail, a heap pollution situation occurs when the List object l, whose static type is List<Number>, is 
 * assigned to another List object, ls, that has a different static type, List<String>. However, the compiler still allows 
 * this assignment. It must allow this assignment to preserve backwards compatibility with versions of Java SE that do not 
 * support generics. Because of type erasure, List<Number> and List<String> both become List. Consequently, the compiler 
 * allows the assignment of the object l, which has a raw type of List, to the object ls.
 * 
 * Furthermore, a heap pollution situation occurs when the l.add method is called. 
 */
public class HeapPollution {
    public static void main(String[] argv) {
        try {
            List l = new ArrayList<Number>();
            List<String> ls = l;       // unchecked warning         - heap pollution occurs
            l.add(0, new Integer(42)); // another unchecked warning - heap pollution occurs 
                                       // ls now holds list of Integers, just like l
            String s = ls.get(0);      // ClassCastException is thrown
        } catch (Exception e) {
            System.out.println(e.getMessage()); // java.lang.Integer cannot be cast to java.lang.String

        }
    }
}
