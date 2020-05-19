/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.Image;

/**
 *
 * @author Mariem
 */
public class Produit_Promo {
    private Integer           id;
    private String            nom;
    private Integer           qte;
    private Integer           prix;
    private Float            prix_promo;
    private String            desc;
    private Image             Image;
    private String t[] ;

    public Produit_Promo() {
    }

    public Produit_Promo(Integer id, String nom, Integer prix, Float prix_promo, String[] t) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prix_promo = prix_promo;
        this.t = t;
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image Image) {
        this.Image = Image;
    }
    
    
    

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Integer getQte() {
        return qte;
    }

    public Integer getPrix() {
        return prix;
    }

    public Float getPrix_promo() {
        return prix_promo;
    }

    public String getDesc() {
        return desc;
    }

    public String[] getT() {
        return t;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public void setPrix_promo(Float prix_promo) {
        this.prix_promo = prix_promo;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setT(String[] t) {
        this.t = t;
    }
   
    
}
