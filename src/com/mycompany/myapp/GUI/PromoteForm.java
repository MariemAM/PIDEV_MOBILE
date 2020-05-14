/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Produit_Promo;
import com.mycompany.myapp.services.PromoteService;



/**
 *
 * @author Mariem
 */
public class PromoteForm extends MenuForm {
    
   

    public PromoteForm(Form prev) {
        this.setTitle("My Offers");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
//        Image icon=null;
//        Style s = UIManager.getInstance().getComponentStyle("Button");
//        icon=FontImage.createMaterial(FontImage.MATERIAL_FILTER,s);
//        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        Tabs t=new Tabs();
//        t.addTab("filter", icon, c);
        for(Produit_Promo e : PromoteService.getInstance().getAllTasks()){ 
        MultiButton mb1=new MultiButton();
        mb1.setTextLine1(e.getNom());
        mb1.setTextLine2(e.getPrix_promo().toString());
        mb1.setTextLine3(e.getPrix().toString());
        mb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProductsDetailsForm(e,prev);
            }}); 
        this.add(mb1);}
        
    
//        int mm = Display.getInstance().convertToPixels(3);     
//  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
//        for(Produit_Promo e : PromoteService.getInstance().getAllTasks()){ 
//        Container c = new Container(BoxLayout.x());
//        Label nom = new Label(e.getNom());
//        Label prix = new Label(e.getPrix().toString());
//        Label prix_promo = new Label(e.getPrix_promo().toString());
//      
//        nom.addPointerPressedListener((evt) -> {
//            
//            Label nomDetails = new Label(e.getNom());
//            
//            detailsProduit.add(nomDetails);
//            
//            detailsProduit.getToolbar().addCommandToLeftBar("Retour", null, (evt2) -> {
//            
//                detailsProduit.removeAll();
//
//                this.show();
//           
//            });
//            
//            detailsProduit.show();
//            
//        });
//  c.addAll(prix,prix_promo,nom);
//  c.setLeadComponent(nom);
// this.add(c);

//this.addComponent(BorderLayout.CENTER,t);
 this.show();
  
    }
        
       
       
      
            
       
 
    
}
