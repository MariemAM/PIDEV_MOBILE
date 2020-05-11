
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author rejeb
 */
public class UserSession {

   
    private static UserSession          instance = null;
    private User                        user     = null;
    private final Map<Produit, Integer> panier   = new HashMap<>();
    private float total=0;
    public UserSession() {}

    private UserSession(User userConnected) {
        this.user = userConnected;
    }

    public float getTotal() {
        return total;
    }

    public void addToPanier(Produit Produit, int qte) {
        if (panier.containsKey(Produit)) {
            panier.put(Produit, panier.get(Produit) + qte); 
            
        } else {
            panier.put(Produit, qte);
        }
        this.total+=Produit.getPrix()*qte;
    }

    public void removeFromPanier(Produit Produit) {
        if (panier.containsKey(Produit)) {
            int qte=panier.get(Produit);
            panier.remove(Produit);
            this.total-=Produit.getPrix()*qte;
        }
    }
    public void newPanier(){
        panier.clear();
        this.total=0;
    }
    public void validerPanier(String tel,String address){
        if(panier.isEmpty())
            return;
        JSONArray ja= new JSONArray();
   
        for(Map.Entry<Produit, Integer> e:panier.entrySet())
        {
            JSONObject json= new JSONObject();
            try {
                json.put("produit", e.getKey().getId());
                 json.put("quantite", e.getValue());
            } 
            catch (JSONException ex) {   
            }
            ja.put(json);
        }
        JSONObject json= new JSONObject();
    
        try {
            json.put("data",ja);    
            json.put("tel", tel);
            json.put("address", address);
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
        }
        ConnectionRequest post = new ConnectionRequest(){
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(json.toString().getBytes("UTF-8"));
            }  
        };
        post.setUrl(Statics.BASE_URL+"/order/");
        post.setPost(true);
        post.setContentType("application/json");
        NetworkManager.getInstance().addToQueueAndWait(post);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public final static UserSession getInstance(User userConnected) {
        if (UserSession.instance == null) {
            UserSession.instance = new UserSession(userConnected);
        }

        return UserSession.instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public Map<Produit, Integer> getPanier() {
        return panier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class getInstance {
        public getInstance() {}
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
