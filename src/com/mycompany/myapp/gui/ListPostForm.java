/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.charts.util.ColorUtil.green;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Jaime;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceJaime;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.services.ServiceUser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.scene.text.Text;

/**
 *
 * @author bhk
 */
public class ListPostForm extends ProfilForm{
Form current;

    public ListPostForm() {
       current=this; 
         setTitle("Profil");
           Container gl = new Container(BoxLayout.yCenter());
        super.addSideMenu();
        Container searchc = new Container(BoxLayout.x());
         TextField tsearch= new TextField("","search",18,TextArea.ANY);
         Button search = new Button();
         Style searchStyle = search.getAllStyles(); 
         // searchStyle.setBorder(RoundBorder.create().color(0x008000).shadowOpacity(100));
          searchStyle.setFgColor(0x696969);
          searchStyle.setBgTransparency(0);
          searchStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          searchStyle.setMargin(Component.LEFT, 0);
          FontImage.setMaterialIcon(search, FontImage.MATERIAL_SEARCH,7);
         
          Style tftStyle =  tsearch.getAllStyles();
          Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
          tftStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tftStyle.setBgColor(0xffffff);
          tftStyle.setBgTransparency(255);
          tftStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tftStyle.setMargin(Component.BOTTOM, 3);
          searchc.addAll(tsearch,search);
         search.addActionListener((ActionListener) (ActionEvent evt1) -> {  
               String user = ServiceUser.getInstance().getNUser(tsearch.getText()).get(0).getUsername();
               if(user != null) {
                new UserDetailsForm(user,current).show();}
               else {
                new ListPostForm();
             }
         });
         add(searchc);
         readPost(1,gl);
        
          Button btnAddPost = new Button();
          btnAddPost.addActionListener(e-> new AddPostForum(current).show());
           Style closeStyle = btnAddPost.getAllStyles();
           closeStyle.setFgColor(0xffffff);
           closeStyle.setBgTransparency(0);
           closeStyle.setPaddingUnit(Style.UNIT_TYPE_DIPS);
           closeStyle.setPadding(3, 3, 3, 3);
           closeStyle.setBorder(RoundBorder.create().shadowOpacity(100));
           FontImage.setMaterialIcon(btnAddPost, FontImage.MATERIAL_ADD);
            Container btn = new Container(BoxLayout.xRight());
        btn.add(btnAddPost);
        gl.add(btn);
        add(gl);
   
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
                  Button edit = new Button(FontImage.MATERIAL_EDIT);
                  Button like = new Button(FontImage.MATERIAL_FAVORITE_BORDER);
                  Button dislike = new Button(FontImage.MATERIAL_FAVORITE);
                  Button delete = new Button(FontImage.MATERIAL_DELETE_FOREVER);
                  final int id = p.getId();
                   Container c3 = new Container(BoxLayout.x());
                   ImageViewer imgv = new ImageViewer();  
               imgv.setImage(setImage(1));
                  sp.setText(ServiceUser.getInstance().getAUser(user).get(0).getUsername());
                  c3.add(imgv);
                  c3.add(sp);
                  c1.add(date);
                  c1.add(cont);
                  c2.add(edit); 
                  c2.add(likes);
                  c2.add(like);
                  c2.add(dislike);
                  c1.add(spj);
                  c2.add(delete);
                  c.add(c3);
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
                 
                  edit.addActionListener((ActionListener) (ActionEvent evt1) -> {
                        new EditPostForm(id,current).show();

                  });
                  
                  delete.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      ff.deletePost(id);
                      new ListPostForm().show();

                  });
                   like.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      jm.addJaime(1,id);
                      new ListPostForm().show();

                  });
                     ArrayList<Jaime> Jaimes = jm.getlikes(1,p.getId());
                     for (Jaime J :Jaimes )
                     {
                      dislike.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      jm.deleteJaime(J.getId());
                      new ListPostForm().show();

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
