/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;


import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.services.CategoryServices;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arij
 */
public class ShowC extends MenuForm{
      Form current;
    public ShowC( Form prev){
        current=this;
        this.setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        setTitle("All Categories");
        Container main = new Container(BoxLayout.y()); 
        Button ADD=new Button("ADD");
        main.add(ADD);
        add(main);
      
       ADD.addActionListener(l->new ADDC(this).show());
       List<Category> lp= new ArrayList<>();
       CategoryServices cs = new CategoryServices();
          lp=cs.getAllCategory();
          
       for(Category i:lp){
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
       // Label produit=new Label(i.getProduit()+" Products");
         //  produit.getAllStyles().setFgColor(0xd1d7e0);
           //produit.getAllStyles().setAlignment(LEFT);
           final int idc = i.getId();
        Button b=new Button("edit");
               b.addActionListener((ActionListener)(ActionEvent evt1) -> {
                    
                     new EditC(idc,current).show();

                  });
        Button b2=new Button("delete");
          b2.addActionListener(e->{
              CategoryServices.getInstance().deleteCategory(i.getId());
              new ShowC(current).show();
               
         });
          
         c.addAll(l,b,b2);
            add(c);   
          
      }
     
    
     
     }
}