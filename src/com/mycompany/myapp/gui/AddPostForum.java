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
     //   setLayout(BoxLayout.yCenter());
        Container CX = new Container();
        Container C = new Container();
        TextField tfContenu = new TextField("","post");
        TextField tfUser = new TextField("","user");
        Form hi = new Form("MediaPlayer", new BorderLayout());
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
          
         Style tStyle =  tfUser.getAllStyles();
         
          tStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tStyle.setBgColor(0xffffff);
          tStyle.setBgTransparency(255);
          tStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tStyle.setMargin(Component.BOTTOM, 3);

   
hi.setToolbar(new Toolbar());
Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, s);
hi.getToolbar().addCommandToRightBar(new Command("", icon) {
    @Override
    public void actionPerformed(ActionEvent evt) {
        Display.getInstance().openGallery((e) -> {
            if(e != null && e.getSource() != null) {
                String file = (String)e.getSource();
                try {
                    System.out.println(file);
                    Media video = MediaManager.createMedia(file, true);
                    hi.removeAll();
                    hi.add(BorderLayout.CENTER, new MediaPlayer(video));
                    hi.revalidate();
                } catch(IOException err) {
                    Log.e(err);
                } 
            }
        }, Display.GALLERY_VIDEO);
    }
});
      
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfContenu.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Post P = new Post(Integer.parseInt(tfUser.getText()),tfContenu.getText());
                         ServicePost.getInstance().addPost(P);
                        new ListPostForm().show();
                       
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
      C.addAll(tfContenu,tfUser,btnValider);
        CX.add(C);
        addAll(CX,hi);
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
