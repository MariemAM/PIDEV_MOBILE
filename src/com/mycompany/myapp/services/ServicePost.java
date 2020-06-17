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
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author ghofrane
 */
public class ServicePost {

    public ArrayList<Post> posts;
    
    public static ServicePost instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicePost() {
         req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }

    public boolean addPost(Post p) {
          String url = Statics.BASE_URL+"/api/newpost?aut="+p.getUser_id()+"&Contenu="+p.getContenu();
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

    
    public ArrayList<Post> parsePosts(String jsonText) throws ParseException{
        try {
         
            posts=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
            for(Map<String,Object> obj : list){
                Post p = new Post();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
              /*  float Date = Date.(obj.get("id").toString());*/
               Map<String,Object> us = (Map<String,Object>)obj.get("user");
               float userid = Float.parseFloat(us.get("id").toString());
                p.setUser_id((int)userid);
                p.setContenu(obj.get("contenu").toString());
               // p.setDateCreation(obj.get("dateCreation").toString());
                 Date date=this.format(obj.get("dateCreation").toString());
                p.setDateCreation(date);
                List like = new ArrayList();
                  like = (List) obj.get("jaimes");
                  int jaime =like.size();
                  p.setNbr(jaime);
                //p.setContenu(ServiceUser.getInstance().getAUser((int)userid).toString());
                posts.add(p);
            }
           
        } catch (IOException ex) {
            
        }
        return posts;
    }
   
    
    public ArrayList<Post> getAllPosts(){
          
        String url = Statics.BASE_URL+"/api/postall";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt)  {
                try {
                    posts = parsePosts(new String(req.getResponseData()));
                } catch (ParseException ex) {
                     System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
         
        NetworkManager.getInstance().addToQueueAndWait(req);
         return posts;
    }
     public ArrayList<Post> getUPosts(int id){
          
        String url = Statics.BASE_URL+"/api/post/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt)  {
                try {
                    posts = parsePosts(new String(req.getResponseData()));
                } catch (ParseException ex) {
                     System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
         
        NetworkManager.getInstance().addToQueueAndWait(req);
         return posts;
    }
     public boolean updatePost(int id,String contenu){
        String url = Statics.BASE_URL+"/api/edit/"+id+"?Contenu="+contenu;
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
     public boolean deletePost(int id){
        String url = Statics.BASE_URL+"/api/delete/"+id;
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
      private Date format(String input) throws ParseException{
         DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
                if ( input.endsWith( "Z" ) ) 
                {
                    input = input.substring( 0, input.length() - 1) + "GMT-00:00"; 
                } 
                else {
                    int inset = 6;

                    String s0 = input.substring( 0, input.length() - inset );
                    String s1 = input.substring( input.length() - inset, input.length() );

                    input = s0 + "GMT" + s1;
                }
                Date date=format.parse(input);
                return date;
     }
     
}
