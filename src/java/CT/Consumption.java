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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yi
 */
@Entity
@Table(name = "CONSUMPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumption.findAll", query = "SELECT c FROM Consumption c")
    , @NamedQuery(name = "Consumption.findByConId", query = "SELECT c FROM Consumption c WHERE c.conId = :conId")
    , @NamedQuery(name = "Consumption.findByUserId", query = "SELECT c FROM Consumption c WHERE c.userId.userId = :userId")
    , @NamedQuery(name = "Consumption.findByDate", query = "SELECT c FROM Consumption c WHERE c.date = :date")
    , @NamedQuery(name = "Consumption.findByFoodId", query = "SELECT c FROM Consumption c WHERE c.foodId.foodId = :foodId")
    , @NamedQuery(name = "Consumption.findByUseridAndDate2", query = "SELECT c FROM Consumption c WHERE c.userId.userId = :userid AND c.date = :date")
    , @NamedQuery(name = "Consumption.findByQuantity", query = "SELECT c FROM Consumption c WHERE c.quantity = :quantity")})
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CON_ID")
    private Integer conId;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Appuser userId;
    @JoinColumn(name = "FOOD_ID", referencedColumnName = "FOOD_ID")
    @ManyToOne
    private Food foodId;

    public Consumption() {
    }

    public Consumption(Integer conId) {
        this.conId = conId;
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Appuser getUserId() {
        return userId;
    }

    public void setUserId(Appuser userId) {
        this.userId = userId;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conId != null ? conId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.conId == null && other.conId != null) || (this.conId != null && !this.conId.equals(other.conId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CT.Consumption[ conId=" + conId + " ]";
    }
    
}
