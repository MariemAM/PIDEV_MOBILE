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
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author arij
 */
public class CategoryServices {
    public ArrayList<Category> ctgs;
    
    public static CategoryServices instance=null;
    
    public boolean resultOK;
    
    private ConnectionRequest req;
    
    public CategoryServices() {
         req = new ConnectionRequest();
    }
     
    public static CategoryServices getInstance() {
        if (instance == null) {
            instance = new CategoryServices();
        }
        return instance;
    }
    public ArrayList<Category> parseCategorys(String jsonText) throws ParseException {
        try {
            ctgs=new ArrayList<>();
            JSONParser j = new JSONParser();

            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
           
            for(Map<String,Object> obj : list){
                
                Category c = new Category();
                float id=Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
               
                
            
   
                
                ctgs.add(c);
                
            }
     
        } catch (IOException ex) {
            
        }
        
        return ctgs;
    }
    
    public ArrayList<Category> getAllCategory(){
        String url = Statics.BASE_URL+"/cat";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    ctgs = parseCategorys(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return ctgs;
    }
    public boolean addCategory (String nom) {
        String url = Statics.BASE_URL+"/newcat/"+nom;
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
    public boolean updateCategory(int id,String nom){
        String url = Statics.BASE_URL+"/editcat/"+id+"?nom="+nom;
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
    public boolean deleteCategory(int id){
        String url = Statics.BASE_URL+"/deletecat/"+id;
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
     public ArrayList<Category> findCat(String nom){
         Category c = new Category();
        String url = Statics.BASE_URL+"/findc/"+nom;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    ctgs = parseCategorys(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return ctgs;
           
    }
}
