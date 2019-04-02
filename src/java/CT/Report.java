/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CT;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yi
 */
@Entity
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByUserId", query = "SELECT r FROM Report r WHERE r.reportPK.userId = :userId")
    , @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.reportPK.date = :date")
    , @NamedQuery(name = "Report.findByCalC", query = "SELECT r FROM Report r WHERE r.calC = :calC")
    , @NamedQuery(name = "Report.findByCalB", query = "SELECT r FROM Report r WHERE r.calB = :calB")
    , @NamedQuery(name = "Report.findByTotalStep", query = "SELECT r FROM Report r WHERE r.totalStep = :totalStep")
    , @NamedQuery(name = "Report.findByCalG", query = "SELECT r FROM Report r WHERE r.calG = :calG")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReportPK reportPK;
    @Column(name = "CAL_C")
    private Integer calC;
    @Column(name = "CAL_B")
    private Integer calB;
    @Column(name = "TOTAL_STEP")
    private Integer totalStep;
    @Column(name = "CAL_G")
    private Integer calG;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Appuser appuser;

    public Report() {
    }

    public Report(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Report(int userId, Date date) {
        this.reportPK = new ReportPK(userId, date);
    }

    public ReportPK getReportPK() {
        return reportPK;
    }

    public void setReportPK(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Integer getCalC() {
        return calC;
    }

    public void setCalC(Integer calC) {
        this.calC = calC;
    }

    public Integer getCalB() {
        return calB;
    }

    public void setCalB(Integer calB) {
        this.calB = calB;
    }

    public Integer getTotalStep() {
        return totalStep;
    }

    public void setTotalStep(Integer totalStep) {
        this.totalStep = totalStep;
    }

    public Integer getCalG() {
        return calG;
    }

    public void setCalG(Integer calG) {
        this.calG = calG;
    }

    public Appuser getAppuser() {
        return appuser;
    }

    public void setAppuser(Appuser appuser) {
        this.appuser = appuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportPK != null ? reportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportPK == null && other.reportPK != null) || (this.reportPK != null && !this.reportPK.equals(other.reportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CT.Report[ reportPK=" + reportPK + " ]";
    }
    
}
