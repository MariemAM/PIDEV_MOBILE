/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.io.Serializable;

/**
 *
 * @author arij
 */
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer           id;
    private String            nom;
    private Integer            produit;

    public Category(Integer id, String nom, Integer produit) {
        this.id = id;
        this.nom = nom;
        this.produit = produit;
    }

    public Category(String nom, Integer produit) {
        this.nom = nom;
        this.produit = produit;
    }

    public Integer getProduit() {
        return produit;
    }

    public void setProduit(Integer produit) {
        this.produit = produit;
    }
    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nom=" + nom + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public Category(Integer id) {
        this.id = id;
    }

    public Category() {
    }
    

    public Category(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
}
