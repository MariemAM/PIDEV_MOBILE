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
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author haifa
 */
public class CommentaireService {
    Guide g;
    User u =new User();
    public ArrayList<Commentaire> comment;
    UserSession us=UserSession.getInstance(u);
    User f = UserSession.getInstance().getUser();
    // User u = MyApplication.currentUser;
     public ArrayList<Commentaire> getListTask(String json){

        comment=new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

            for (Map<String, Object> obj : list) {
                Commentaire e = new Commentaire();

                
                float id = Float.parseFloat(obj.get("id").toString());
//               float user_id=Float.parseFloat(obj.get("user_id").toString());
                e.setId((int) id);
               // e.setUser_id((int)user_id);
                
                 // e.setGuide_id((int) Integer.parseInt(obj.get("guide_id").toString()));
                // e.setUser_id((int) Integer.parseInt(obj.get("user_id").toString()));
                   e.setContenu(obj.get("contenu").toString());
                  //User u = new User(1);
               /// float idu= Float.parseFloat(obj.get().toString());
                //e.setUser_id((int)idu);
            //    u.getId();
              //  UserSession.getInstance();
              //  e.setUser_id(f.getId());
                // e.setUser_id(obj.get("user_id").toString());
                
             // User u = new User();
                //u.setUsername(obj.get("username").toString());
              // e.setUser_id(u.getId());
              
             // e.setUser_id((int)obj.get("user_id").toString());
                comment.add(e);
              System.out.println(comment);
            }

        } catch (IOException ex) {
        }
        
        return comment;

    }
     ArrayList<Commentaire> listComments = new ArrayList<>();
    
    public ArrayList<Commentaire> getList2(Guide g){ 
        
       
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://localhost/pidev/web/app_dev.php/api/AfficherComm/"+g.getId());
        
        //con.addArgument("id", String.valueOf(g.getId()));
        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentaireService ser = new CommentaireService();
                comment = ser.getListTask(new String(con.getResponseData()));
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return comment;
    }
    
    public void addComment(Commentaire c ){
       
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/api/AddComm/"+c.getUser_id()+"/"+c.getGuide_id()+"/"+c.getContenu();
        //Url=Url+"?comment=" + c.getContenu()+"&id="+c.getGuide_id();
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
       // String Url = "http://localhost/pidev/web/app_dev.php/api/DellCom/" + e.getId()+"/"+e.getGuide_id();
   String    Url = "http://localhost/pidev/web/app_dev.php/api/DellCom/" + e.getId()+"/"+e.getGuide_id()+"/"+e.getUser_id();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "commentaire supprimé", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
     
    
public ArrayList<Commentaire> parseTasks(String jsonText){
        try {
            comment=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commentaire t = new Commentaire();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setContenu(obj.get("contenu").toString());
                //t.setUser_id((int)Float.parseFloat(obj.get("user_id").toString()));
               // t.setGuide_id(((int)Float.parseFloat(obj.get("guide_id").toString())));
               
               // t.setImage(obj.get("photo"));
               System.out.print(comment);
                comment.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return comment;
    }
    
     public ArrayList<Commentaire> getAllTasks(Guide g){
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://localhost/pidev/web/app_dev.php/api/AfficherComm/"+g.getId());
  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comment = parseTasks(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return comment;
    }
     public ArrayList<Commentaire> getComments(Guide g) 
     {
        ArrayList<Commentaire> listComments = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/web/app_dev.php/api/AfficherComm/"+g.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapCpmments = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCpmments.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                       // Commentaire comment = new Commentaire();
                        Commentaire comment = toCommentaire(obj);

                        /*float id = Float.parseFloat(obj.get("id").toString());
//                       float user_id = Float.parseFloat(obj.get("user_id").toString());
 
                        String contenu = obj.get("contenu").toString();
                        String date=obj.get("date").toString();
                        DateFormat format = new com.codename1.l10n.SimpleDateFormat("yyyy-MM-dd ");

                        comment.setId((int) id);
  //                     comment.setUser_id((int) user_id);
                        comment.setContenu(contenu);
                     //  int user=Integer.parseInt(obj.get("user_id").toString());
                       //comment.setUser_id(user);
                              
                        
                        comment.setDate(date);*/
                Map<String, Object> client  = (Map<String, Object>) obj.get("user");
                User u = new User();
               //u.setUsername(client.get("").toString());
                u.setUsername(client.get("username").toString());
                
                float user_id = Float.parseFloat(client.get("id").toString());
                u.setId((int) user_id);
                comment.setUser_id(u.getId());
                        System.out.print(listComments);
                        listComments.add(comment);

                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComments;
    }
      public static Commentaire toCommentaire(Object obj) {
        Commentaire c = new Commentaire();
        Map<String, Object> jsonComm = (Map<String, Object>) obj;
        c.setContenu(jsonComm.get("contenu").toString());
        c.setId((int) Float.parseFloat(jsonComm.get("id").toString()));
        //c.setGuide_id((int)Float.parseFloat(jsonComm.get("guide").toString()));
        //c.setUser_id((int)Float.parseFloat(jsonComm.get("user").toString()));
          //c.setDate((String) jsonComm.get("date"));
        return c;
    }
      public static User toUser(Object obj) {
        User u = new User();
        Map<String, Object> jsonUser = (Map<String, Object>) obj;
        u.setId((int) Float.parseFloat(jsonUser.get("id").toString()));
        u.setNom(jsonUser.get("nom").toString());
        u.setUsername(jsonUser.get("username").toString());
        u.setEmail(jsonUser.get("email").toString());
        u.setPassword(jsonUser.get("password").toString());
     
     
        return u;
    }
      public double avgReview(Guide g) {
        ArrayList<Commentaire> ReviewL = new ArrayList();
        ReviewL = getComments(g);
        double bo = 0;
        for (Commentaire r : ReviewL) {
            bo = bo + r.getId();
        }
        
        return bo;
    }
    
    
 

public ArrayList<Commentaire> parseReclamation(String jsonText) throws ParseException{
        try {
            comment=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
            for(Map<String,Object> obj : list){
                Commentaire r = new Commentaire();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
              /*  float Date = Date.(obj.get("id").toString());*/
               Map<String,Object> us = (Map<String,Object>)obj.get("user");
               float userid = Float.parseFloat(us.get("id").toString());
               r.setUser_id((int)userid);
             String nom=us.get("nom").toString();
                  r.setNom(nom);
               
               
               Map<String,Object> tr = (Map<String,Object>)obj.get("guide");
               float target = Float.parseFloat(tr.get("id").toString());
               r.setGuide_id((int)target);
               r.setContenu(obj.get("contenu").toString());
              // r.setObject(obj.get("objet").toString());
               //Date date=this.format(obj.get("date").toString());
               //r.setDate(date);


                System.out.println(comment);

                comment.add(r);
            }

        } catch (IOException ex) {

        }
        return comment;
    }

    public ArrayList<Commentaire> getAllClaims(int id){
         ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/pidev/web/app_dev.php/api/AfficherComm/"+id;
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    comment = parseReclamation(new String(con.getResponseData()));
                } catch (ParseException ex) {
                     System.out.println(ex.getMessage());
                }
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return comment;
    }
     
}
