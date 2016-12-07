package org.mw.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.mw.annotation.AuditClass;
import org.mw.annotation.AuditCollection;
import org.mw.annotation.AuditField;
import org.mw.annotation.AuditMethod;

/**
 * Sample test code: EnrollmentTest.java
 *
 */
public class AuditUtil {

    static final String BLANK = "";

    public void handleFields(Object ao) {
        try {
            AuditClass auditClass = ao.getClass().getAnnotation(AuditClass.class);
            System.out.println("====================================================");
            if (auditClass != null) {
                System.out.println("ao.getClass().getName()=" + ao.getClass().getName());
                System.out.println("auditClass.name=" + auditClass.name());
                if (!auditClass.idMethod().isEmpty()) {
                	System.out.println("id=" + getValueFromMethod(ao, auditClass.idMethod()));
                }
            }
            Field[] fields = ao.getClass().getDeclaredFields();
            // get list of normal audit fields, audit collection fields
            List<Field> auditFields = new ArrayList<>();
            List<Field> auditCollectionFields = new ArrayList<>();

            for (Field field : fields) {
                if (field.isAnnotationPresent(AuditField.class)) {
                    auditFields.add(field);
                } else if (field.isAnnotationPresent(AuditCollection.class)) {
                    auditCollectionFields.add(field);
                }
            }
            for (Field f : auditFields) {
                handleNormalField(ao, f);
            }
            for (Field f : auditCollectionFields) {
                handleCollectionField(ao, f);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param ao
     * @param field an AuditField object
     */
    public void handleNormalField(Object ao, Field field) {
        Object objectValue = BLANK;
        try {
            AuditField f = field.getAnnotation(AuditField.class);
            System.out.println("    " + f);
            if (f.target().length > 0) {
                System.out.println("    target=");
                for (String target : f.target()) {
                    System.out.println("        " + target);
                }
            }
            if (!f.fieldMethod().isEmpty()) {
                objectValue = getValueFromMethod(ao, f.fieldMethod());
            } else {
                // get field value
                field.setAccessible(true); // must set this
                objectValue = field.get(ao);
            }
            System.out.println("    fieldName=" + field.getName() + ", value='" + objectValue + "'\n");
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 
     * @param ao
     * @param field an AuditCollection field object
     */
    @SuppressWarnings("rawtypes")
    public void handleCollectionField(Object ao, Field field) {
        Object objectValue;
        Collection collection = null;
        try {
            AuditCollection f = field.getAnnotation(AuditCollection.class);
            System.out.println("    field=" + f);
            // get field value
            field.setAccessible(true); // must set this
            objectValue = field.get(ao);
            if (objectValue instanceof Collection) {
                collection = (Collection) objectValue;
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Object obj = it.next();
                    handleFields(obj);
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
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
     * Get value from a specified audit field
     *
     * @param ao          annotated audit object
     * @param auditField audit field name
     */
    public Object getValueFromAuditField(Object ao, String auditField) {
        Object val = BLANK;
        for (Field f : ao.getClass().getDeclaredFields()) {
            if (auditField.equals(f.getName())) {
                try {
                    f.setAccessible(true); // must set this
                    val = f.get(ao);
                    break;
                } catch (IllegalAccessException e) {
                    //ignore;
                }
            }
        }
        return val;
    }

    /**
     * Get value from a specified audit method
     *
     * @param ao          annotated audit object
     * @param auditMethod audit method name
     */
    public Object getValueFromMethod(Object ao, String auditMethod) {
        Object val = BLANK;
        for (Method m : ao.getClass().getMethods()) {
            if (auditMethod.equals(m.getName())) {
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