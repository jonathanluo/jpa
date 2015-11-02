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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author oracle
 */
@Embeddable
public class TitlesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "emp_no")
    private int empNo;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    public TitlesPK() {
    }

    public TitlesPK(int empNo, String title, Date fromDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empNo;
        hash += (title != null ? title.hashCode() : 0);
        hash += (fromDate != null ? fromDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TitlesPK)) {
            return false;
        }
        TitlesPK other = (TitlesPK) object;
        if (this.empNo != other.empNo) {
            return false;
        }
        if ((this.title == null && other.title != null) || (this.title != null && !this.title.equals(other.title))) {
            return false;
        }
        if ((this.fromDate == null && other.fromDate != null) || (this.fromDate != null && !this.fromDate.equals(other.fromDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.moonwave.jpa.model.TitlesPK[ empNo=" + empNo + ", title=" + title + ", fromDate=" + fromDate + " ]";
    }
    
}
