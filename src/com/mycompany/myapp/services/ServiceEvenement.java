/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.utils.Statics;
//Serimport java.awt.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yosrio
 */
public class ServiceEvenement {
    public ArrayList<Evenement> events;
    
    
    public static ServiceEvenement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    
    
    public ServiceEvenement() {
         req = new ConnectionRequest();
    }

    public static  ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new  ServiceEvenement();
        }
        return instance;
    }
public ArrayList<Evenement> getMyEventList() {
        ArrayList<Evenement> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/web/app_dev.php/api/evenement");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Evenement task = new Evenement();
                        task.setNom(obj.get("nom").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setNbre_places((int) Float.parseFloat(obj.get("nbrePlaces").toString()));    
                        task.setId((int) Float.parseFloat(obj.get("id").toString()));
                        //float id = Float.parseFloat(obj.get("id").toString()); 
                         //  e.setImage(obj.get("image").toString());
                        task.setPhoto((String)obj.get("photo"));
                        Map<String, Object> listRecupeventuser = null;
                        listRecupeventuser = (Map<String, Object>) obj.get("user");
                        Map<String, Object> date = (Map<String, Object>) obj.get("dateDebut");
                         float time=Float.parseFloat(date.get("timestamp").toString());
                         task.setDate_debut(new Date((long)time*1000));
                          Map<String, Object> date1 = (Map<String, Object>) obj.get("dateFin");
                         float time1=Float.parseFloat(date1.get("timestamp").toString());
                         task.setDate_fin(new Date((long)time1*1000));
                        //String S=(String) listRecupeventuser.get("username");
                         // task.setUsername(S);
                        
                        task.setUser_id((int) Float.parseFloat(listRecupeventuser.get("id").toString()));
                        listEvents.add(task);
                    }
                    
                    
                    System.out.println(tasks);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
public ArrayList<Evenement> getEventSingle(Evenement e){
       
         
         String url = "http://localhost/pidev/web/app_dev.php/api/evenement/show/"+ e.getId();      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }


    public boolean addEvent(Evenement t) {
        String url =  "http://localhost/pidev/web/app_dev.php/api/evenement/new?nom=" + t.getNom()+ "&description="
                + t.getDescription()+"&datedebut="+t.getDate_debut()+"&datefin="+t.getDate_fin()+"&photo="+t.getPhoto()
                +"&nbre_places="+t.getNbre_places();
        
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
    
    public boolean addEvent(Evenement e, String img) {
       

        MultipartRequest request = new MultipartRequest();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDateDebut = dateFormat.format(e.getDate_debut());
        String strDateFin = dateFormat.format(e.getDate_fin());
        
        String url = "http://localhost/pidev/web/app_dev.php/api/evenement/"+ e.getUser_id()+"/new?nom=" + e.getNom() 
                + "&description=" + e.getDescription() 
                + "&datedebut=" + strDateDebut 
                + "&datefin=" + strDateFin 
                + "&nbr_places=" + e.getNbre_places();
        request.setUrl(url);
        
        try {
            request.addData("fileUpload", img, "image/jpeg");
            request.setFilename("fileUpload", "myPicture.jpg");
            request.addArgument(img, url);
        } catch (IOException ex) {
           ex.printStackTrace();
           return false;
        }

        request.addResponseListener((ex) -> {
            String str = new String(request.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return true;
    }
}
