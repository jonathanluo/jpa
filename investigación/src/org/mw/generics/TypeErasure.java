package org.mw.generics;

/**
 * https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html
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
