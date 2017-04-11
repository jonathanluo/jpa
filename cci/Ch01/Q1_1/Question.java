package Q1_1;

/**
 * http://stackoverflow.com/questions/10910913/how-do-shift-operators-work-in-java
 * http://www.geeksforgeeks.org/bitwise-shift-operators-in-java/
 * http://www.java-samples.com/showtutorial.php?tutorialid=268
 * https://en.wikibooks.org/wiki/Java_Programming/Primitive_Types
 * http://stackoverflow.com/questions/6766343/best-practice-for-getting-datatype-sizesizeof-in-java
 * http://stackoverflow.com/questions/2370288/is-there-any-sizeof-like-method-in-java
 */
public class Question {

	public static boolean isUniqueChars(String str) {
		System.out.println("isUniqueChars() - " + str);
		int checker = 0;
		for (int i = 0; i < str.length(); ++i) {
			int val = str.charAt(i) - 'a';
			System.out.println("\tval: " + str.charAt(i) + ", " + val);
			int shifted = 1 << val;
			System.out.println("\t\t1 << val: " + shifted);

			if ((checker & (1 << val)) > 0) return false;
			checker |= (1 << val);
		}
		return true;
	}
	
	public static boolean isUniqueChars2(String str) { // all printable chars mapped to 0~255 value
		System.out.println("isUniqueChars2() - " + str);
		boolean[] char_set = new boolean[256];
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			System.out.println("\tval: " + str.charAt(i) + ", " + val);
			if (char_set[val]) { 
				return false;
			}
			char_set[val] = true;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle", "PaPPy"};
		for (String word : words) {
			System.out.println(word + ": " + isUniqueChars(word) + " " + isUniqueChars2(word));
		}
	    System.out.println(Integer.toBinaryString(2 << 0));
	    System.out.println(Integer.toBinaryString(2 << 1));
	    System.out.println(Integer.toBinaryString(2 << 2));
	    System.out.println(Integer.toBinaryString(2 << 3));
	    System.out.println(Integer.toBinaryString(2 << 4));
	    System.out.println(Integer.toBinaryString(2 << 5));

       int x = -4;
       System.out.println(x + ", " + Integer.toBinaryString(x));
       System.out.println(Integer.toBinaryString(x) + ", " + (x>>1) + ", " + Integer.toBinaryString(x>>1));
       int y = 4;
       System.out.println(y + ", " + Integer.toBinaryString(y));
       System.out.println(Integer.toBinaryString(y) + ", " + (y>>1) + ", " + Integer.toBinaryString(y>>1));

       char c = 4; // char - 16 bits from 0 to 2^16-1 (65535), All Unicode characters
                   // short 16 -2^15 2^15-1
       System.out.println("char c = 4: " + c + ", " + Integer.toBinaryString(c) + ", " +  Character.SIZE);

       byte b = 4;
       System.out.println("byte b = 4: " + b + ", " + Integer.toBinaryString(b));

       System.out.println("Boolean.SIZE: " + 1);
       System.out.println("Byte.SIZE: " + Byte.SIZE);
       System.out.println("Character.SIZE: " + Character.SIZE);
       System.out.println("Integer.SIZE: " + Integer.SIZE);
       System.out.println("Long.SIZE: " + Long.SIZE);
       System.out.println("Float.SIZE: " + Float.SIZE);
       System.out.println("Double.SIZE: " + Double.SIZE);

	}

}
