
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
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.User;
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
 * @author rejeb
 */
public class PromoServices {
    public ArrayList<Promotion> promos;
     public ArrayList<User> users;
    public static  PromoServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private PromoServices() {
        req = new ConnectionRequest();
    }
    public static PromoServices getInstance() {
        if (instance == null) {
            instance = new PromoServices();
        }
        return instance;
    }
     public ArrayList<Promotion> parseTasks(String jsonText) throws ParseException{
        try {
            promos=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Promotion t = new Promotion();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                Date datedebut;
                datedebut = this.formate(obj.get("dateDebut").toString());
                t.setDateDebut(datedebut);
                Date dateFin;
                dateFin = this.formate(obj.get("dateFin").toString());
                t.setDateFin(dateFin);
                t.setTauxReduction((int)Float.parseFloat(obj.get("tauxReduction").toString()));
                promos.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return promos;
    }
     
     public ArrayList<Promotion> getAllTasks(){
       // String url = Statics_M.BASE_URL+"/pidevmerge/web/app_dev.php/api/all/Promotion";
       String url = Statics.BASE+"promotion/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            
            public void actionPerformed(NetworkEvent evt) {
                try {
                    promos = parseTasks(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.out.println("Parse Error "+ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return promos;
    }
      public Date formate(String input) throws ParseException{
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
      public String df(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String strDate= formatter.format(d);  
        System.out.println(strDate); 
        return strDate;
      }
       public boolean updatePromotion(Promotion p){
        String url = Statics.BASE + "promo/update/"+p.getId()+"?"+"nom="+p.getNom()+"&rate="+p.getTauxReduction()+"&dateDebut="+df(p.getDateDebut())+"&dateFin="+df(p.getDateFin());
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
       public boolean createPromotion(String nom,int tx,Date start,Date end ){//Date d1,Date d2
        String url = Statics.BASE+ "newpromo?nom="+nom+"&rate="+tx+"&dateDebut="+df(start)+"&dateFin="+df(end);
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
       
       
     public boolean deletePromotion(Promotion  p){
        String url = Statics.BASE + "promo/supprimer/"+p.getId();
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
     
      public ArrayList<User> getAPImessage(){
       // String url = Statics_M.BASE_URL+"/pidevmerge/web/app_dev.php/api/all/Promotion";
       String url = Statics.BASE+"getphonenumber";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            
            public void actionPerformed(NetworkEvent evt) {
                try {
                    users = parseUser(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.out.println("Parse Error "+ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
       public ArrayList<User> parseUser(String jsonText) throws ParseException{
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                User t = new User();
                t.setPhone(obj.get("tel").toString());
            
                users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return users;
    }
    
}



