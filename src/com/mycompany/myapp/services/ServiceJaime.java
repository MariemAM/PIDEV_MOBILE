/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Jaime;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ghofrane
 */
public class ServiceJaime  {
    
     public ArrayList<Jaime> jaimes;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceJaime() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addJaime(int user , int id) {
        String url = Statics.BASE_URL+"/api/newjaime?us="+user+"&post="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Jaime> parsejaimes(String jsonText){
        try {
            jaimes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
            for(Map<String,Object> obj : list){
                Jaime js = new Jaime();
                float id = Float.parseFloat(obj.get("id").toString());
                js.setId((int)id);
              /*  float Date = Date.(obj.get("id").toString());*/
               Map<String,Object> us = (Map<String,Object>)obj.get("user");
               float userid = Float.parseFloat(us.get("id").toString());
               js.setUser((int)userid);
               Map<String,Object> posts = (Map<String,Object>)obj.get("post");
               float post = Float.parseFloat(posts.get("id").toString());
               js.setPost((int)post);
                jaimes.add(js);
            }
            
        } catch (IOException ex) {
            
        }
        return jaimes;
    }
    
    public  ArrayList<Jaime> getlikes(int user, int post){
        String url = Statics.BASE_URL+"/api/jaimeu/"+user+"/"+post;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                jaimes = parsejaimes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return jaimes;
    }
       
     public boolean deleteJaime(int id){
        String url = Statics.BASE_URL+"/api/deletejaime/"+id;
        req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
