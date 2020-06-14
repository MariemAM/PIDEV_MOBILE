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
public class ProductServices {
    
    public ArrayList<Produit> prds;
    
    public static ProductServices instance=null;
    
    public boolean resultOK;
    
    private ConnectionRequest req;
    
    public ProductServices() {
         req = new ConnectionRequest();
    }
     
    public static ProductServices getInstance() {
        if (instance == null) {
            instance = new ProductServices();
        }
        return instance;
    }
     public ArrayList<Produit> parseProducts(String jsonText) throws ParseException {
        try {
            prds=new ArrayList<>();
            JSONParser j = new JSONParser();

            System.out.println(jsonText);
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
          
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
           
            for(Map<String,Object> obj : list){
                
                Produit c = new Produit();
              
                float id=Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
                
                float qte=Float.parseFloat(obj.get("qte").toString());
                c.setQte((int)qte);
                
               float prix=Float.parseFloat(obj.get("prix").toString());
               c.setPrix((int)prix);
                
               c.setDescription(obj.get("description").toString());
               c.setPhoto(obj.get("photo").toString());
                
                
                prds.add(c);
                
            }
     
        } catch (IOException ex) {
            
        }
        
        return prds;
    }
    public ArrayList<Produit> getAllProduct(){
        String url = Statics.BASE+"produit";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    prds = parseProducts(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return prds;
    }
     public boolean addProduit (Produit p) {
        String url = Statics.BASE+"newprod?nom="+p.getNom()+"&qte="+p.getQte()+"&prix="+p.getPrix()+"&prix_promo="+p.getPrix()+"&description="+p.getDescription()+"&photo="+p.getPhoto()+"&categorie="+p.getCategorie();
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
public boolean updateProduct(int id,String nom,int qte,int prix,String description,int categorie){
 String url = Statics.BASE+"editprod/"+id+"?nom="+nom+"&qte="+qte+"&prix="+prix+"&description="+description+"&categorie="+categorie;

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
     public boolean deleteProduit(int id){
        String url = Statics.BASE+"deletep/"+id;
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

public ArrayList<Produit> SearchProduct(String nom){
        String url = Statics.BASE+"find/"+nom;
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    prds = parseProducts(new String(req.getResponseData()));
                } catch (ParseException ex) {
                   
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prds;
    }
public ArrayList<Produit> getAProduct(int id){
        String url = Statics.BASE+"details/"+id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    prds = parseProducts(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return prds;
    }
    public ArrayList<Produit> POut(){
        String url = Statics.BASE+"findPOut";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    prds = parseProducts(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return prds;
    }
        public ArrayList<Produit> PByCat(String nom){
        String url = Statics.BASE+"findcats/"+nom;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    prds = parseProducts(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return prds;
           
    }
        
}
