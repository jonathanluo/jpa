package org.mw.generics;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html
 */
public class MainTest {

	public static void someMethod(Number n) { /* ... */ }

	public static void main(String[] argv) {
		Object someObject = new Object();
		Integer someInteger = new Integer(10);
		someObject = someInteger;   // OK

		someMethod(new Integer(10));   // OK
		someMethod(new Double(10.1));   // OK
	}
	
}
