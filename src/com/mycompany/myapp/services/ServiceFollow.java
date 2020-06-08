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
import com.mycompany.myapp.entities.Follow;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ghofrane
 */
public class ServiceFollow {
      public ArrayList<Follow> follow; 
    public static ServicePost instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFollow() {
         req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }

    public boolean addFollow(Follow f) {
        String url = Statics.BASE_URL+"/api/newfollow?flw="+f.getFollower()+"&fld="+f.getFollowed();
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
     public ArrayList<Follow> parseFollow(String jsonText){
        try {
         
            follow=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
                for(Map<String,Object> obj : list){
                   
                Follow f = new Follow();
                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int)id);
                Map<String,Object> follower = (Map<String,Object>)obj.get("follower");
                float flw = Float.parseFloat(follower.get("id").toString());
                f.setFollower((int)flw);
                Map<String,Object> followed = (Map<String,Object>)obj.get("followed");
                float fld = Float.parseFloat(followed.get("id").toString());
                f.setFollowed((int)fld);
                follow.add(f);
                    }
            
            
           
        } catch (IOException ex) {
            
        }
        return follow;
    }
     
    public  ArrayList<Follow> getFollows(int follower, int followed){
        String url = Statics.BASE_URL+"/api/followu/"+follower+"/"+followed;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                follow = parseFollow(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return follow;
    }
      public  ArrayList<Follow> getUFollows(int follower){
        String url = Statics.BASE_URL+"/api/followl/"+follower;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                follow = parseFollow(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return follow;
    }
      
      public  ArrayList<Follow> deletelFollows(int follower, int followed){
        String url = Statics.BASE_URL+"/api/deletef/"+follower+"/"+followed;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                follow = parseFollow(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return follow;
    }
     
      
      
      
     public boolean deleteFollow(int id){
        String url = Statics.BASE_URL+"/api/deletefollow/"+id;
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
