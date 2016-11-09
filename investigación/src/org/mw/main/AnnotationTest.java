package org.mw.main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.mw.annotation.MethodInfo;

/**
 * https://www.javacodegeeks.com/2012/11/java-annotations-tutorial-with-custom-annotation.html
 *
 */
public class AnnotationTest {

    public static void main(String[] args) {
        try {
            for (Method method : AnnotationTest.class
                    .getClassLoader()
                    .loadClass(("org.mw.entity.AnnotationExample"))
                    .getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method
                        .isAnnotationPresent(MethodInfo.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method "  + method + " : " + anno);
                        }
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("Method with revision no 1 = "
                                    + method);
                        }

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}