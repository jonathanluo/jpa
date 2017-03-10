package org.mw.lang;

import java.util.Scanner;

/**
 * http://stackoverflow.com/questions/4367260/creating-a-recursive-method-for-palindrome 
 */
public class Palindromes {
    public static boolean isPal(String s) {
        if(s.length() == 0 || s.length() == 1)
            // if length =0 OR 1 then it is
            return true; 
        if(s.charAt(0) == s.charAt(s.length()-1))
            // check for first and last char of String:
            // if they are same then do the same thing for a substring
            // with first and last char removed. and carry on this
            // until you string completes or condition fails
            return isPal(s.substring(1, s.length()-1));

        // if its not the case than string is not.
        return false;
    }

    public void consoleTest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("type a word to check if its a palindrome or not");
        String x = sc.nextLine();
        if(isPal(x))
            System.out.println(x + " is a palindrome");
        else
            System.out.println(x + " is not a palindrome");
        if (sc != null)
        	sc.close();
    }

    public static boolean isPalindrome(String str, int left, int right) {
        if(left >= right) {
            return true;
        }
        else {
            if(str.charAt(left) == str.charAt(right)) {
                return isPalindrome(str, ++left, --right);
            }
            else {
                return false;
            }
        }
    }

     public static void main(String []args){
        String str = "abcdcbb"; 
        System.out.println(str + " "  + isPalindrome(str, 0, str.length()-1));

        str = "radar"; 
        System.out.println(str + " "  + isPalindrome(str, 0, str.length()-1));

     }
}




// http://www.cs.toronto.edu/~krj/courses/108/lectures/lect13/Palindromes.java
//*******************************************************************
//
// Palindromes.java          In Text          Application
//
// Authors:  Lewis and Loftus
//
// Classes:  Palindromes
//           Palindrome_Tester
//
//*******************************************************************

//-------------------------------------------------------------------
//
//Class Palindromes demonstrates recursive calls.
//
//Methods:
//
//   public static void main (String[] args)
//
//-------------------------------------------------------------------

class Palindromes2 {

 //===========================================================
 //  Creates a Palindrome_Tester object, and tests several
 //  strings.
 //===========================================================
 public static void main (String[] args) {

    Palindrome_Tester tester = new Palindrome_Tester();
    
    System.out.println ("'radar' is a palindrome? " 
       + tester.ptest ("radar")
       + " " + tester.isPalindrom("radar")
       );

    System.out.println ("'abcddcba' is a palindrome? " 
       + tester.ptest ("abcddcba")
       + " " + tester.isPalindrom("abcddcba")
       );

    System.out.println ("'able was I ere I saw elba' is a palindrome? "
       + tester.ptest ("able was I ere I saw elba") 
       + " " + tester.isPalindrom("able was I ere I saw elba")
       );

    System.out.println ("'hello' is a palindrome? "
       + tester.ptest ("hello")
       + " " + tester.isPalindrom("hello")
       );

    System.out.println ("'abcxycba' is a palindrome? "
       + tester.ptest ("abcxycba")
       + " " + tester.isPalindrom("abcxycba")
       );

 }  // method main

}  // class Palindromes

//-------------------------------------------------------------------
//
//Class Palindrome_Tester contains a method to test to see if
//a string is a palindrome.
//
//Methods:
//
//   public boolean ptest (String str)
//
//-------------------------------------------------------------------

class Palindrome_Tester {

 //===========================================================
 //  Uses recursion to perform the palindrome test.
 //===========================================================
 public boolean ptest (String str) {

    boolean result = false;

    if (str.length() <= 1)
       result = true;
    else {
       if (str.charAt (0) == str.charAt (str.length()-1))
          result = ptest (str.substring (1, str.length()-1));
    }
    return result;

 }  // method ptest

 // https://www.khanacademy.org/computing/computer-science/algorithms/recursive-algorithms/a/using-recursion-to-determine-whether-a-word-is-a-palindrome
 public boolean isPalindrom(String str) {
     if (str.length() == 0 || str.length() == 1){
         return true;
     } else {
         if (str.charAt(0) != str.charAt(str.length() - 1)){
             return false;
         } else {
             return isPalindrom(str.substring(1, str.length()-1));
         }
     }
 } 

}  // class Palindrome_Tester
