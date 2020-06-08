/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author ghofrane
 */
public class UserDetailsForm extends ProfilForm {
    Form current ;
   public UserDetailsForm (String user ,Form previous) {
      current=this;
      
      ServiceFollow fl = new ServiceFollow();
      Container listglobal = new Container(BoxLayout.y()); 
        listglobal.getAllStyles().setMarginTop(45);
      int uid = ServiceUser.getInstance().getNUser(user).get(0).getId();
      Container p1 = new Container (BoxLayout.y());
      Container p2 = new Container (BoxLayout.y());
      super.addSideMenu();
      Label username = new Label (user);
       Container btn = new Container(BoxLayout.x()); 
       Button follow = new Button("  follow  ");
       Button unfollow = new Button("  unfollow  ");
       Button claim = new Button("  claim  ");
       
       follow.getUnselectedStyle().setAlignment(Component.CENTER);
       follow.getAllStyles().setFgColor(0xffffff);
       follow.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
       follow.getUnselectedStyle().setPadding(2, 2, 2, 2);
       follow.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true).shadowOpacity(90)
);
        
       unfollow.getUnselectedStyle().setAlignment(Component.CENTER);
       unfollow.getAllStyles().setFgColor(0xffffff);
       unfollow.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
       unfollow.getUnselectedStyle().setPadding(2, 2, 2, 2);
       unfollow.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true).shadowOpacity(90)
);
       
       claim.getUnselectedStyle().setAlignment(Component.CENTER);
       claim.getAllStyles().setFgColor(0xffffff);
       claim.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
       claim.getUnselectedStyle().setPadding(2, 2, 2, 2);
       claim.getUnselectedStyle().setBorder(
        RoundBorder.create().color(0xFF6600).rectangle(true).shadowOpacity(90)
);
       
       
      
        follow.addActionListener((ActionListener) (ActionEvent evt1) -> {  
              Follow f = new Follow(1,uid);
                         fl.addFollow(f);
                        new HomeProfilForm().show();
                                     
                       
             });
        
          
        
       p1.add(username);
       btn.add(follow);
       btn.add(unfollow);
       btn.add(claim);
       p1.add(btn);
       readPost(user,uid ,p2);
       listglobal.add(p1);
       listglobal.add(p2);
       
       
             
                     {
                       unfollow.addActionListener((ActionListener) (ActionEvent evt1) -> { 
                            ArrayList<Follow> Follows = fl.getFollows(1,uid);
                     for (Follow f :Follows){
                         System.out.print(f);
                        fl.deleteFollow(f.getId());
                        new HomeProfilForm().show();
                     }
                       });
                     }
         int follows =fl.getFollows(1,uid).size();
                  if(follows != 0){
                      follow.remove();
                  }
                  else 
                  {
                      unfollow.remove();
                  }
       add(listglobal);
       
   }
    
    private void readPost(String user ,int uid,Container gl){
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
                  int userf= p.getUser_id();
                  SpanLabel sp = new SpanLabel();
                  SpanLabel spj = new SpanLabel();
                  
                  Button like = new Button(FontImage.MATERIAL_FAVORITE_BORDER);
                  Button dislike = new Button(FontImage.MATERIAL_FAVORITE);
              
                  final int id = p.getId(); 
                  sp.setText(ServiceUser.getInstance().getAUser(userf).get(0).getUsername());
                  c1.add(sp);
                  c1.add(date);
                  c1.add(cont);
                 
                  c2.add(likes);
                  c2.add(like);
                  c2.add(dislike);
                  c1.add(spj);
                
                  c.add(c1);
                  c.add(c2);
                  listpost.add(c);
                  int liked =jm.getlikes(uid,id).size();
                  if(liked != 0){
                      like.remove();
                  }
                  else 
                  {
                      dislike.remove();
                  }
                 
                  
                   like.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      jm.addJaime(1,id);
                      new UserDetailsForm(user, current).show();

                  });
                     ArrayList<Jaime> Jaimes = jm.getlikes(1,p.getId());
                     for (Jaime J :Jaimes )
                     {
                      dislike.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      jm.deleteJaime(J.getId());
                      new UserDetailsForm(user, current).show();

                  });
                     }
                  
                }
               
               gl.add(listpost);
              
                
    
       
        
    }
    
    
}
    

