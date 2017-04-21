package org.mw.lang;

import java.util.BitSet;
public class BitSetDemo {

/**
 * https://www.tutorialspoint.com/java/java_bitset_class.htm 
 */
public static void main(String args[]) {
      BitSet bits1 = new BitSet(16);
      BitSet bits2 = new BitSet(16);
      System.out.println("(1)Initial pattern in bits1: ");
      System.out.println(bits1);
      bits1.set(0);
      System.out.println(bits1);
      bits1.set(15);
      System.out.println(bits1);
      bits1.set(2);
      System.out.println("bits1.length(): " + bits1.length() + ", bits1.size(): " + bits1.size() + ", the follwoing bits are true" + bits1);
      bits1.flip(2);
      System.out.println("bits1.flip(2) "+ bits1);
      System.out.println("bits1.length(): " + bits1.length() + ", bits1.size(): " + bits1.size() + ", the follwoing bits are true" + bits1);

      System.out.println("\n(1)Initial pattern in bits2: ");
      System.out.println(bits2);

      // set some bits
      for(int i = 0; i < 16; i++) {
         if((i % 2) == 0) bits1.set(i); // even number - Sets the bit at the specified index to true
         if((i % 5) != 0) bits2.set(i); // exclude 5 10, 15
      }
     
      System.out.println("(2)Initial pattern in bits1: ");
      System.out.println(bits1);
      System.out.println("\n(2)Initial pattern in bits2: ");
      System.out.println(bits2);

      // AND bits
      bits2.and(bits1);
      System.out.println("\nbits2 AND bits1: ");
      System.out.println(bits2);

      // OR bits
      bits2.or(bits1);
      System.out.println("\nbits2 OR bits1: ");
      System.out.println(bits2);

      // XOR bits
      bits2.xor(bits1);
      System.out.println("\nbits2 XOR bits1: ");
      System.out.println(bits2);
   }
}
