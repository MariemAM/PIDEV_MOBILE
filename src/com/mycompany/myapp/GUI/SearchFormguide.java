/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.services.GuidesService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author haifa
 */
public class SearchFormguide extends MenuForm{
     Form hi;
     Guide b ;
     GuidesService bS = new GuidesService();
     Container listeContainer = new Container(BoxLayout.y());
     public SearchFormguide(Form prev){}
      public SearchFormguide(String d){
              hi=this;
     Button back = $(new Button("Back")).addActionListener(e2 -> {

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            new GuidesForm(hi).showBack();
        }).asComponent(Button.class);
   // Form F2 = new Form(BoxLayout.y());
         
 
 ArrayList<Guide> liche = bS.ChercherTopic(d);
 for (Guide guide: liche) {
 Label titre = new Label(guide.getTitre());
            //titre.getAllStyles().setFgColor(0x0c42c0);
            //String m=guide.getDate_creation();
            //SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            //String date = DATE_FORMAT.format(m);
            //Label date = new Label(guide.getDate_creation());
         Label cate = new Label(guide.getCategorie());
   
     //   searchContainer.add(srchText);
      //  searchContainer.add(searchFiled);
        // wholeContainer.add(searchContainer);
         //    add(wholeContainer);
        //wholeContainer.add(listeContainer);
      
        //wholeContainer.add(listeContainer);
//             add(wholeContainer);
        //Service testing
       

           Button ev = new Button("Details");
            
        ev.addActionListener((evt) -> {
           //new GuideDetailsForm(prev ).show();
          // bS.AfficherDetails(b);
           // new GuideDetailsForm(prev, b, CENTER).show();
         new GuideDetailsForm(guide).show();
            });
      
     
  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),350), true);
                        URLImage imgg= URLImage.createToStorage(img,guide.getPhoto(), "http://localhost/pidev/web/images/"+guide.getPhoto());
                        imgg.fetch();
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
                        int likeCount = 0;
                        boolean liked = false;
                        int commentCount = 0;
//                        bS.nbrelike(guide);
             Label likes = new Label((guide.getNote())+ " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       Style s2 = new Style(likes.getUnselectedStyle());
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           
           s2.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s2);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       //FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);  
      FontImage commIm = FontImage.createMaterial(FontImage.MATERIAL_CHAT, s2);
           comments.setIcon(commIm);
      
            SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
            Label informations = new Label("Crée le"+(guide.getDate_creation()));
          informations.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            System.out.println(guide);
            Container guideInfoContainer = new Container(BoxLayout.y());
            listeContainer.add(titre);
//             listeContainer.add(date);
              listeContainer.add(cate);
              listeContainer.add(imgv);
            listeContainer.add(informations);
            listeContainer.addComponent(ev);
          // listeContainer.add(searchContainer);
          Container info=new Container(BoxLayout.x());
         info.add(likes);
         
         
          info.add(comments);
        listeContainer.add(info);
        listeContainer.add(back);
        
        }
     hi.add(listeContainer);
        hi.show();
     
     
     
        }

}

