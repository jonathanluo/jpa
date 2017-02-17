package org.mw.generics;

import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/capture.html
 *
 * In this example, the compiler processes the i input parameter as being of type Object. When the foo method invokes 
 * List.set(int, E), the compiler is not able to confirm the type of object that is being inserted into the list, and an
 * error is produced. When this type of error occurs it typically means that the compiler believes that you are assigning
 * the wrong type to a variable. Generics were added to the Java language for this reason — to enforce type safety at 
 * compile time.
 */
public class WildcardError {
 
    void foo(List<?> i) {
    // error msg:The method set(int, capture#1-of ?) in the type List<capture#1-of ?> is not applicable for the 
    // arguments (int, capture#2-of ?)
      i.set(0, i.get(0));
    }
}
