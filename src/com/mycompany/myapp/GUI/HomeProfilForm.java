/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Follow;
import com.mycompany.myapp.entities.Jaime;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceFollow;
import com.mycompany.myapp.services.ServiceJaime;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;
import java.text.SimpleDateFormat;



/**
 *
 * @author ghofrane
 */
public class HomeProfilForm extends ProfilForm {


    public HomeProfilForm () {
       
        
         setTitle("Home");
         super.addSideMenu();
         Container Page = new Container(BoxLayout.y());
      
          ServiceFollow fol = new ServiceFollow();
          ArrayList<Follow> Follows = fol.getUFollows(12);
           int fl=Follows.size();
         if (fl ==0){
             System.out.println("fff");
             setLayout(BoxLayout.yCenter());
             Container c =new Container(BoxLayout.xCenter());
             c.add(new Label("Empty"));
             Page.add(c);
         }
             else{
             Label text = new Label("Nice to See You Again");
             Page.add(text);
             
             for (Follow f :Follows)
             {
                 int uid = f.getFollowed();
                 readPost(2,Page);
                 
             }
             
         }
                   
         add(Page);
    
    }  
    

  
    
    private void readPost(int uid,Container gl){
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        Container listpost = new Container(BoxLayout.y()); 
       
          ServiceJaime jm = new ServiceJaime();
          ServicePost ff = new ServicePost();
          
          ArrayList<Post> posts = ff.getUPosts(uid);
           
        
        
         for (Post p : posts)
                {
                
                  Container c = new Container(BoxLayout.y()){
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setWidth(CN.getDisplayWidth()*93/100);
                return d;
            } 
                  };
                  Container c1 = new Container(BoxLayout.y());
                  Container c2 = new Container(BoxLayout.x());
                   Style tftStyle =  c.getAllStyles();
                Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
          tftStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tftStyle.setBgColor(0xffffff);
          tftStyle.setBgTransparency(255);
          tftStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tftStyle.setMargin(Component.BOTTOM, 3);
          tftStyle.setMarginLeft(2);
                  Label cont = new Label( p.getContenu());
                  Label date  = new Label(formatter.format(p.getDateCreation()));
                  Label likes  = new Label(""+p.getNbr());
                  int user= p.getUser_id();
                  SpanLabel sp = new SpanLabel();
                  SpanLabel spj = new SpanLabel();
               
                  Button like = new Button(FontImage.MATERIAL_FAVORITE_BORDER);
                  Button dislike = new Button(FontImage.MATERIAL_FAVORITE);
                
                  final int id = p.getId(); 
                  Container c3 = new Container(BoxLayout.x());
                   ImageViewer imgv = new ImageViewer();  
                  imgv.setImage(setImage(user));
                  sp.setText(ServiceUser.getInstance().getAUser(user).get(0).getUsername());
                  c3.add(imgv);
                  c3.add(sp);
                  c1.add(date);
                  c1.add(cont);
                
                  c2.add(likes);
                  c2.add(like);
                  c2.add(dislike);
                  c1.add(spj);
                   c.add(c3);
                  c.add(c1);
                  c.add(c2);
                  listpost.add(c);
                  int liked =jm.getlikes(12,id).size();
                    System.out.println(liked);
                  if(liked != 0){
                      like.remove();
                  }
                  else 
                  {
                      dislike.remove();
                  }
                 
                 
                   like.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      jm.addJaime(1,id);
                      new HomeProfilForm().show();

                  });
                     ArrayList<Jaime> Jaimes = jm.getlikes(12,p.getId());
                     for (Jaime J :Jaimes )
                     {
                      dislike.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      jm.deleteJaime(J.getId());
                    new HomeProfilForm().show();

                  });
                     }
                  
                }
               
               gl.add(listpost);
                
    
       
        
    }
    
    public URLImage setImage(int id){
        User u = new User();
        ServiceUser su = new ServiceUser();
        String img =su.getAUser(id).get(0).getPhoto();
         EncodedImage imgs = EncodedImage.createFromImage(Image.createImage(150,150), true);
                        URLImage imgg= URLImage.createToStorage(imgs,img, "http://localhost/pidev/web/images/"+img);
                      return imgg;
                       
                        
    }
}
