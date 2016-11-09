package org.mw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuditMethod {

	// alias of the method 
    public String alias() default "";

    public boolean concurrent() default false;

    public int priority() default 0;

}
