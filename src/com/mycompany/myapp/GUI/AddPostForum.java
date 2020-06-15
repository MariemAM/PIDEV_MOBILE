/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MediaPlayer;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;

/**
 *
 * @author ghofrane
 */
public class AddPostForum extends Form{
Form current ;
    public AddPostForum(Form previous) {
        setTitle("Add POST");
       setLayout(BoxLayout.yCenter());
        Container CX = new Container(BoxLayout.yCenter());
        Container C = new Container(BoxLayout.yCenter());
        TextField tfContenu = new TextField("","post");
       
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
                        Post P = new Post(12,tfContenu.getText());
                         ServicePost.getInstance().addPost(P);
                        new ListPostForm().show();
                       
                    } catch (Exception e) {
                       
                    }
                    
                }
                
                
            }
        });
        
      C.addAll(tfContenu,btnValider);
        CX.add(C);
        addAll(CX);
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
