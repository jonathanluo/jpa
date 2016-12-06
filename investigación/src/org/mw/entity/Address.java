package org.mw.entity;

import org.mw.annotation.AuditClass;
import org.mw.annotation.AuditField;
import org.mw.annotation.AuditMethod;

@AuditClass(name="address")
public class Address {

    public enum AddressType {HOME,
                             WORK,
                             ALTERNATIVE};

    @AuditField(id=true)
    int id;

    String street;

    String city;

    String state;

    String zip;

    AddressType addressType;

    public Address() {
    }

    public Address(String street, String city, String state, String zip, AddressType addressType) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.addressType = addressType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @AuditMethod(alias="get address string", priority=2)
    public String getAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(street).append(", ").append(zip).append(", ").append(city).append(" ").append(state).append(" ").append(zip);
        return sb.toString();
    }
}