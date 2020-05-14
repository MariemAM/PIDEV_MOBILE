/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
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
import java.util.Hashtable;
import java.util.Vector;



/**
 *
 * @author Mariem
 */
public class PromoteForm extends MenuForm {
    
   

    public PromoteForm(Form prev) {
        this.setTitle("My Offers");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Storage store=Storage.getInstance();
        Vector produits=new Vector();
        Hashtable produit=new Hashtable();
    
//        Image icon=null;
//        Style s = UIManager.getInstance().getComponentStyle("Button");
//        icon=FontImage.createMaterial(FontImage.MATERIAL_FILTER,s);
//        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        Tabs t=new Tabs();
//        t.addTab("filter", icon, c);
        for(Produit_Promo e : PromoteService.getInstance().getAllTasks()){ 
         produit.put("nom", e.getNom());
         produit.put("prix", e.getPrix());
         produit.put("prixp", e.getPrix_promo());
         produit.put("desc", e.getDesc());
         produits.add(produit);
         
         
         
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
        
        
    boolean verifstorage=store.writeObject("infoproduits", produits);
    if(verifstorage==true){System.out.println("Success");}
    else System.out.println("Echec");;
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
