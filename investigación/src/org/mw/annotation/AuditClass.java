package org.mw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
public @interface AuditClass {

    /**
     * Name of the audit 
     */
    public String name(); 

    /**
     * Sort order
     */
    public int ordinal() default 0;

    /**
     * Custom target attribute
     */
    public String[] target() default {};

    /**
     * The name of method which returns the unique id of an entity object
     *
     * @return idMethod
     */
    public String idMethod() default "";
}
