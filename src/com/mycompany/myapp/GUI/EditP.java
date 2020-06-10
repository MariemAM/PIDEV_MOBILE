/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.getDisplayHeight;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
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
public class EditP extends MenuForm{
    Form current;
     public EditP(int id ,Form prev){
    current = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());       
        setTitle("Edit Product");
        setLayout(BoxLayout.yCenter());
        Container CX = new Container(BoxLayout.yCenter());
        Container C = new Container(BoxLayout.yCenter());
        TextField tfnom = new TextField("","name");
        TextField tfqte = new TextField("","quantity");
        TextField tfprix = new TextField("","price");
       // TextField tfprix_promo = new TextField("","discount price");
        TextField tfdescription = new TextField("","description");
        //TextField tfphoto = new TextField("","please insert photo url");
       // TextField tfcategorie = new TextField("","categorie");
       Container catg = new Container(BoxLayout.x());

        catg.getAllStyles().setPadding(25, 25, 25, 25);
 
    CategoryServices cs= new CategoryServices();
    List<Category> lc = new ArrayList<>();
    lc=cs.getAllCategory();
    ComboBox categories= new ComboBox();
         for(Category c:lc){
             categories.addItem(c.getNom());
         }
                    catg.add(categories);
    add(catg);
         
          String catf = categories.getSelectedItem().toString();
          
     
            
        Button btnValider = new Button("Edit");
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
          
         Style tStyle =  tfqte.getAllStyles();
         
          tStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tStyle.setBgColor(0xffffff);
          tStyle.setBgTransparency(255);
          tStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tStyle.setMargin(Component.BOTTOM, 3);
        
                   Style ttStyle =  tfprix.getAllStyles();
         
          ttStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          ttStyle.setBgColor(0xffffff);
          ttStyle.setBgTransparency(255);
          ttStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          ttStyle.setMargin(Component.BOTTOM, 3);
                 //  Style tsStyle =  tfprix_promo.getAllStyles();
         
          /*tsStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tsStyle.setBgColor(0xffffff);
          tsStyle.setBgTransparency(255);
          tsStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tsStyle.setMargin(Component.BOTTOM, 3*/
                   Style tbStyle =  tfdescription.getAllStyles();
         
          tbStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tbStyle.setBgColor(0xffffff);
          tbStyle.setBgTransparency(255);
          tbStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tbStyle.setMargin(Component.BOTTOM, 3);
/*                   Style tpStyle =  tfphoto.getAllStyles();
         
          tpStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tpStyle.setBgColor(0xffffff);
          tpStyle.setBgTransparency(255);
          tpStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tpStyle.setMargin(Component.BOTTOM, 3);*/
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfnom.getText().length()==0 ||tfprix.getText().length()==0||tfqte.getText().length()==0||tfdescription.getText().length()==0 )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else if (Integer.parseInt(tfprix.getText())<1) {
                    Dialog.show("Alert", "Price can't be null", new Command("OK"));
                }
                                else if (Integer.parseInt(tfqte.getText())<1) {
                    Dialog.show("Alert", "Quantity can't be null", new Command("OK"));
                }
                else
                {
                    try { List<Category> lcd = cs.findCat(catf);
        for(Category c:lcd){
           int idc =c.getId();
                        ProductServices.getInstance().updateProduct( id ,tfnom.getText(),Integer.parseInt( tfqte.getText()),Integer.parseInt(tfprix.getText())  ,tfdescription.getText(),idc);
                      
                         new ShowP(current).show();
        }
                       
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }}); 
       
          
         C.addAll(tfnom,tfqte,tfprix,tfdescription,btnValider);
            add(C);   
          
      }
     
    
     
     }

