package org.mw.entity;

import org.mw.annotation.AuditField;
import org.mw.annotation.AuditMethod;

public class Enrollment {

    @AuditField(id=true)
    int id;

    @AuditField(columnName="First Name", columnIndex=1)
    String firstname;

    @AuditField(columnName="Last Name", columnIndex=2)
    String lastname;

    String note;

    @AuditField(columnName="Feedback", columnIndex=3, id=false, target={"weblogic", "tomcat"})
    String comment;

    @AuditMethod(alias="get surname", priority=2)
    public String getFirstname() {
        return firstname;
    }

    @AuditMethod(alias="get given name", priority=1)
    public String getLastname() {
        return lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
}