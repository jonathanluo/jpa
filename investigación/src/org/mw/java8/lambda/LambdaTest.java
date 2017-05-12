package org.mw.java8.lambda;

/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html *
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

   https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm #Java8Tester
    Following are the important characteristics of a lambda expression:

	Lambda Type Inference @see http://tutorials.jenkov.com/java/lambda-expressions.html#type-inference
    Optional type declaration − No need to declare the type of a parameter. The compiler can inference the same from the
        value of the parameter.

	Lambda Parameters @see http://tutorials.jenkov.com/java/lambda-expressions.html#lambda-parameters
    Optional parenthesis around parameter − No need to declare a single parameter in parenthesis. For multiple parameters, 
        parentheses are required, e.g.
        s -> Arrays.asList(s.split(" "))
        word -> new Tuple2<>(word, 1)

	Lambda Function Body @see http://tutorials.jenkov.com/java/lambda-expressions.html#lambda-body
    Optional curly braces − No need to use curly braces in expression body if the body contains a single statement.

	Returning a Value From a Lambda Expression @see http://tutorials.jenkov.com/java/lambda-expressions.html#returning values-from-lambda-expression
    Optional return keyword − The compiler automatically returns the value if the body has a single expression to return 
        the value. Curly braces are required to indicate that expression returns a value.

 * http://tutorials.jenkov.com/java/lambda-expressions.html
 *   Java lambda expressions can only be used where the type they are matched against is a single method interface
 *   A single method interface is also sometimes referred to as a functional interface
 */
public class LambdaTest {

    private static final String NEWLINE = "\n";

    public static void main(String args[]) {
        LambdaTest tester = new LambdaTest();

        //with type declaration
        MathOperation addition = (int a, int b) -> a + b; // lhs of -> is the parameters, rhs of -> is the implementation

        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        int op1 = 20;
        int op2 = 5;


        StringBuilder sb = new StringBuilder();
        sb.append("20 + 5 = " + tester.operate(op1, op2, addition)).append(NEWLINE);
        sb.append("20 - 5 = " + tester.operate(op1, op2, subtraction)).append(NEWLINE);
        sb.append("20 x 5 = " + tester.operate(op1, op2, multiplication)).append(NEWLINE);
        sb.append("20 / 5 = " + tester.operate(op1, op2, division)).append(NEWLINE);
        System.out.print(sb.toString());

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