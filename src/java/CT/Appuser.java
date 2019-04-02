/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CT;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yi
 */
@Entity
@Table(name = "APPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appuser.findAll", query = "SELECT a FROM Appuser a")
    , @NamedQuery(name = "Appuser.findByUserId", query = "SELECT a FROM Appuser a WHERE a.userId = :userId")
    , @NamedQuery(name = "Appuser.findByFirstname", query = "SELECT a FROM Appuser a WHERE a.firstname = :firstname")
    , @NamedQuery(name = "Appuser.findBySurname", query = "SELECT a FROM Appuser a WHERE a.surname = :surname")
    , @NamedQuery(name = "Appuser.findByEmail", query = "SELECT a FROM Appuser a WHERE a.email = :email")
    , @NamedQuery(name = "Appuser.findByDob", query = "SELECT a FROM Appuser a WHERE a.dob = :dob")
    , @NamedQuery(name = "Appuser.findByHeight", query = "SELECT a FROM Appuser a WHERE a.height = :height")
    , @NamedQuery(name = "Appuser.findByWeight", query = "SELECT a FROM Appuser a WHERE a.weight = :weight")
    , @NamedQuery(name = "Appuser.findByGender", query = "SELECT a FROM Appuser a WHERE a.gender = :gender")
    , @NamedQuery(name = "Appuser.findByAddress", query = "SELECT a FROM Appuser a WHERE a.address = :address")
    , @NamedQuery(name = "Appuser.findByPostcode", query = "SELECT a FROM Appuser a WHERE a.postcode = :postcode")
    , @NamedQuery(name = "Appuser.findByLoa", query = "SELECT a FROM Appuser a WHERE a.loa = :loa")
    , @NamedQuery(name = "Appuser.findBySpm", query = "SELECT a FROM Appuser a WHERE a.spm = :spm")})
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    @Size(max = 30)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 30)
    @Column(name = "SURNAME")
    private String surname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Size(max = 10)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 4)
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "LOA")
    private Integer loa;
    @Column(name = "SPM")
    private Integer spm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Credential> credentialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
    private Collection<Report> reportCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Consumption> consumptionCollection;

    public Appuser() {
    }

    public Appuser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getLoa() {
        return loa;
    }

    public void setLoa(Integer loa) {
        this.loa = loa;
    }

    public Integer getSpm() {
        return spm;
    }

    public void setSpm(Integer spm) {
        this.spm = spm;
    }
    

    @XmlTransient
    public Collection<Credential> getCredentialCollection() {
        return credentialCollection;
    }

    public void setCredentialCollection(Collection<Credential> credentialCollection) {
        this.credentialCollection = credentialCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuser)) {
            return false;
        }
        Appuser other = (Appuser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CT.Appuser[ userId=" + userId + " ]";
    }
    
}
