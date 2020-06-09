/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.services.PromoServices;

/**
 *
 * @author Mariem
 */
public class AddProductPromoForm extends MenuForm {

    public AddProductPromoForm(Promotion p) {
        getToolbar().hideToolbar();
        getAllStyles().setBorder(Border.createEmpty());
        getAllStyles().setBackgroundType(BACKGROUND_NONE);
        getAllStyles().setBgTransparency(255);
        getAllStyles().setBgColor(0x0d0d0d);
        setLayout(BoxLayout.xCenter());
        
        Container c=new Container(BoxLayout.yCenter());
        
        ComboBox products =new ComboBox();//model de type ModelListe
        
       
        products.getAllStyles().setMarginBottom(100);
        TextComponent qte = new TextComponent().label("Quantity ");
        Validator val = new Validator();
        val.addConstraint(qte, new NumericConstraint(true));
     
       
         Label err=new Label(" ");
         
        err.setAlignment(CENTER);
        err.getAllStyles().setFgColor(0xfe0000);
        err.getAllStyles().setMarginBottom(100);
        qte.getAllStyles().setMarginBottom(100);
        Button r=new Button("Register");
        r.getAllStyles().setMarginBottom(100);
        r.getAllStyles().setBorder(Border.createEmpty());
        r.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        r.getAllStyles().setBgTransparency(255);
        r.getAllStyles().setBgColor(0x2d283e);//remplissage
        r.getAllStyles().setFgColor(0xd3d3d3);//text
        r.addActionListener(l->{
        
        });
         c.addAll(products,qte,r);
         addAll(c);
         this.show();
        
        
        
    }
        
        
    }
    
    

