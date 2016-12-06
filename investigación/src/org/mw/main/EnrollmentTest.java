package org.mw.main;

import org.mw.entity.Address;
import org.mw.entity.Enrollment;
import org.mw.entity.Phone;
import org.mw.util.AuditUtil;

public class EnrollmentTest {

    public static void main(String[] args) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(101);
        enrollment.setFirstname("Jason");
        enrollment.setLastname("Smith");
        enrollment.setComment("Enrolled in 2016");
        enrollment.setNote("Notes: 1) 2) 3)");
        Phone phone1 = new Phone("(555) 999-1234", Phone.PhoneType.HOME);
        Phone phone2 = new Phone("(555) 888-1234", Phone.PhoneType.MOBILE);
        enrollment.getPhones().add(phone1);
        enrollment.getPhones().add(phone2);
        Address address1 = new Address("1234 E 1st ST", "Tempe", "PA", "20030", Address.AddressType.HOME);
        Address address2 = new Address("8080 S 2nd ST", "Chandler", "PA", "20033", Address.AddressType.WORK);
        enrollment.getAddresses().add(address1);
        enrollment.getAddresses().add(address2);

        AuditUtil auditUtil = new AuditUtil();
        auditUtil.handleFields(enrollment);
        auditUtil.handleMethods(enrollment);
        System.out.println("getlastName: " + auditUtil.getValueFromAuditMethod(enrollment, "getLastname"));
        System.out.println("comment: " + auditUtil.getValueFromAuditField(enrollment, "comment"));
    }
}