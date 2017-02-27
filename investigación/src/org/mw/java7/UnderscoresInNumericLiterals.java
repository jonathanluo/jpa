package org.mw.java7;

/**
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/enhancements.html#javase7
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html
 */
public class UnderscoresInNumericLiterals {

    public static void main(String[] argv) {
        long creditCardNumber = 1234_5678_9012_3456L;
        long socialSecurityNumber = 999_99_9999L;
        float pi = 3.14_15F;
        float pi2 = 3.1_4_159F;
        long hexBytes = 0xFF_EC_DE_5E;
        long hexWords = 0xCAFE_BABE;
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        byte nybbles = 0b0010_0101;
        long bytes = 0b11010010_01101001_10010100_10010010;

        long ipaddress = 101_258_274_670L;

        System.out.println("creditCardNumber: " + creditCardNumber);
        System.out.println("socialSecurityNumber: " + socialSecurityNumber);
        System.out.println("pi: " + pi);
        System.out.println("pi2: " + pi2);
        System.out.println("ipaddress: " + ipaddress);
    }
}
