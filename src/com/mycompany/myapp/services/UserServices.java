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

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 *
 * @author Mariem
 */
public class UserServices {
    public static UserServices instance=null;
    private User u;
    public boolean resultOK;
    
    private ConnectionRequest req;
    
    private UserServices() {
         req = new ConnectionRequest();
    }
    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }
    public User parseUser(String jsonText) throws ParseException, IOException{
        User user=new User();
        JSONParser j = new JSONParser();
        Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        if(obj.get("id")==null) return null;
        float id = Float.parseFloat(obj.get("id").toString());
        user.setId((int)id);
        user.setUsername(obj.get("username").toString());
        user.setEmail(obj.get("email").toString());
        return user;
    }
    public User login(String uname,String pass){
        String url = "http://127.0.0.1:8000/api/login/"+uname+"/"+pass;
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    u = parseUser(new String(req.getResponseData()));
                    if(u!=null)
                        UserSession.getInstance(u);
                   
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return u;
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
