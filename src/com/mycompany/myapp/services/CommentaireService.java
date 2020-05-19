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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author haifa
 */
public class CommentaireService {
     public ArrayList<Commentaire> getListTask(String json) {

        ArrayList<Commentaire> listuser = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

            for (Map<String, Object> obj : list) {
                Commentaire e = new Commentaire();

                
                float id = Float.parseFloat(obj.get("id").toString());
            
                e.setId((int) id);
                
                
                
                e.setContenu(obj.get("contenu").toString());
                //User u = new User(1);
                ///u.setUsername(obj.get("user").toString());
                //e.setUser_id(u.getId());
                
            
                listuser.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listuser;

    }
     ArrayList<Commentaire> listComments = new ArrayList<>();
    
    public ArrayList<Commentaire> getList2(int id){ 
        
       
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://localhost/pidev/web/app_dev.php/api/AfficherComm");
        
        con.addArgument("bid", String.valueOf(id));
        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentaireService ser = new CommentaireService();
                listComments = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComments;
    }
    
    public void addComment(Commentaire c){
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/api/AddComm";
        Url=Url+"?comment=" + c.getContenu()+"&id="+c.getGuide_id();
        con.setUrl(Url); 
        NetworkManager.getInstance().addToQueueAndWait(con);



    }
  /*  public void delete(Commentaire c , int bid , int uid) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://localhost/pidev/web/app_dev.php/api/DellCom/"+c.getId()+c.getGuide_id());
        con.addArgument("id", String.valueOf(c));

        NetworkManager.getInstance().addToQueueAndWait(con);
    }*/
    /*public void ajoutComm(Commentaire d) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/api/AddComm"+"contenu="+d.getContenu()+"/"+"guide=" + d.getGuide_id() ;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }*/
    
   /* public void supprimerDoc(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/api/DellCom/"+id;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            
            System.out.println(str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }*/
     public void supprimercom(Commentaire e) {
        ConnectionRequest con = new ConnectionRequest();
         System.out.println(e.getId());
        String Url = "http://localhost/piweb-master/web/app_dev.php/api/deleteTopMob/" + e.getId();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "commentaire supprimé", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    

     
}
