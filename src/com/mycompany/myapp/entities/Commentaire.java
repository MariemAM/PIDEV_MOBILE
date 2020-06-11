/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author haifa
 */
public class Commentaire implements Serializable {
    private int id;
    private int guide_id;
    private int user_id;
    private String contenu;
    private String date;
    private String nom;
   private List<Guide> lcs= new ArrayList<>();
   private List<User> lcu= new ArrayList<>();
    public Commentaire() {
    }

   /* public Commentaire(int id, int guide_id, int user_id, String contenu) {
        this.id = id;
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
    }*/

    public Commentaire(int guide_id, int user_id, String contenu) {
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
    }

    public Commentaire(int guide_id, int user_id, String contenu, String date) {
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire(int id, int guide_id, int user_id, String contenu, String date) {
        this.id = id;
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire(int id, int guide_id, int user_id, String contenu) {
        this.id = id;
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.contenu = contenu;
    }

    public Commentaire(int id, int guide_id, int user_id) {
        this.id = id;
        this.guide_id = guide_id;
        this.user_id = user_id;
    }
    
public void addLcs(Guide lc){
        this.lcs.add(lc);
    }
public List<Guide> getLcs(){
        return this.lcs;
    }
    public void addLcu(User lu){
        this.lcu.add(lu);
    }
public List<User> getLcu(){
        return this.lcu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }
    public String setDate(String date){
      return  this.date=date;
    }

    public void String(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", guide_id=" + guide_id + ", user_id=" + user_id + ", contenu=" + contenu + ", date=" + date + '}';
    }
    
}
