/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.getDisplayHeight;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ProductServices;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arij
 */
public class ShowPDetails extends MenuForm{
    Form current;
     public ShowPDetails(int id ,Form prev){
    current = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());       
        setTitle("Product Details");
        setLayout(BoxLayout.yCenter());
        List<Produit> lp= new ArrayList<>();
      ProductServices pr = new ProductServices();
          lp=pr.getAProduct(id);
          
      for(Produit i:lp){
          Container c = new Container(BoxLayout.y()){
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setWidth(CN.getDisplayWidth()*85/100);
                return d;
            }                
        };
 
        c.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        c.getAllStyles().setBgColor(0x2d283e);
        c.getAllStyles().setBgTransparency(255);
        c.getAllStyles().setMargin(20, 20, 20, 20);
        c.getAllStyles().setPadding(20, 20, 20, 20);
          Label l=new Label(i.getNom());
          l.getAllStyles().setFgColor(0xd1d7e0);
          l.getAllStyles().setAlignment(LEFT);
          l.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        Label prix=new Label(i.getPrix()+" T.N.D");
           prix.getAllStyles().setFgColor(0xd1d7e0);
           prix.getAllStyles().setAlignment(LEFT);
          Label qte=new Label(i.getQte()+" Pieces available");
           qte.getAllStyles().setFgColor(0xd1d7e0);
           qte.getAllStyles().setAlignment(LEFT);
          Label des=new Label("Description: "+i.getDescription());
           des.getAllStyles().setFgColor(0xd1d7e0);
           des.getAllStyles().setAlignment(LEFT);
           final int idp = i.getId();
          EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),450), true);
                        URLImage imgg= URLImage.createToStorage(img,i.getPhoto(), "http://localhost:8080/pidev-merge/web/images/"+i.getPhoto());
                        imgg.fetch(); 
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
          Button b=new Button(FontImage.MATERIAL_ADD);
          b.addActionListener(e->{
              UserSession.getInstance().addToPanier(i, 1);
              System.out.println(UserSession.getInstance().getPanier());
               
          });
          
          ShareButton share = new ShareButton();
             share.setTextToShare("Check out our product "+i.getNom()+ " !  It is "+i.getDescription()+" and only for "+ i.getPrix()+" T.N.D");
             share.setAlignment(LEFT);

       
          c.addAll(imgv,l,des,prix,qte,share,b);
            add(c);  
      }
     
    
     
     }}

