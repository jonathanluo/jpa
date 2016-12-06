package org.mw.entity;

import org.mw.annotation.AuditClass;
import org.mw.annotation.AuditField;

@AuditClass(name="phone", idMethod="getId")
public class Phone {

    public enum PhoneType {HOME,
                           MOBILE,
                           WORK,
                           FAX};

    int id;

    @AuditField
    String phone;

    @AuditField
    PhoneType phoneType;

    public Phone() {
    }

    public Phone(String phone, PhoneType phoneType) {
        this.phone = phone;
        this.phoneType = phoneType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

}