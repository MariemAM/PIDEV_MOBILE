/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.rate;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author haifa
 */

public class GuidesService {
    User f = UserSession.getInstance().getUser();
     private Guide currentGuide;
      public static GuidesService instance=null;
     public static GuidesService getInstance() {
        if (instance == null) {
            instance = new GuidesService();
        }
        return instance;
    }
    
    public ArrayList<Guide> getList() throws NullPointerException {
            ArrayList<Guide> listguides = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        
       // String Url = "http://127.0.0.1/pidev/web/app_dev.php/api/guides/get_guides/idguide="+guide.getId()+"&titre="+guide.getTitre();
     String Url ="http://localhost/pidev/web/app_dev.php/api/guides";
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>()   {
            @Override
            public void actionPerformed(NetworkEvent evt) throws NullPointerException{ 
                JSONParser jsonp = new JSONParser();
                                try {
                    Map<String, Object> Guides = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Guides.get("root"));
                   List<Map<String, Object>> lists = (List<Map<String, Object>>) Guides.get("root");
                    for (Map<String, Object> obj : lists) {
                        
                        Guide guide = new Guide();
                       
                        
                        guide.setTitre(obj.get("titre").toString());
                         guide.setDescription(obj.get("description").toString());
      
                         guide.setLien(obj.get("lien").toString());
                         guide.setNote(Double.parseDouble(obj.get("note").toString()));
                       //  guide.setNbLikes(obj.get("nbLikes").toString());
                      //guide.setNbLikes((int) Integer.parseInt(obj.get("nbLikes").toString())); 
                      // guide.setNbLikes((int) Float.parseFloat(obj.get("nbLikes").toString()));
                        float id = Float.parseFloat(obj.get("id").toString());
                 //       String date =obj.get("date creation").toString();
                   //     guide.setDate_creation(date);
//                        String date1 = obj.get("date_creation").toString();
//                             guide.setDate_creation(obj.get("date_creation").toString());
                           guide.setCategorie(obj.get("categorie").toString());
                        guide.setId((int) id);
                      guide.setPhoto(obj.get("photo").toString());
                       DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
                     //    Date date = null;
                     
               /*           Double nte = Double.parseDouble(obj.get("note").toString());
                guide.setNote(nte);
                float nb = Float.parseFloat(obj.get("nbLikes").toString()); 
                guide.setNbLikes((int)nb);*/
               //guide.setNbLikes(Integer.parseInt((String) obj.get("nbLikes")));
                                System.out.println(guide.getPhoto());
//                       String  dateString = obj.get("date_creation").toString();
                      java.util.Date date1 = new java.util.Date();
//                       String date=obj.get("date_creation").toString();
                       guide.setDate_creation(obj.get("dateCreation").toString());
                       
       /*String date_creation= new java.text.SimpleDateFormat("yyyy-MM-dd").format(date1);
  //      String date_creation= new java.text.SimpleDateFormat("yyyy-MM-dd").format(dateString);
        guide.setDate_creation(date_creation);
        //guide.getDate_creation();*/
                  listguides.add(guide);
                 System.out.println(listguides);
                    }
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listguides;
       
    }
   
    private Date format(String input) throws java.text.ParseException{
         java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
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
public void AfficherDetails(Guide b)throws NullPointerException{
             
    ConnectionRequest con = new ConnectionRequest() {
            
       /*     @Override
            protected void postResponse() throws NullPointerException{ 
                Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("Show more");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.show();
            }*/
        };
       // con.setUrl("http://localhost/pidev/web/app_dev.php/api/details?id="+b.getId());
      con.setUrl("http://localhost/pidev/web/app_dev.php/api/details/"+b.getId());
        NetworkManager.getInstance().addToQueue(con);
    }
  public void recherche(Guide g){
          
      ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/api/rechercher" + g.getTitre();
        con.setUrl(Url);
           con.setHttpMethod("GET");

        System.out.println(Url);

        /*con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });*/
        NetworkManager.getInstance().addToQueueAndWait(con);

      }
  public String nbrelike(Guide g){
          
      ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/api/nbrelike/" + g.getId();
        con.setUrl(Url);
           con.setHttpMethod("GET");

        System.out.println(Url);

        /*con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });*/
        NetworkManager.getInstance().addToQueueAndWait(con);
       
  return Url ;
      }
   
       
   public Guide fillData(Map<String,Object> e){
          Guide guide = new Guide();
          guide.setId((int)e.get("id"));
         guide.setNbLikes(((Double)e.get("nblike")).intValue());
          guide.setTitre((String)e.get("titre"));
          guide.setDate_creation((String)e.get("dateCreation"));
          
          return guide;
    }
              
    public void likeAction(Likes g, boolean action){
        ConnectionRequest con = new ConnectionRequest();
       // String Url = RESTAURANT_API+"like/"+UserCo.userCo.getId()+"/"+g.getId();
                    String Url ="http://localhost/pidev/web/app_dev.php/api/PasAimer/"+g.getId_guide()+"/"+g.getId_user();
        if(!action){
         String Url2 ="http://localhost/pidev/web/app_dev.php/api/Aimer/"+g.getId_guide()+"/"+g.getId_user();
           con.setUrl(Url2);
           NetworkManager.getInstance().addToQueueAndWait(con);
        }else{
           con.setUrl(Url);
           NetworkManager.getInstance().addToQueueAndWait(con);
  
        }
}
/*public boolean isLiked(Restaurant r){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"isliked/"+UserCo.userCo.getId()+"/"+r.getId();
        final boolean[] liked = {false};
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            String response = new String(con.getResponseData());
            if(response.equals("\"true\"")){
                liked[0] = true;
            }
            else
                liked[0] = false;
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return liked[0];
        
    }*/
    
     public ArrayList<Guide> ChercherTopic(String titre) {
        ArrayList<Guide> listTopic = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
       
        String Url = "http://localhost/pidev/web/app_dev.php/api/rechercher";
        Url=Url+"?titre="+titre;
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Guide task = new Guide();
                    float id = Float.parseFloat(obj.get("id").toString());
                    task.setId((int) id);
                    task.setTitre(obj.get("titre").toString());
                    task.setDescription(obj.get("description").toString());
                    task.setPhoto(obj.get("photo").toString());
                    task.setCategorie(obj.get("categorie").toString());
                  /*      String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);*/
                    listTopic.add(task);
                    System.out.println(task);
                }
            } catch (IOException ex) {
                 System.out.println(ex.getMessage());
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopic;
    }
public void AddRate(rate p)throws IOException{
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://localhost/pidev/web/app_dev.php/api/guide/rate/"+p.getId_user()+"/"+p.getId_guide()+"/"+p.getNote();
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     //con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
    // con.addArgument("id_user", p.getId_user()+"");
  //  con.addArgument("id_guide", p.getId_guide()+"");
   // con.addArgument("note", p.getNote()+"");

     //con.setPost(true);
        System.out.println(url);
     
     
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
             });
    
        NetworkManager.getInstance().addToQueueAndWait(con);
    
    }

}
