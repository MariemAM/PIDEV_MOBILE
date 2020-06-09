/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Mariem
 */
public class LignePromotion {

    private int id;
    private  int idpdt;
    private int qtepdt; 
    private int qtepromo;
    private String nompdt;
    // private int prixprom
    //private Produit_Promo p;
    //private Promotion promo;
    
 public LignePromotion() {

    }

    public LignePromotion(int id, Promotion promo, int quantite) {
        this.id = id;
        //this.promo = promo;
        this.qtepromo = quantite;
        
    }

    public void setNompdt(String nompdt) {
        this.nompdt = nompdt;
    }

    public String getNompdt() {
        return nompdt;
    }
    
    public int getIdpdt() {
        return idpdt;
    }

    public int getQtepdt() {
        return qtepdt;
    }

    public void setIdpdt(int idpdt) {
        this.idpdt = idpdt;
    }

    public void setQtepdt(int qtepdt) {
        this.qtepdt = qtepdt;
    }
    
  
    

   

//    public void setP(Produit_Promo p) {
//        this.p = p;
//    }

    public void setQtepromo(int qtepromo) {
        this.qtepromo = qtepromo;
    }

//    public Produit_Promo getP() {
//        return p;
//    }

    public int getQtepromo() {
        return qtepromo;
    }

    public int getId() {
        return id;
    }

//    public Promotion getPromo() {
//        return promo;
//    }

    

    public void setId(int id) {
        this.id = id;
    }

//    public void setPromo(Promotion promo) {
//        this.promo = promo;
//    }

   

    @Override
    public String toString() {
        return "LignePromotion{" + "id=" + id + ", quantite=" + qtepromo + '}';
    }

    
}
