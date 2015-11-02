/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.moonwave.jpa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oracle
 */
@Entity
@Table(name = "dept_emp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeptEmp.findAll", query = "SELECT d FROM DeptEmp d"),
    @NamedQuery(name = "DeptEmp.findByEmpNo", query = "SELECT d FROM DeptEmp d WHERE d.deptEmpPK.empNo = :empNo"),
    @NamedQuery(name = "DeptEmp.findByDeptNo", query = "SELECT d FROM DeptEmp d WHERE d.deptEmpPK.deptNo = :deptNo"),
    @NamedQuery(name = "DeptEmp.findByFromDate", query = "SELECT d FROM DeptEmp d WHERE d.fromDate = :fromDate"),
    @NamedQuery(name = "DeptEmp.findByToDate", query = "SELECT d FROM DeptEmp d WHERE d.toDate = :toDate")})
public class DeptEmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DeptEmpPK deptEmpPK;

    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Basic(optional = false)
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;

    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Departments departments;

    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employees employees;

    public DeptEmp() {
    }

    public DeptEmp(DeptEmpPK deptEmpPK) {
        this.deptEmpPK = deptEmpPK;
    }

    public DeptEmp(DeptEmpPK deptEmpPK, Date fromDate, Date toDate) {
        this.deptEmpPK = deptEmpPK;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public DeptEmp(int empNo, String deptNo) {
        this.deptEmpPK = new DeptEmpPK(empNo, deptNo);
    }

    public DeptEmpPK getDeptEmpPK() {
        return deptEmpPK;
    }

    public void setDeptEmpPK(DeptEmpPK deptEmpPK) {
        this.deptEmpPK = deptEmpPK;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptEmpPK != null ? deptEmpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptEmp)) {
            return false;
        }
        DeptEmp other = (DeptEmp) object;
        if ((this.deptEmpPK == null && other.deptEmpPK != null) || (this.deptEmpPK != null && !this.deptEmpPK.equals(other.deptEmpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.moonwave.jpa.model.DeptEmp[ deptEmpPK=" + deptEmpPK + " ]";
    }
    
}
