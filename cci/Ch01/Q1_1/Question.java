package Q1_1;

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
	}

}
