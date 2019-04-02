/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CT;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yi
 */
@Entity
@Table(name = "CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findByUsername", query = "SELECT c FROM Credential c WHERE c.username = :username")
    , @NamedQuery(name = "Credential.findByPassword", query = "SELECT c FROM Credential c WHERE c.password = :password")
    , @NamedQuery(name = "Credential.findByUserid", query = "SELECT c FROM Credential c WHERE c.userId.userId = :userId")
    , @NamedQuery(name = "Credential.findBySdate", query = "SELECT c FROM Credential c WHERE c.sdate = :sdate")})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "SDATE")
    @Temporal(TemporalType.DATE)
    private Date sdate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Appuser userId;

    public Credential() {
    }

    public Credential(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Appuser getUserId() {
        return userId;
    }

    public void setUserId(Appuser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CT.Credential[ username=" + username + " ]";
    }
    
}
