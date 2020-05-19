/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getDisplayHeight;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.EasyThread;
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.services.GuidesService;
//import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author haifa
 */
public class GuidesForm extends MenuForm{
    Container listeContainer = new Container(BoxLayout.y());
    Container searchContainer = new Container(BoxLayout.x());
    Container wholeContainer = new Container(BoxLayout.y());
    Resources res;
    Guide b ;
     GuidesService bS = new GuidesService();
    public GuidesForm(Form prev){
       
        super();
         this.setLayout(BoxLayout.y());
        this.setScrollableY(false);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
      setTitle("GUIDES");
       Container c=new Container(BoxLayout.y()){
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setHeight(getDisplayHeight() *1/ 2);
                return d;
            }                
        };
        /*Label srchText = new Label("search:");
        TextField searchFiled = new TextField("", "search here...");
        searchFiled.getAllStyles().setFgColor(0x000000);*/
        Style s = UIManager.getInstance().getComponentStyle("Title");
        TextField searchField = new TextField("", " Search"); 
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
      searchField.addDataChangedListener((int d1, int d2) ->{
           
            if (!searchField.getText().equals(""))  {
              //  bS.rechercheList(searchFiled.getText());
              bS.recherche(b);
            } else {
                bS.getList();

            }
        });
        //searchContainer.add(srchText);
        searchContainer.add(searchIcon);
       searchContainer.add(searchField);
       this.add(searchContainer);
 /*this.addCommandToRightBar("", searchIcon, (e) -> {
    searchField.startEditingAsync(); 
            });*/
      List<Guide> lp= new ArrayList<>();
      GuidesService pr = new GuidesService();
          lp=pr.getList();
               //Recovering objects
        for (Guide guide : bS.getList()) {
            Label titre = new Label(guide.getTitre());
            //titre.getAllStyles().setFgColor(0x0c42c0);
            Label date = new Label(guide.getDate_creation());
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
      
     
  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),450), true);
                        URLImage imgg= URLImage.createToStorage(img,guide.getPhoto(), "http://localhost/pidev/web/images/"+guide.getPhoto());
                        imgg.fetch();
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
                        int likeCount = 0;
                        boolean liked = false;
                        int commentCount = 0;
             Label likes = new Label((guide.getNbLikes())+ " Likes  ", "NewsBottomLine");
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
             listeContainer.add(date);
              listeContainer.add(cate);
              listeContainer.add(imgv);
            listeContainer.add(informations);
            listeContainer.addComponent(ev);
          // listeContainer.add(searchContainer);
          Container info=new Container(BoxLayout.x());
         info.add(likes);
         
         
          info.add(comments);
        listeContainer.add(info);
        }
        
        
          add(listeContainer);
          //add(searchContainer);
        
        }
    
}   
            
            
    

