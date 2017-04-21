package ch19;

public class Q19_04 {

	public static int getMax(int a, int b) {
		int c = a - b;
		int k = (c >> 31) & 0x1;
		int max = a - k * c;
		return max;
	}
	
	public static void main(String[] args) {
		int a = 103217;
		int b = 242239;
		System.out.println("max(" + a + ", " + b + ") = " + getMax(a, b));
	}

}
