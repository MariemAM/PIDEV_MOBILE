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
import com.mycompany.myapp.entities.Produit_Promo;
import com.mycompany.myapp.utils.Statics_M;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mariem
 */
public class PromoteService {
    public ArrayList<Produit_Promo> Produits_promo;
    public static PromoteService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public PromoteService() {
        req = new ConnectionRequest();
    }
    public static PromoteService getInstance() {
        if (instance == null) {
            instance = new PromoteService();
        }
        return instance;
    }
    
     public ArrayList<Produit_Promo> parseTasks(String jsonText){
        try {
            Produits_promo=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit_Promo t = new Produit_Promo();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrix((int)Float.parseFloat(obj.get("prix").toString()));
                t.setPrix_promo((Float.parseFloat(obj.get("prix_promo").toString())));
                t.setDesc(obj.get("description").toString());
               // t.setImage(obj.get("photo"));
               
                Produits_promo.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return Produits_promo;
    }
    
     public ArrayList<Produit_Promo> getAllTasks(){
        String url = Statics_M.BASE_URL+"/pidev-merge/pidev-merge/web/app_dev.php/api/products_promo";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits_promo = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits_promo;
    }
    
}
