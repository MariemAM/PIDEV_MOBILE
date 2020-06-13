/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Util;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;

import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.rate;
import com.mycompany.myapp.services.CommentaireService;

import com.mycompany.myapp.services.GuidesService;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author haifa
 */
public class GuideDetailsForm extends MenuForm{
    //public Guide g;
    Form hi;
       // init();
        
     Container listeContainer = new Container(BoxLayout.y());
    Container C1 = new Container(BoxLayout.x());
    Container wholeContainer = new Container(BoxLayout.y());
    Resources res;
    Guide b ;
     GuidesService bS = new GuidesService();
     CommentaireService bC=new CommentaireService();
    Commentaire cd=new Commentaire();
 Commentaire c=new Commentaire();
     User f = UserSession.getInstance().getUser();

   // private void init() {
public GuideDetailsForm(Form prev){}
    public GuideDetailsForm(Guide g) throws NullPointerException{
    hi=this;
   
        Button back = $(new Button("Back")).addActionListener(e2 -> {

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            new GuidesForm(hi).showBack();
        }).asComponent(Button.class);
        
        Label titre = new Label(g.getTitre());
            titre.getAllStyles().setFgColor(0x0c42c0);
            Label date = new Label(g.getDate_creation());
         Label cate = new Label(g.getCategorie());
     
         EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),450), true);
                        URLImage imgg= URLImage.createToStorage(img,g.getPhoto(), "http://localhost/pidev/web/images/"+g.getPhoto());
                        imgg.fetch();
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);         
       
        Container cy = new Container(BoxLayout.y());
        
        ShareButton sb = new ShareButton();
        sb.getAllStyles().setFgColor(0x0c42c0);
        sb.setText("Share Screenshot");

        Image screenshot = Image.createImage(getWidth(), getHeight());
        revalidate();       
        paintComponent(screenshot.getGraphics(), true);

        setVisible(true);

        
       
       // cy.addComponent(sb);
        removeAll();
         Style s = UIManager.getInstance().getComponentStyle("Title");
       //addComponent(cy);
        
       // addComponent(imgv);
       String a = "http://localhost/pidev/web/PDF/"+g.getId();
      //  String a="https://www.polyu.edu.hk/iaee/files/pdf-sample.pdf";
      FontImage Icon5 = FontImage.createMaterial(FontImage.MATERIAL_FILE_UPLOAD,s);
        Button devGuide = new Button("Show PDF");
    devGuide.addActionListener(e -> {
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() +g.getTitre();
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile(a, fileName, true);
    }
    Display.getInstance().execute(fileName);
});
   //addComponent(devGuide);
   
  
   FontImage Icon = FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, s);
      
   Button btn7 =new Button("like");
   btn7.addActionListener((evt) -> {
       Likes l=new Likes(f.getId(),g.getId());
       bS.likeAction(l, false);
            
            });
   
        Button btn2 =new Button("Dislike");
   btn2.addActionListener((evt) -> {
     
       Likes l=new Likes(f.getId(),g.getId());
     
             bS.likeAction(l,true);
            });
     FontImage Icon2 = FontImage.createMaterial(FontImage.MATERIAL_THUMB_DOWN, s);
       // addComponent(btn2);
     Container C2=new Container(BoxLayout.x());
      Container C3=new Container(BoxLayout.x());
      Container C4=new Container(BoxLayout.x());
          Container Concomm=new Container(BoxLayout.y());
      Button    ajouter = new Button("ADD Comment");
      TextArea comm = new TextArea();
        comm.setHint("Add a new comment");
    Concomm.add(comm);
    Concomm.addComponent(ajouter);
       ajouter.addActionListener((e) -> {
            
         
         SpanLabel content = new SpanLabel(c.getContenu());
          if (comm.getText().length() != 0) {
           //Commentaire n=new Commentaire(g.getId(),UserSession.getInstance().getUser().getId(),comm.getText());
          Commentaire n=new Commentaire(g.getId(),f.getId(), comm.getText());
      
        bC.addComment(n);
       // GuideDetailsForm an=new GuideDetailsForm(hi);
       //  an.show();
        new GuideDetailsForm(g).show();
         Dialog.show("Alert", "Comment Added", "ok", null);
        } else {
                Dialog.show("Alert", "empty field ", "ok", null);}
        Concomm.add(content);
    }); 
       for (Commentaire comment : bC.getAllComm(g.getId())){

          Container list = new Container(BoxLayout.y());
       
 DateFormat format = new com.codename1.l10n.SimpleDateFormat("yyyy-MM-dd ");
            SpanLabel con = new SpanLabel(comment.getContenu());
          Label username = new Label(" Username:"+comment.getNom());
            username.getAllStyles().setFgColor(0x1f2a7e);
            Concomm.add(username);
             Button b = new Button("X");
            b.getAllStyles().setFgColor(0x1f2a7e);
           list.add(con);
           if (f.getId().equals(comment.getUser_id())) {
                list.add(b);

           }
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
              
                   Commentaire cd=new Commentaire(comment.getId(), g.getId(), f.getId());
                    bC.supprimercom(cd);
                   new GuideDetailsForm(g).show();
                
                }
              
       });
           Concomm.add(list) ;}
        Slider star = createStarRankSlider();
        star.addActionListener((e) -> {
        try {
            int rating = star.getProgress();
            // System.out.println(rating);
            rate p=new rate(f.getId(),g.getId(),rating);
            bS.AddRate(p);
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
            
        });
        C2.add(btn7);
     C2.add(Icon);
        //addComponent(btnl);
      C2.add(btn2);
      C2.add(Icon2);
      C3.add(sb);
      C4.add(devGuide);
      C4.add(Icon5);
     cy.addAll(back,titre,date,cate,imgv,C1,C2,C3,C4,Concomm);
   //  cy.add(FlowLayout.encloseCenterMiddle(createStarRankSlider()));
       cy.add(FlowLayout.encloseCenterMiddle(star));
     hi.add(cy);
       hi. show();
       
       }   
      
     
    
   /*public SwipeableContainer createRankWidget(String title, String year) {
        MultiButton button = new MultiButton(title);
        button.setTextLine2(year);
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()),
                button);
    }*/
    
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
        s.setBgColor(0x77B0E0E6);
    
    }
    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0x77B0E0E6);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
     public Form getF() {
        return hi;
    }

    public void setF(Form hi) {
        this.hi = hi;
    }
 
}
            
                
    
        
        
    
    
    
    
    


    
