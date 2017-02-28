package org.mw.java7;

import java.util.*;

/**
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/enhancements.html#javase7
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/non-reifiable-varargs.html
 *
 * @SafeVarargs v.s. @SuppressWarnings({"unchecked", "varargs"})
 */
class ArrayBuilder {

  /**
   * Type safety: Potential heap pollution via varargs parameter elements
   */
  public static <T> void addToList (List<T> listArg, T... elements) {
    for (T x : elements) {
      listArg.add(x);
    }
  }

  /**
   * Type safety: Potential heap pollution via varargs parameter l
   */
  public static void faultyMethod(List<String>... l) {
    Object[] objectArray = l;  // Valid
    objectArray[0] = Arrays.asList(new Integer(42));
    String s = l[0].get(0);    // ClassCastException thrown here - java.lang.Integer cannot be cast to java.lang.String
  }

}

public class HeapPollutionExample {

  public static void main(String[] args) {

    List<String> stringListA = new ArrayList<String>();
    List<String> stringListB = new ArrayList<String>();

    ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
    ArrayBuilder.addToList(stringListA, "Ten", "Eleven", "Twelve");
    List<List<String>> listOfStringLists = new ArrayList<List<String>>();
    ArrayBuilder.addToList(listOfStringLists, stringListA, stringListB);

    ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
  }
}
