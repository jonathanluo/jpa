package org.mw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AuditCollection {

    public enum Mapping {ONE_TO_ONE, ONE_TO_MANY, MANY_TO_ONE, MANY_TO_MANY};

    public Mapping mapping() default Mapping.ONE_TO_MANY;
}
