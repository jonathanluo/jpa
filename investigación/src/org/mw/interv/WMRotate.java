package org.mw.interv;

/**
 * Write a program to rotate an array of integers for a given number of steps
 * created 7/18/17 3 - 3:55 PM 
 *
 */
class WMRotate {
    public static void rotate(int[] a, int numberOfRotates) {
        if (a.length == 0) {
            return;
        }
        if (numberOfRotates > a.length) {
            System.out.print("not allowed!");
            return;
        }
        for (int j= 0; j < a.length; j++) {
            System.out.print(a[j] + " ");
        }
        int[] b = new int[a.length];
        int i = 0;
        for (int j = numberOfRotates; j < a.length; j++) {
            b[i++] = a[j];
        }
        for (int j = 0; j < numberOfRotates; j++) {
            b[a.length - numberOfRotates + j] = a[j];
        }
        System.out.print("\n");
        for (int j= 0; j < b.length; j++) {
            System.out.print(b[j] + " ");
        }
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        // Output {3,4,5,6,7,1,2} k = 2
        rotate(a, 8);
    }
}