package org.mw.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.mw.annotation.AuditField;
import org.mw.annotation.AuditMethod;

public class AuditUtil {

    static final String BLANK = "";

    public void handleFields(Object ao) {
        Object objectValue;
        try {
            Field[] fields = ao.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(AuditField.class)) {
                    try {
                        for (Annotation anno : field.getDeclaredAnnotations()) {
                            System.out.println("Annotation in field "  + field + " : " + anno);
                        }
                        AuditField f = field.getAnnotation(AuditField.class);
                        System.out.println("    field=" + f);
                        System.out.println("    columnName=" + f.columnName());
                        System.out.println("    columnIdx=" + f.columnIndex());
                        System.out.println("    target=" + f.target());
                        // get field value
                        field.setAccessible(true); // must set this
                        objectValue = field.get(ao);
                        System.out.println("    fieldName=" + field.getName());
                        System.out.println("    field value='" + objectValue + "'");
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void handleMethods(Object obj) {
        try {
            Method[] methods = obj.getClass().getMethods();
            for (Method m : methods) {
                // checks if MethodInfo annotation is present for the method
                if (m.isAnnotationPresent(AuditMethod.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : m.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method "  + m + " : " + anno);
                        }
                        AuditMethod am =  m.getAnnotation(AuditMethod.class);
                        System.out.println("    am=" + am);
                        System.out.println("    alias=" + am.alias());
                        System.out.println("    priority=" + am.priority());
                        System.out.println("    concurrent=" + am.concurrent());

                        // call method
                        try {
                            Object objectValue = m.invoke(obj);
                            System.out.println("    method=" + m.getName());
                            System.out.println("    AuditMethod value='" + objectValue + "'");
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            //ignore;
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get value from a specified audit method
     *
     * @param ao          annotated audit object
     * @param auditMethod audit method name
     */
    public Object getValueFromAuditMethod(Object ao, String auditMethod) {
        Object val = BLANK;
        for (Method m : ao.getClass().getMethods()) {
            AuditMethod am = (AuditMethod) m.getAnnotation(AuditMethod.class);
            if ((am != null) && auditMethod.equals(m.getName())) {
                try {
                    val = m.invoke(ao);
                    break;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    //ignore;
                }
            }
        }
        return val;
    }

}