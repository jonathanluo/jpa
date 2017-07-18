package org.mw.interv;

/**
 * Write a program to count the max sequence count
 * created 7/18/17 3 - 3:55 PM 
 *
 */
class WMMaxSequenceCount {
    public static void maxCount(int[] a) {
        if (a.length == 0) {
            return;
        }
        for (int j= 0; j < a.length; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.print("\n");
        int i = 0;
        int curr;
        int maxcount = -1;
        int numWithMaxcount = 0;
        int count = 0;

        curr = a[0];
        count++;
        for (int j = i + 1; j < a.length; j++) {
            if (curr == a[j]) {
                count++;
                continue;
            }
            if (count > maxcount) {
                numWithMaxcount = curr;
                maxcount = count;
            }
            count = 0;
            curr = a[j];
            count++;
        }
        System.out.print("maxNumber: " + numWithMaxcount + " : " + maxcount);
        System.out.print("\n");
    }
    public static void main(String[] args) {
        int[] a = {2,2,3,2,2,2,2,2,5,4,4,4,4,7,7};
        // Output 2:5
        maxCount(a);
        int[] b = {2,2,3,2,2,2,2,2,5,4,4,4,4,4,4,7,7};
        // Output 4:6
        maxCount(b);
    }
    
}