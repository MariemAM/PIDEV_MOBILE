/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rejeb
 */
public class ShopTestForm extends MenuForm{
     public ShopTestForm(Form prev){
         super();
         setLayout(BoxLayout.y());
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
      setTitle("Shop");
      Button cart=new Button("Cart");
      add(cart);
      cart.addActionListener(l->new CartForm(this).show());
      List<Produit> lp= new ArrayList<>();
      Produit p = new Produit(4,"test",50,100);
      Produit p1 = new Produit(6,"fish",50,20);
      lp.add(p);
      lp.add(p1);
      for(Produit i:lp){
          Container c=new Container(BoxLayout.x());
          Label l=new Label(i.getNom());
          Button b=new Button("add");
          b.addActionListener(e->{
              UserSession.getInstance().addToPanier(i, 1);
              System.out.println(UserSession.getInstance().getPanier());
          });
          c.addAll(l,b);
          add(c);
          
      }
      
   
     
     }
}
