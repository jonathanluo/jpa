package org.mw.generics;

import java.util.Arrays;
import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/unboundedWildcards.html
 *
 * Note: The Arrays.asList - This static factory method converts the specified array and returns a fixed-size list.
 */
public class UpperBoundedWildcards {

	public static void printList(List<Object> list) {
	    for (Object elem : list)
	        System.out.println(elem + " ");
	    System.out.println();
	}

	public static void printList2(List<?> list) {
	    for (Object elem: list)
	        System.out.print(elem + " ");
	    System.out.println();
	}

    public static void main(String[] argv) {
    	List<Object> li0 = Arrays.asList(1, 2, 3);
    	printList(li0);

    	List<Integer> li1 = Arrays.asList(1, 2, 3);
    	List<String>  ls1 = Arrays.asList("one", "two", "three");
//    	printList(li1); // compiler error
//    	printList(ls1); // compiler error
    	printList2(li1);
    	printList2(ls1);
    }

}
