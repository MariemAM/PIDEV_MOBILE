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
   // private Integer            produit;

    public Category(String nom) {
        this.nom = nom;
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
