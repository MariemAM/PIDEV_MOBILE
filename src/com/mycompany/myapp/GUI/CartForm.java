/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.UserSession;
import java.util.Map;

/**
 *
 * @author rejeb
 */
public class CartForm extends MenuForm {
    CartForm(Form prev){
        
      setLayout(BoxLayout.y());
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
      setTitle("Your Cart");
      Label total=new Label("Total: "+String.valueOf(UserSession.getInstance().getTotal())+" T.N.D");
      total.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
            strokeOpacity(120));   
            total.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            total.getAllStyles().setBgTransparency(255);
            total.getAllStyles().setBgColor(0x2d283e);
            total.getAllStyles().setFgColor(0xd1d7e0);
             total.getAllStyles().setMargin(20, 20, 20, 20);
           
      total.setAlignment(CENTER);
      Map<Produit,Integer> m =UserSession.getInstance().getPanier();
      if (!m.isEmpty()){
          add(total);
    
      for (Map.Entry<Produit, Integer> entry : m.entrySet()) {
          Container c= new Container(BoxLayout.x());
            c.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
            strokeOpacity(120));   
            c.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            c.getAllStyles().setBgTransparency(255);
            c.getAllStyles().setBgColor(0x2d283e);
            c.getAllStyles().setMargin(20, 20, 20, 20);
            c.getAllStyles().setPadding(20, 20, 20, 20);
          Container left = new Container(BoxLayout.x()) {
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setWidth(CN.getDisplayWidth()*62/100);
                return d;
            }                
        };
          
          Label id=new Label("Product: "+entry.getKey().getNom());
          left.add(id);
          left.getAllStyles().setMarginRight(10);
          Label v= new Label("Quantity: "+entry.getValue().toString());
          left.add(v);
          id.getAllStyles().setFgColor(0xd1d7e0);
           v.getAllStyles().setFgColor(0xd1d7e0);
          Button plus=new Button("+");
          plus.setAlignment(RIGHT);
          plus.getAllStyles().setFgColor(0x07da63);
          Button minus=new Button("-");
          minus.setAlignment(RIGHT);
          minus.getAllStyles().setFgColor(0xFF6600);
          Button rm=new Button("x");
          rm.setAlignment(RIGHT);
          rm.getAllStyles().setFgColor(0xb80f0a);
          plus.addActionListener(l->{
              UserSession.getInstance().addToPanier(entry.getKey(), 1);
              v.setText("Quantity: "+entry.getValue().toString());
              total.setText("Total: "+String.valueOf(UserSession.getInstance().getTotal())+" T.N.D");
                  });
          rm.addActionListener(l->{
          UserSession.getInstance().removeFromPanier(entry.getKey());
          getCurrentForm().setTransitionOutAnimator(CommonTransitions.createEmpty());
                new CartForm(prev).show();
                  });
          minus.addActionListener(l->{
              if(entry.getValue()==1){
                  UserSession.getInstance().removeFromPanier(entry.getKey());
                getCurrentForm().setTransitionOutAnimator(CommonTransitions.createEmpty());
                new CartForm(prev).show();
              }else{
                  
               UserSession.getInstance().addToPanier(entry.getKey(), -1);
               v.setText("Quantity: "+entry.getValue().toString());
               total.setText("Total: "+String.valueOf(UserSession.getInstance().getTotal())+" T.N.D");
              }
                  });
          Container right = new Container(BoxLayout.xRight()) {
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setWidth(CN.getDisplayWidth()*38/100);
                return d;
            }                
        };
          right.addAll(plus,minus,rm);
          c.addAll(left,right);
           
          add(c);
      }
      Button valider=new Button("Checkout");
        valider.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        valider.getAllStyles().setBgTransparency(255);
        valider.getAllStyles().setBgColor(0xd1d7e0);
        valider.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120)); 
      add(valider);
      valider.addActionListener(e->{
          new CheckoutForm(this).show();
      });
      valider.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
            strokeOpacity(120));   
            valider.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            valider.getAllStyles().setBgTransparency(255);
            valider.getAllStyles().setBgColor(0x2d283e);
            valider.getAllStyles().setFgColor(0xd1d7e0);
            valider.getAllStyles().setMargin(20, 20, 20, 20);
          
    }else{
                setLayout(BoxLayout.yCenter());
             Container c =new Container(BoxLayout.xCenter());
             c.add(new Label("Your cart is empty"));
             add(c);
      }
    }
}
