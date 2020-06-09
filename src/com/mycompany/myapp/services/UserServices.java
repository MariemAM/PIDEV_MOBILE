/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics_M;

/**
 *
 * @author Mariem
 */
public class UserServices {
    public static UserServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public UserServices() {
         req = new ConnectionRequest();
    }
    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }
    public boolean addUser(User  u){
        String url = Statics_M.BASE_URL + "/pidevmerge/web/app_dev.php/api/newuser?nom="+u.getUsername()+"&prenom="+u.getUsernameCanonical()+"&email="+u.getEmail()+"&pass="+u.getPassword();//+"&date="+u.getLastLogin();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
    
}
