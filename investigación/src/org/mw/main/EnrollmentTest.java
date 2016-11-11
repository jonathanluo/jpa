package org.mw.main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.mw.annotation.AuditField;
import org.mw.annotation.AuditMethod;
import org.mw.entity.Enrollment;

public class EnrollmentTest {

    static final String BLANK = "";

    public void displayMethods(Object obj) {
        try {
            Method[] methods = obj.getClass().getMethods();
            for (Method method : methods) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(AuditMethod.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method "  + method + " : " + anno);
                        }
                        AuditMethod m = method.getAnnotation(AuditMethod.class);
                        System.out.println("    method=" + m);
                        System.out.println("    alias=" + m.alias());
                        System.out.println("    priority=" + m.priority());
                        System.out.println("    concurrent=" + m.concurrent());

                        // get field value
                        AuditMethod am = (AuditMethod) method.getAnnotation(AuditMethod.class);
                        if (am != null) {
                            try {
                                Object objectValue = method.invoke(obj);
                                System.out.println("    AuditMethod value='" + objectValue + "'");
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                //ignore;
                            }
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

    public void displayFields(Object ao) {
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
                        field.setAccessible(true);
                        objectValue = field.get(ao);
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

    /**
     * Get value from a specified audit method
     *
     * @param ao          annotation audit object
     * @param auditMethod audit method name
     */
    private Object getValueFromAuditMethod(Object ao, String auditMethod) {
        Object val = BLANK;
        for (Method m : ao.getClass().getMethods()) {
            AuditMethod am = (AuditMethod) m.getAnnotation(AuditMethod.class);
            if ((am != null) && auditMethod.equals(m.getName())) {
                try {
                    val = m.invoke(ao);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    //ignore;
                }
            }
        }
        return val;
    }

    public static void main(String[] args) {
        EnrollmentTest test = new EnrollmentTest();
        Enrollment enrollment = new Enrollment();
        enrollment.setId(101);
        enrollment.setFirstname("Jason");
        enrollment.setLastname("Smith");
        enrollment.setNote("Notes: 1) 2) 3)");
        enrollment.setComment("Enrolled in 2016");
        test.displayMethods(enrollment);
        System.out.println("=====================================================");
        test.displayFields(enrollment);
    }
}