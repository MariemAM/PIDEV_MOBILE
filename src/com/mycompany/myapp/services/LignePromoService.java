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
import com.mycompany.myapp.entities.LignePromotion;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mariem
 */
public class LignePromoService {
    public ArrayList<LignePromotion> lgpromo;
   
    
    public static LignePromoService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private LignePromoService() {
         req = new ConnectionRequest();
    }
    public  static LignePromoService getInstance(){
        if(instance==null){
        instance=new LignePromoService();}
    return instance;
    }
    public ArrayList<LignePromotion> parseTasks(String jsonText){
        try {
            lgpromo=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                 LignePromotion lg=new LignePromotion();
                 
                 lg.setId((int)Float.parseFloat(obj.get("id").toString()));
                 lg.setQtepromo((int)Float.parseFloat(obj.get("quantite").toString()));
                 
                //Produit_Promo t = new Produit_Promo();
                Map<String,Object> pdt = (Map<String,Object>) obj.get("produit");
                
                lg.setIdpdt((int)Float.parseFloat(pdt.get("id").toString()));
                
               lg.setNompdt(pdt.get("nom").toString());
                lg.setQtepdt((int)Float.parseFloat(pdt.get("qte").toString()));
               
                lgpromo.add(lg);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return lgpromo;
    } 
    
    
     public ArrayList<LignePromotion> parselignepromo(String jsonText){
        try {
            lgpromo=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                 LignePromotion lg=new LignePromotion();
                 
                lg.setId((int)Float.parseFloat(obj.get("id").toString()));
                lgpromo.add(lg);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return lgpromo;
    } 
    
    
      public boolean UpdateQuantity(int id,int qtepdt,int qtepromo){
         String url = Statics.BASE+"quantitysupdate/"+id+"?"+"qtepdt="+qtepdt+"&qtepromo="+qtepromo;
        req.setUrl(url);
        req.setPost(false);
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
       public ArrayList<LignePromotion> createLigne(int idpdt,int idpromo){
         String url = Statics.BASE+"newligne/"+idpdt+"/"+idpromo;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // resultOK = req.getResponseCode() == 200;
                 lgpromo = parselignepromo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      return lgpromo;
      
    }
        public ArrayList<LignePromotion> getAllTasks(int id){
        String url = Statics.BASE+"promo/products/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lgpromo = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lgpromo;
    }
     
     
}
