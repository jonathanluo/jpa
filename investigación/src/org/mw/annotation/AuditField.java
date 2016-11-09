package org.mw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sample:
 *
 * @AuditField(columnName="Name", columnIndex=1, id=false, target={"weblogic", "tomcat"})
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AuditField {

    public String columnName()  default "";

    public int    columnIndex() default 0;

    public boolean id() default true;

    public String[] target() default {};
}
