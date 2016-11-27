package org.mw.main;

import org.mw.entity.Enrollment;
import org.mw.util.AuditUtil;

public class EnrollmentTest {

    public static void main(String[] args) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(101);
        enrollment.setFirstname("Jason");
        enrollment.setLastname("Smith");
        enrollment.setComment("Enrolled in 2016");
        enrollment.setNote("Notes: 1) 2) 3)");

        AuditUtil auditUtil = new AuditUtil();
        auditUtil.handleFields(enrollment);
        System.out.println("=====================================================");
        auditUtil.handleMethods(enrollment);
        System.out.println("getlastName: " + auditUtil.getValueFromAuditMethod(enrollment, "getLastname"));
        System.out.println("comment: " + auditUtil.getValueFromAuditField(enrollment, "comment"));
    }
}