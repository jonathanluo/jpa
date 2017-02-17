package org.mw.generics;

import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/capture.html
 */
public class WildcardErrorBad {

	/**
	 * Though list l1 and l2 both have super type of Number, l1 and l2 are not the same type, you cannot swap element
	 * between them; the following example illustrates this  
	 */
    void swapFirst(List<? extends Number> l1, List<? extends Number> l2) {
      Number temp = l1.get(0);
      // The method set(int, capture#2-of ? extends Number) in the type List<capture#2-of ? extends Number> is not 
      // applicable for the arguments (int, capture#3-of ? extends Number)
//      l1.set(0, l2.get(0)); // expected a CAP#1 extends Number,
                            // got a CAP#2 extends Number;
                            // same bound, but different types
 
      // The method set(int, capture#4-of ? extends Number) in the type List<capture#4-of ? extends Number> is not 
      // applicable for the arguments (int, Number)
//      l2.set(0, temp);	    // expected a CAP#1 extends Number,
                            // got a Number
    }
}