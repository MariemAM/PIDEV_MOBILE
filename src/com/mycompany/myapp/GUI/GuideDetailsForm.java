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
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
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
     User u=new User(1);
             UserSession us=UserSession.getInstance(u);
               

   // private void init() {
public GuideDetailsForm(Form prev){}
    public GuideDetailsForm(Guide g) {
    hi=this;
   /*  
    //GuideDetailsForm (Guide g) throws IOException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm"); */ 
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
       /* setTitle("Guide Details");
        setLayout(BoxLayout.y());
        GuidesService gs=new GuidesService();*/
       // ArrayList<Guide> listeGuide = new ArrayList<>();

       // listeGuide = gs.getList();

        //for (Guide g : listeGuide) {
          //  Container c = new Container(BoxLayout.y());*/
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
    String fileName = fs.getAppHomePath() + g.getTitre();
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile(a, fileName, true);
    }
    Display.getInstance().execute(fileName);
});
   //addComponent(devGuide);
   
  
   FontImage Icon = FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, s);
      
   Button btn7 =new Button("like");
   btn7.addActionListener((evt) -> {
       Likes l=new Likes();
       l.setId_guide(g.getId());
       l.setId_user(us.getUser().getId());
       UserSession.getInstance().getUser().getId().toString();
       bS.likeAction(g, false);
            
            });
   
        Button btn2 =new Button("Dislike");
   btn2.addActionListener((evt) -> {
       UserSession.getInstance().getUser().getId().toString();
       Likes l=new Likes();
       l.setId_guide(g.getId());
       l.setId_user(u.getId());
             bS.likeAction(g, true);
            });
     FontImage Icon2 = FontImage.createMaterial(FontImage.MATERIAL_THUMB_DOWN, s);
       // addComponent(btn2);
     Container C2=new Container(BoxLayout.x());
      Container C3=new Container(BoxLayout.x());
      Container C4=new Container(BoxLayout.x());
          Container Concomm=new Container(BoxLayout.y());
      Button    ajouter = new Button("Ajouter");
TextArea comm = new TextArea();
        comm.setHint("Ajouter un commentaire");
    Concomm.add(comm);
    Concomm.addComponent(ajouter);
       ajouter.addActionListener((e) -> {
           Commentaire c=new Commentaire();
          
           c.setUser_id(u.getId());
            c.setGuide_id(g.getId());
            c.setContenu(comm.getText());
            
            
        bC.addComment(c);
    }); 
      // for (Commentaire comment : bC.getList2(g.getId())){

       //     Container list = new Container(BoxLayout.y());

        //    SpanLabel content = new SpanLabel(comment.getContenu());

           
            
       // Concomm.add(content);
        
        C2.add(btn7);
     C2.add(Icon);
        //addComponent(btnl);
      C2.add(btn2);
      C2.add(Icon2);
      C3.add(sb);
      C4.add(devGuide);
      C4.add(Icon5);
     cy.addAll(back,titre,date,cate,imgv,C1,C2,C3,C4);
     cy.add(FlowLayout.encloseCenterMiddle(createStarRankSlider()));
       
     hi.add(cy);
       hi. show();
    
    }
   public SwipeableContainer createRankWidget(String title, String year) {
        MultiButton button = new MultiButton(title);
        button.setTextLine2(year);
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()),
                button);
    }

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
        starRank.setMaxValue(10);
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

}
            
                
    
        
        
    
    
    
    
    


    
