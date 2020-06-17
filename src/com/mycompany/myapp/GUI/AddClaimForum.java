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
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author ghofrane
 */
public class AddClaimForum extends Form{
Form current;
    public AddClaimForum(String user,Form previous) {
        current = this ;
        setTitle("Add a new claim");
        setLayout(BoxLayout.yCenter());
         Container CX = new Container(BoxLayout.yCenter());
        Container C = new Container(BoxLayout.yCenter());
        TextField tfContenu = new TextField("","claim");
        TextField tfObj = new TextField("","obj");
        Button btnValider = new Button("Add");
        Style tftStyle =  tfContenu.getAllStyles();
          Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
          tftStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tftStyle.setBgColor(0xffffff);
          tftStyle.setBgTransparency(255);
          tftStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tftStyle.setMargin(Component.BOTTOM, 3);
          
         
           Style Style =  tfObj.getAllStyles();
          Style.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          Style.setBgColor(0xffffff);
          Style.setBgTransparency(255);
          Style.setMarginUnit(Style.UNIT_TYPE_DIPS);
          Style.setMargin(Component.BOTTOM, 3);
          
          
       btnValider.getUnselectedStyle().setAlignment(Component.CENTER);
       btnValider.getAllStyles().setFgColor(0xffffff);
       btnValider.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
       btnValider.getUnselectedStyle().setPadding(2, 2, 2, 2);
       btnValider.getUnselectedStyle().setBorder(
       RoundBorder.create().rectangle(true).shadowOpacity(90));
          
             
          
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfContenu.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                     int id = ServiceUser.getInstance().getNUser(user).get(0).getId();
                        Reclamation r = new Reclamation(tfContenu.getText(), tfObj.getText(),id,12);
                        ServiceReclamation.getInstance().addReclamation(r);
                       
                             new ListClaimForm().show();
                                     
                    } catch (Exception e) {
                        
                    }
                    
                }
                
                
            }
        });
        
        C.addAll(tfContenu,tfObj,btnValider);
        CX.add(C);
        add(CX);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
