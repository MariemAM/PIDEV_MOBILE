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
    //private  Produit p;
    private Promotion promo;
    private int quantite;

    public LignePromotion() {

    }

    public LignePromotion(int id, Promotion promo, int quantite) {
        this.id = id;
        this.promo = promo;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public Promotion getPromo() {
        return promo;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LignePromotion{" + "id=" + id + ", promo=" + promo + ", quantite=" + quantite + '}';
    }

}
