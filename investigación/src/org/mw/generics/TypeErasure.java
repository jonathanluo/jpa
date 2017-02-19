package org.mw.generics;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/erasure.html
 * https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html

   Type Erasure is the process where the compiler removes information related to type parameters and type arguments.

   - Replace all type parameters in generic types with their bounds or Object if the type parameters are unbounded. The 
     produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
   - Insert type casts if necessary to preserve type safety.
   - Generate bridge methods to preserve polymorphism in extended generic types.

   Type erasure ensures that no new classes are created for parameterized types; consequently, generics incur no runtime
   overhead.
 */
public class TypeErasure {

    public static void main(String[] argv) {
        try {
            MyNode mn = new MyNode(5);
            Node n = mn;            // A raw type - compiler throws an unchecked warning
            n.setData("Hello");     // actual - Causes a ClassCastException to be thrown
            Integer x = mn.data;    // Causes a ClassCastException to be thrown
        } catch (Exception e) {
            System.out.println(e.getMessage()); // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        }
    }
}

class Node<T> {

    public T data;
    public Node(T data) { 
        this.data = data; 
    }
    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

class MyNode extends Node<Integer> {
    public MyNode(Integer data) { 
        super(data); 
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
