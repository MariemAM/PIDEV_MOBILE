/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Mariem
 */
public class Promotion {

    private int id;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private int tauxReduction;

    public Promotion() {
    }

    public Promotion(int id, String nom, Date dateDebut, Date dateFin, int tauxReduction) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tauxReduction = tauxReduction;
    }

 

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

   

   

   

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(int tauxReduction) {
        this.tauxReduction = tauxReduction;
    }


}
