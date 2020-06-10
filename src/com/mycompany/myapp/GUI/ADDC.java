/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.services.CategoryServices;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arij
 */
public class ADDC extends MenuForm{
    Form current;
     public ADDC(Form prev){
    current = this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());       
        setTitle("Add Category");
        setLayout(BoxLayout.yCenter());
        List<Category> lp= new ArrayList<>();
        CategoryServices cs = new CategoryServices();
        lp=cs.getAllCategory();
        List<String> lnc = new ArrayList<>();
        for (Category cc:lp){lnc.add(cc.getNom());}
        Container CX = new Container(BoxLayout.yCenter());
        Container C = new Container(BoxLayout.yCenter());
        TextField tfnom = new TextField("","nom");
        TextField tfproduit = new TextField("","produit");
        Button btnValider = new Button("Add");
        Style tftStyle =  tfnom.getAllStyles();
          Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
          tftStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tftStyle.setBgColor(0xffffff);
          tftStyle.setBgTransparency(255);
          tftStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tftStyle.setMargin(Component.BOTTOM, 3);
          
         Style tStyle =  tfproduit.getAllStyles();
         
          tStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tStyle.setBgColor(0xffffff);
          tStyle.setBgTransparency(255);
          tStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tStyle.setMargin(Component.BOTTOM, 3);
          
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfnom.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else if (lnc.contains(tfnom.getText())) {
                    Dialog.show("Alert", "category already exists", new Command("OK"));
                }
                else
                {
                    try {
                        Category C = new Category( tfnom.getText());
                        CategoryServices.getInstance().addCategory(tfnom.getText());
                        new ShowC(current).show();
                       
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        }); 
       
          
         C.addAll(tfnom,btnValider);
            add(C);   
          
      }
     
    
     
     }