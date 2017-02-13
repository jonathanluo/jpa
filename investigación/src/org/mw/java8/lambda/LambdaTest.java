package org.mw.java8.lambda;

/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html *
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 * https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm #Java8Tester
    Following are the important characteristics of a lambda expression:

    Optional type declaration − No need to declare the type of a parameter. The compiler can inference the same from the
        value of the parameter.

    Optional parenthesis around parameter − No need to declare a single parameter in parenthesis. For multiple parameters, 
        parentheses are required.

    Optional curly braces − No need to use curly braces in expression body if the body contains a single statement.

    Optional return keyword − The compiler automatically returns the value if the body has a single expression to return 
        the value. Curly braces are required to indicate that expression returns a value.
 * 
 */
public class LambdaTest {

    public static void main(String args[]) {
        LambdaTest tester = new LambdaTest();

        //with type declaration
        MathOperation addition = (int a, int b) -> a + b;

        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        int op1 = 20;
        int op2 = 5;
        System.out.println("20 + 5 = " + tester.operate(op1, op2, addition));
        System.out.println("20 - 5 = " + tester.operate(op1, op2, subtraction));
        System.out.println("20 x 5 = " + tester.operate(op1, op2, multiplication));
        System.out.println("20 / 5 = " + tester.operate(op1, op2, division));

        //with parenthesis
        GreetingService greetService1 = (message) -> System.out.println("Hello " + message);

        //without parenthesis
        GreetingService greetService2 = message -> System.out.println("Hello " + message);

        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}