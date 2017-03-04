package org.mw.lang;

/**
 * http://introcs.cs.princeton.edu/java/23recursion/
 */
public class Recursion {

	/**
	 * http://introcs.cs.princeton.edu/java/23recursion/
	 * Our factorial() implementation exhibits the two main components that are required for every recursive function.
	 * 1) The base case returns a value without making any subsequent recursive calls. It does this for one or more special 
	 *    input values for which the function can be evaluated without recursion. For factorial(), the base case is n = 1.
	 * 2) The reduction step is the central part of a recursive function. It relates the value of the function at one (or more) 
	 *    input values to the value of the function at one (or more) other input values. Furthermore, the sequence of input 
	 *    values values must converge to the base case. For factorial(), the value of n decreases by 1 for each call, so the 
	 *    sequence of input values converges to the base case.
	 */
	public static long factorial(int n) { 
	    if (n == 1) return 1; 
	    return n * factorial(n-1); 
	}
	
	public static void main(String[] argv) {
		System.out.println(Euclid.gcd(1440, 408));
	}
}

/**
 * http://introcs.cs.princeton.edu/java/23recursion/Euclid.java.html
 */
class Euclid {
    // recursive implementation
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }
    // non-recursive implementation
    public static int gcd2(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
}