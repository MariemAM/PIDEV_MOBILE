/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Yosrio
 */
public class ListeParticipants implements Serializable  {
    
     private int id;
    private Evenement evenement;
    private user user;
    private Date dateparticip;

    public ListeParticipants() {
    }

    public ListeParticipants(int id, Evenement evenement, user user, Date dateparticip) {
        this.id = id;
        this.evenement = evenement;
        this.user = user;
        this.dateparticip = dateparticip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Date getDateparticip() {
        return dateparticip;
    }

    public void setDateparticip(Date dateparticip) {
        this.dateparticip = dateparticip;
    }

    @Override
    public String toString() {
        return "ListeParticipants{" + "id=" + id + ", evenement=" + evenement + ", user=" + user + ", dateparticip=" + dateparticip + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListeParticipants other = (ListeParticipants) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
