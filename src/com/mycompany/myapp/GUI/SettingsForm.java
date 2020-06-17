/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;
import java.io.OutputStream;



/**
 *
 * @auhor user16
 */
public class SettingsForm extends ProfilForm {
    Form current;

    public SettingsForm() {
            
            current=this;
            super.addSideMenu();
            setTitle("Settings");
            Container gl = new Container(BoxLayout.y());
          
          
            TextField susername = new TextField("","nom");
           TextField semail = new TextField("","e-mail");
            TextField stlf = new TextField("","phone Number"); 
             TextField sadd = new TextField("","adress");   
            Style tStyle =  susername.getAllStyles();
            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            tStyle.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          tStyle.setBgColor(0xffffff);
          tStyle.setBgTransparency(255);
          tStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
          tStyle.setMargin(Component.BOTTOM, 3);
            
          Style Style =  semail.getAllStyles();
            Style.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          Style.setBgColor(0xffffff);
          Style.setBgTransparency(255);
          Style.setMarginUnit(Style.UNIT_TYPE_DIPS);
          Style.setMargin(Component.BOTTOM, 3);
          
          
          Style Styled =  stlf.getAllStyles();
            Styled.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          Styled.setBgColor(0xffffff);
          Styled.setBgTransparency(255);
          Styled.setMarginUnit(Style.UNIT_TYPE_DIPS);
          Styled.setMargin(Component.BOTTOM, 3);
          
          
          Style Styledd =  sadd.getAllStyles();
            Styledd.setBorder(RoundRectBorder.create().
                         strokeColor(0xffffff).
                         strokeOpacity(120).
                         stroke(borderStroke));
          Styledd.setBgColor(0xffffff);
          Styledd.setBgTransparency(255);
          Styledd.setMarginUnit(Style.UNIT_TYPE_DIPS);
          Styledd.setMargin(Component.BOTTOM, 3);
          Button browse = new Button ("browse");
              Button save = new Button ("save");
              ImageViewer img = new ImageViewer ();
             
              Container tf = new Container(BoxLayout.y());
              
             tf.add(susername);
             tf.add(semail);
             tf.add(stlf);
             tf.add(sadd);
             tf.add(browse);
             tf.add(save);
             tf.add(img);
                 
       save.getUnselectedStyle().setAlignment(Component.CENTER);
       save.getAllStyles().setFgColor(0xffffff);
       save.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
       save.getUnselectedStyle().setPadding(2, 2, 2, 2);
       save.getUnselectedStyle().setBorder(
       RoundBorder.create().rectangle(true).shadowOpacity(90));
       browse.getUnselectedStyle().setAlignment(Component.CENTER);
       browse.getAllStyles().setFgColor(0xffffff);
       browse.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
       browse.getUnselectedStyle().setPadding(2, 2, 2, 2);
       browse.getUnselectedStyle().setBorder(
       RoundBorder.create().rectangle(true).shadowOpacity(90));    
             
             
           browse.addActionListener((ActionListener)(ActionEvent evt1) -> {
 
                try {
                    Image capturedImage = Image.createImage(Capture.capturePhoto(850,850));
                   
                    img.setImage(capturedImage);
                  
                    String imageFile = "file://C://wamp64/www/pidev/web/images/"+capturedImage.getImage().hashCode()+".png";
                    try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                              ImageIO.getImageIO().save(capturedImage, os, ImageIO.FORMAT_PNG, 1);
                        save.addActionListener((ActionListener)(ActionEvent evt2) -> {
                    ServiceUser su = new ServiceUser();
                    su.updateUser(12, susername.getText(), semail.getText(), sadd.getText(),Integer.parseInt(stlf.getText()), capturedImage.getImage().hashCode()+".png");
                     new ListPostForm().show();
                });       
                              
                            } catch(IOException err) { } 
                    
              
                } catch (IOException ex) {
                   
               }  });
               
                
        gl.add(tf);
         add(gl);
}
    
}
