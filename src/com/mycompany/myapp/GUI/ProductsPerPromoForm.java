/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.LignePromotion;
import com.mycompany.myapp.entities.Produit_Promo;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.services.LignePromoService;
import com.mycompany.myapp.services.Product_PromoService;
import java.util.ArrayList;
import java.util.TreeSet;


/**
 *
 * @author Mariem
 */
public class ProductsPerPromoForm extends MenuForm {

    public ProductsPerPromoForm(Form prev,Promotion promo) {
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        this.setTitle("Products ");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
  
        ArrayList<LignePromotion> list=new ArrayList<>();
        
         for (LignePromotion e : LignePromoService.getInstance().getAllTasks(promo.getId())){list.add(e);}
         for (Produit_Promo pdt : Product_PromoService.getInstance().getAllProducts()){
                       
                           LignePromotion l=new LignePromotion();
                           l.setIdpdt(pdt.getId());
                           l.setNompdt(pdt.getNom());
                           l.setQtepdt(pdt.getQte());
                           l.setQtepromo(0);
                           list.add(l);
                  
              }
    
         
            
              for (LignePromotion o : list){
           Container c= new Container(BoxLayout.x());
            c.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
            strokeOpacity(120));   
            c.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            c.getAllStyles().setBgTransparency(255);
            c.getAllStyles().setBgColor(0x2d283e);
            c.getAllStyles().setMargin(20, 20, 20, 20);
            c.getAllStyles().setPadding(20, 20, 20, 20);
          Container left = new Container(BoxLayout.y()) {
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setWidth(CN.getDisplayWidth()*62/100);
                return d;
            }                
        };
          
          Label nom=new Label("Product: "+o.getNompdt());
          left.add(nom);
          left.getAllStyles().setMarginRight(10);
          Label v= new Label("Quantity: "+o.getQtepromo());
          left.add(v);
          nom.getAllStyles().setFgColor(0xd1d7e0);
           v.getAllStyles().setFgColor(0xd1d7e0);
          Button plus=new Button("+");
          plus.setAlignment(RIGHT);
          plus.getAllStyles().setFgColor(0x07da63);
          Button minus=new Button("-");
          minus.setAlignment(RIGHT);
          minus.getAllStyles().setFgColor(0xFF6600);
          
          Button done=new Button();
          FontImage.setMaterialIcon(done, FontImage.MATERIAL_DONE);
          done.setAlignment(RIGHT);
          done.getAllStyles().setFgColor(0xb80f0a);
          
          
          plus.addActionListener(l->{
              if(o.getQtepromo()==0&& o.getQtepdt()>=1){
                  for (LignePromotion lf: LignePromoService.getInstance().createLigne(o.getIdpdt(),promo.getId())){
                      o.setId(lf.getId());              }
                //  LignePromoService.getInstance().UpdateQuantity(TOP, TOP, TOP)
                 // o.setId(21);//
                  
                       //   createLigne(int idpdt,int idpromo);
                       o.setQtepromo(o.getQtepromo()+1);
                       o.setQtepdt(o.getQtepdt()-1);
                       v.setText("Quantity: "+o.getQtepromo());}
                       else if(o.getQtepdt()!= 0)
                       { o.setQtepromo(o.getQtepromo()+1);
                 o.setQtepdt(o.getQtepdt()-1);
               v.setText("Quantity: "+o.getQtepromo());}
              else{
           Label error= new Label("insufficient quantity!!");
           Dialog d = new Dialog("", BoxLayout.y());
         d.addAll(error);
         d.showPopupDialog(plus);}
             
//              }else{
//              if(o.getQtepdt()!= 0 )
//              {  o.setQtepromo(o.getQtepromo()+1);
//                 o.setQtepdt(o.getQtepdt()-1);
//               v.setText("Quantity: "+o.getQtepromo());}
//              else{
//           Label error= new Label("insufficient quantity!!");
//           Dialog d = new Dialog("", BoxLayout.y());
//         d.addAll(error);
//         d.showPopupDialog(plus);}
              
                      //}
                  });
//          rm.addActionListener(f->{
//       getCurrentForm().setTransitionOutAnimator(CommonTransitions.createEmpty());
//          new ProductsPerPromoForm(prev,p).show();
//          
//                
//               
//                  });
          minus.addActionListener(l->{
              if(o.getQtepromo()>0){
              o.setQtepromo(o.getQtepromo()-1);
              o.setQtepdt(o.getQtepdt()+1);
              v.setText("Quantity: "+o.getQtepromo());
             
              }  });
          
          done.addActionListener(l->{
         if( LignePromoService.getInstance().UpdateQuantity(o.getId(), o.getQtepdt(), o.getQtepromo())==true){
         Label message= new Label("Succesufuly Updated");
           Dialog d = new Dialog("", BoxLayout.y());
         d.addAll(message);
         d.showPopupDialog(done);}
          });
          
          
          Container right = new Container(BoxLayout.xRight()) {
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setWidth(CN.getDisplayWidth()*38/100);
                return d;
            }                
        };
          right.addAll(plus,minus,done);
          c.addAll(left,right);
 
           
          add(c);}
         
        
          this.show(); 
        
    }
    
    
}
