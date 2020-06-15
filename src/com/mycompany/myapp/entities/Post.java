/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author ghofrane
 */
public class Post {
      private int id , user_id,nbr;
      private String contenu;
      private Date dateCreation;

   


    public Post(String contenu) {
        this.contenu = contenu;
       
    }

    public Post() {
    }

    public Post(int id, String contenu, Date dateCreation, int user_id) {
        this.id = id;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.user_id = user_id;
    }

    public Post(int user_id,String contenu) {
        this.user_id = user_id;
        this.contenu = contenu;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }



    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

 public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public int getNbr() {
        return nbr;
    }

    @Override
    public String toString() {
        return "post{" + "id=" + id + ", dateCreation=" + dateCreation + ", contenu=" + contenu + ", user=" + user_id + '}';
    }
    
}
