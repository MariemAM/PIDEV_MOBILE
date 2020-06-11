/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ProductServices;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arij
 */
public class ShowP extends MenuForm{
    Form current;
    public ShowP( Form prev){
       current=this;
        this.setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        setTitle("All Products");
        Container main = new Container(BoxLayout.y()); 
        /*Button ADD=new Button("ADD");
        main.add(ADD);
        add(main);
      
       ADD.addActionListener(l->new ADDP(this).show());*/
       List<Produit> lp= new ArrayList<>();
       ProductServices pr = new ProductServices();
          lp=pr.getAllProduct();
          
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
            l.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
            strokeOpacity(120));   
            l.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            l.getAllStyles().setBgTransparency(255);
            l.getAllStyles().setBgColor(0x2d283e);
            l.getAllStyles().setFgColor(0xd1d7e0);
            l.getAllStyles().setMargin(20, 20, 20, 20);
            l.getAllStyles().setAlignment(LEFT);
        Label prix=new Label(i.getPrix()+" T.N.D");
           prix.getAllStyles().setFgColor(0xd1d7e0);
           prix.getAllStyles().setAlignment(LEFT);
          Label qte=new Label(i.getQte()+" Pieces available");
           qte.getAllStyles().setFgColor(0xd1d7e0);
           qte.getAllStyles().setAlignment(LEFT);
          Label des=new Label("Description : "+i.getDescription());
           des.getAllStyles().setFgColor(0xd1d7e0);
           des.getAllStyles().setAlignment(LEFT);
           final int idp = i.getId();
        EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),450), true);
                        URLImage imgg= URLImage.createToStorage(img,i.getPhoto(), "http://localhost/pidev/web/images/"+i.getPhoto());
                        imgg.fetch(); 
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
        Button b=new Button("edit");
        b.getAllStyles().setFgColor(0xFF6600);
        b.addActionListener((ActionListener)(ActionEvent evt1) -> {
                    
                     new EditP(idp,current).show();

                  });
        Button b2=new Button("delete");
        b2.getAllStyles().setFgColor(0xb80f0a);
        
                
       
       b2.addActionListener((ActionListener)(ActionEvent evt1) -> {
                      pr.deleteProduit(idp);
                     new ShowP(current).show();

                  });
               Button b3=new Button("details");
               b3.getAllStyles().setFgColor(0x07da63);
        
                

          
         c.addAll(imgv,l,qte,des,prix,b,b2);
            add(c);   
          
      }
     
    
     
     }
}

    
    
