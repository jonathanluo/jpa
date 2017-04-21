package ch09;

import java.util.Arrays;

import cclib.AssortedMethods;

public class Q9_2 {
	public static void main(String[] args) {
		String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
		System.out.println(AssortedMethods.stringArrayToString(array));
		Arrays.sort(array, new AnagramComparator());
		System.out.println(AssortedMethods.stringArrayToString(array));
	}
}
