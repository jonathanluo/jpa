package org.mw.java8.lambda.example;

/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section1
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/examples/LambdaExamples01.zip
 *
 * Lambda expressions address the bulkiness of anonymous inner classes by converting five lines of code into a single
 * statement. This simple horizontal solution solves the "vertical problem" presented by inner classes.
 *
 * @author mikew
 */
public class Main {

  public static void main(String[] args) {

    RunnableTest.main(args);
    ComparatorTest.main(args);
    ListenerTest.main(args);
  }
}
