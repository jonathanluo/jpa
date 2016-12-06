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

    public String columnName()  default ""; // column name to be displayed, if not presents use field name

    public int    columnIndex() default 0;

    public boolean id() default false; // true if this field is id

    public String[] target() default {}; // audit target for specified action code

    public String fieldMethod()  default ""; // if presents, use this method to override default field value 
}
