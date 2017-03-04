package org.mw.lang;

/**
 * http://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
 */
public class PassByValue {

	public void objectTest() {
	    Dog aDog = new Dog("Max");
        System.out.println( "aDog.getName(): "  + aDog.getName());
	    changeFieldValue(aDog);
        System.out.println( "after called changeFieldValue(aDog) - aDog.getName(): "  + aDog.getName());

	    aDog = new Dog("Max");
        System.out.println( "aDog.getName(): "  + aDog.getName());
	    newDog(aDog);
        System.out.println( "after called newDog(aDog) - aDog.getName(): "  + aDog.getName());

	    if (aDog.getName().equals("Max")) { //true
	        System.out.println( "Java passes by value." );
	    } else if (aDog.getName().equals("Fifi")) {
	        System.out.println( "Java passes by reference??" );
	    }
	}
	
	public void changeFieldValue(Dog d) {
	    d.setName("Fifi"); // changes passed back to caller
        System.out.println( "inside changeFieldValue - d.getName(): "  + d.getName());
	}

	public void newDog(Dog d) {
        System.out.println( "inside newDog step 1 - d.getName(): "  + d.getName());
	    d = new Dog("Fifi"); // new object never pass back to caller, invisible to caller
        System.out.println( "inside newDog step 2 - d.getName(): "  + d.getName());
	}

	static class Dog {
		String name;
		public Dog(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public void sampleTest(){
	    int i = 5;
	    System.out.println("before pass i ==> "+ i);
	    incrementBy100(i);
	    System.out.println("after passed i ==> "+ i);

	    Integer j = new Integer(5);
	    System.out.println("\nbefore pass j ==> "+ j);
	    incrementBy100(j);
	    System.out.println("after passed j ==> "+ j);
	}
	private void incrementBy100(int i) {
	    System.out.println("inside incrementBy100() argument i = " + i);
	    i += 100;
	    System.out.println("inside incrementBy100() incremented = " + i);
	}

	public static void main(String[] argv) {
		PassByValue test = new PassByValue();
		test.sampleTest();

	    System.out.println("");
		test.objectTest();
	}
}
