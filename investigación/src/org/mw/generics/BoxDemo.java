package org.mw.generics;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html
 *
 * Type Inference and Generic Methods
 * Type Inference and Instantiation of Generic Classes
	You can replace the type arguments required to invoke the constructor of a generic class with an empty set of 
	type parameters (<>) as long as the compiler can infer the type arguments from the context.

	For example, consider the following variable declaration:

	Map<String, List<String>> myMap = new HashMap<String, List<String>>();
	You can substitute the parameterized type of the constructor with an empty set of type parameters (<>):

	Map<String, List<String>> myMap = new HashMap<>();
 */
public class BoxDemo {

	  public static <U> void addBox(U u, java.util.List<Box<U>> boxes) {
	    Box<U> box = new Box<>();
	    box.set(u);
	    boxes.add(box);
	  }

	  public static <U> void outputBoxes(java.util.List<Box<U>> boxes) {
	    int counter = 0;
	    for (Box<U> box: boxes) {
	      U boxContents = box.get();
	      System.out.println("Box #" + counter + " contains [" + boxContents.toString() + "]");
	      counter++;
	    }
	  }

	  public static void main(String[] args) {
	    java.util.ArrayList<Box<Integer>> listOfIntegerBoxes =  new java.util.ArrayList<>();
	    BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes); // specify the type parameter <Integer> as a type witness 
	    BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes); // Alternatively, if type witness omitted,a Java compiler automatically infers (from the method's arguments) that the type parameter is Integer 
	    BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
	    BoxDemo.outputBoxes(listOfIntegerBoxes);
	  }
	}
