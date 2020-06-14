/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.entities.Evenement;

/**
 *
 * @author Yosrio
 */
public class ServiceParticipation {
    public void participer(Evenement e) {
        
        System.out.println("Participation Evenement: "+e.getId());
        //String userId = Statics.userId.toString();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/api/evenement/inscrire/"
                + e.getId() + "/" + e.getUser_id();
        con.setUrl(Url);
        con.setPost(false);

        con.addResponseListener((k) -> {
            String str = new String(con.getResponseData());
            System.out.print("Response: ");
            System.out.println(str);

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
     public void Annuler(Evenement e) {
         System.out.println("annuler Participation Evenement: "+e.getId());
        //String userId = Statics.userId.toString();
        ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/pidev/web/app_dev.php/api/evenement/annuler/"
                + e.getId() + "/" + e.getUser_id();
        con.setUrl(Url);
        con.setPost(false);

        con.addResponseListener((k) -> {
            String str = new String(con.getResponseData());
            System.out.print("Response: ");
            System.out.println(str);

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
