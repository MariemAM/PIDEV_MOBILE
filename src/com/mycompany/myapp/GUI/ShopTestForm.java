/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.CategoryServices;
import com.mycompany.myapp.services.ProductServices;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arij
 */
public class ShopTestForm extends MenuForm{
    Form current;
     public ShopTestForm(Form prev){
         current=this; 
         this.setLayout(BoxLayout.y());
         
         Container searchc = new Container(BoxLayout.x());
         TextField tsearch= new TextField("","search",18,TextArea.ANY);
         Button search = new Button();
         Style searchStyle = search.getAllStyles(); 
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
          
          search.addActionListener((ActionListener) (ActionEvent evt9) -> {  
               Produit p = new Produit();
               if(!ProductServices.getInstance().SearchProduct(tsearch.getText()).isEmpty()){
               p = ProductServices.getInstance().SearchProduct(tsearch.getText()).get(0);
              
              
                new ShowPDetails( p.getId(),current).show();
               }});
         add(searchc);
         
         
    Container catg = new Container(BoxLayout.x());
    catg.getAllStyles().setPadding(25, 25, 25, 25);
    CategoryServices cs= new CategoryServices();
    List<Category> lc = new ArrayList<>();
    lc=cs.getAllCategory();
    ComboBox categories= new ComboBox();
         for(Category c:lc){
             categories.addItem(c.getNom());
                  
         }
        // Category cat = new Category();
         
         categories.addActionListener(e10->{
             String catf = categories.getSelectedItem().toString();
             new ShopCat(catf,current).show();
         });
    
    catg.add(categories);
    add(catg);
    
     
     
     
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
     setTitle("Shop");
     Container main = new Container(BoxLayout.y()); 
     Button cart=new Button("Cart");
     main.add(cart);
     add(main);
      
      cart.addActionListener(l->new CartForm(this).show());
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
           final int idp = i.getId();
          EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),450), true);
                        URLImage imgg= URLImage.createToStorage(img,i.getPhoto(), "http://localhost/pidev/web/images"+i.getPhoto());
                        imgg.fetch(); 
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
          Button b=new Button(FontImage.MATERIAL_ADD);
          b.addActionListener(e->{
              UserSession.getInstance().addToPanier(i, 1);
              System.out.println(UserSession.getInstance().getPanier());
               
          });
        Button b2=new Button("details");
        b2.addActionListener((ActionListener)(ActionEvent evt9) -> {new ShowPDetails(idp,current).show(); });
        
         ShareButton share = new ShareButton();
             share.setTextToShare("Check out our product "+i.getNom()+ " !  It is "+i.getDescription()+" and only for "+ i.getPrix()+" T.N.D");
             share.setAlignment(LEFT);

             
          
          
         c.addAll(imgv,l,prix, b,b2,share);
            add(c);   
          
      }
     
    
     
     }
}
