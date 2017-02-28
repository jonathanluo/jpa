package org.mw.java7;

/**
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/enhancements.html#javase7
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html
 */
public class CatchMultiple {

    static class FirstException extends Exception { }
    static class SecondException extends Exception { }

    public void rethrowException(String exceptionName) throws Exception {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception e) {
            //e = null; // okay
            throw e;
        }
    }

    public void catchException(String exceptionName) throws Exception {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (FirstException | SecondException e) {
            /* If a catch block handles more than one exception type, then the catch parameter is implicitly final. In 
             * this example, the catch parameter ex is final and therefore you cannot assign any values to it within the 
             * catch block.*/
            //e = null; // The parameter e of a multi-catch block cannot be assigned
            throw e;
        }
    }

    public void rethrowExceptions(String exceptionName)
              throws FirstException, SecondException {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        }
        catch (Exception e) {
          throw e;
        }
    }
    
    public static void main(String[] argv) {

    }
}
