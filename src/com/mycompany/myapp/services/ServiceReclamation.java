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
import com.mycompany.myapp.entities.Reclamation;
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
public class ServiceReclamation {
     public ArrayList<Reclamation> claims;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addReclamation(Reclamation r) {
        String url = Statics.BASE_URL+"/api/newclaim?cont="+r.getContenu()+"&obj="+r.getObject()+"&us="+r.getUser_id()+"&target="+r.getReclamer();
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

    public ArrayList<Reclamation> parseReclamation(String jsonText) throws ParseException{
        try {
            claims=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation r = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
              /*  float Date = Date.(obj.get("id").toString());*/
               Map<String,Object> us = (Map<String,Object>)obj.get("user");
               float userid = Float.parseFloat(us.get("id").toString());
               r.setUser_id((int)userid);
               Map<String,Object> tr = (Map<String,Object>)obj.get("reclamer");
               float target = Float.parseFloat(tr.get("id").toString());
               r.setReclamer((int)target);
               r.setContenu(obj.get("contenu").toString());
               r.setObject(obj.get("objet").toString());
               Date date=this.format(obj.get("date").toString());
               r.setDate(date);
              
              
                
               
                claims.add(r);
            }
            
        } catch (IOException ex) {
            
        }
        return claims;
    }
    
    public ArrayList<Reclamation> getAllClaims(int id){
        String url = Statics.BASE_URL+"/api/claims/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    claims = parseReclamation(new String(req.getResponseData()));
                } catch (ParseException ex) {
                     System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return claims;
    }
         public boolean updateClaim(int id,String contenu,String object){
        String url = Statics.BASE_URL+"/api/editclaim/"+id+"?cont="+contenu+"&obj"+object;
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
     public boolean deleteClaim(int id){
        String url = Statics.BASE_URL+"/api/deleteclaim/"+id;
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
