package org.mw.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
 */
public class WildcardsAndSubtyping {

	public static void main(String[] argv) {
		List<? extends Integer> intList = new ArrayList<>();
		List<? extends Integer> intList2 = null;

		List<? extends Number>  numList = new ArrayList<>();
		List<? extends Number>  numList2 = intList;  // OK. List<? extends Integer> is a subtype of List<? extends Number>

//		intList2 = numList; // error
	}
}
