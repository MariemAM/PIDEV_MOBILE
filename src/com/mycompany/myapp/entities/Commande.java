
package com.mycompany.myapp.entities;


import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


/**
 *
 * @author rejeb
 */
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
  
    private Integer userId;
    
    private Double prixTotal;
    
    private Date date;
 
    private Date dateLivraison;
    
    private String status;
    
    private String chargeId;

    private String addresse;

    private String tel;
    private List<LigneCommande> lcs= new ArrayList<>();
 
    public Commande() {
    }

    public Commande(Integer id, Integer userId, Double prixTotal, Date date, Date dateLivraison, String status, String chargeId, String addresse, String tel) {
        this.id = id;
        this.userId = userId;
        this.prixTotal = prixTotal;
        this.date = date;
        this.dateLivraison = dateLivraison;
        this.status = status;
        this.chargeId = chargeId;
        this.addresse = addresse;
        this.tel = tel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }
    public void addLcs(LigneCommande lc){
        this.lcs.add(lc);
    }
    public List<LigneCommande> getLcs(){
        return this.lcs;
    }
    public Commande(Integer id, Date date, Date dateLivraison, String status, String addresse, String tel) {
        this.id = id;
        this.date = date;
        this.dateLivraison = dateLivraison;
        this.status = status;
        this.addresse = addresse;
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", userId=" + userId + ", prixTotal=" + prixTotal + ", date=" + date + ", dateLivraison=" + dateLivraison + ", status=" + status + ", chargeId=" + chargeId + ", addresse=" + addresse + ", tel=" + tel + ", lcs=" + lcs + '}';
    }
    
    


}