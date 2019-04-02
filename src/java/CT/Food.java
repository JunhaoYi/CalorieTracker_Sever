/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CT;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yi
 */
@Entity
@Table(name = "FOOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
    , @NamedQuery(name = "Food.findByFoodId", query = "SELECT f FROM Food f WHERE f.foodId = :foodId")
    , @NamedQuery(name = "Food.findByFoodname", query = "SELECT f FROM Food f WHERE f.foodname = :foodname")
    , @NamedQuery(name = "Food.findByCategory", query = "SELECT f FROM Food f WHERE f.category = :category")
    , @NamedQuery(name = "Food.findByCalAmount", query = "SELECT f FROM Food f WHERE f.calAmount = :calAmount")
    , @NamedQuery(name = "Food.findBySerUnit", query = "SELECT f FROM Food f WHERE f.serUnit = :serUnit")
    , @NamedQuery(name = "Food.findBySerAmount", query = "SELECT f FROM Food f WHERE f.serAmount = :serAmount")
    , @NamedQuery(name = "Food.findByFat", query = "SELECT f FROM Food f WHERE f.fat = :fat")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FOOD_ID")
    private Integer foodId;
    @Size(max = 50)
    @Column(name = "FOODNAME")
    private String foodname;
    @Size(max = 30)
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "CAL_AMOUNT")
    private Integer calAmount;
    @Size(max = 30)
    @Column(name = "SER_UNIT")
    private String serUnit;
    @Column(name = "SER_AMOUNT")
    private Integer serAmount;
    @Column(name = "FAT")
    private Integer fat;
    @OneToMany(mappedBy = "foodId")
    private Collection<Consumption> consumptionCollection;

    public Food() {
    }

    public Food(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCalAmount() {
        return calAmount;
    }

    public void setCalAmount(Integer calAmount) {
        this.calAmount = calAmount;
    }

    public String getSerUnit() {
        return serUnit;
    }

    public void setSerUnit(String serUnit) {
        this.serUnit = serUnit;
    }

    public Integer getSerAmount() {
        return serAmount;
    }

    public void setSerAmount(Integer serAmount) {
        this.serAmount = serAmount;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
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
        hash += (foodId != null ? foodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodId == null && other.foodId != null) || (this.foodId != null && !this.foodId.equals(other.foodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CT.Food[ foodId=" + foodId + " ]";
    }
    
}
