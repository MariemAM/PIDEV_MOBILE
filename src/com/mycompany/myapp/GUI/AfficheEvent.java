/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.services.ServiceParticipation;
import java.util.ArrayList;

/**
 *
 * @author Yosrio
 */
public class AfficheEvent extends Form {
    
    public AfficheEvent(Form previous)
    {
        ServiceEvenement serviceTask=new ServiceEvenement();
        ArrayList<Evenement> list=serviceTask.getMyEventList();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setTitle("List of Events");
        
        for (Evenement t : list) {
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
            SimpleDateFormat Date = new SimpleDateFormat("dd-MM-yyyy");
           
            String dd= Date.format(t.getDate_debut());
            String df= Date.format(t.getDate_fin());
            
       
        Evenement i = new Evenement(t.getId(),t.getUser_id());
        Button btnparticiper = new Button("participer");
        Button btnannuler = new Button("Annuler");
        
        btnannuler.setEnabled(false);  
        
          btnparticiper.addActionListener(e -> {


            try {
                ServiceParticipation serviceParticiper = new ServiceParticipation();
                serviceParticiper.participer(i);
                btnparticiper.setEnabled(false);
                btnannuler.setEnabled(true);
                
                
            } catch (Exception ex) {
                System.out.println(ex + "problem");
            }

        }
        );
       
        

        btnannuler.addActionListener(e -> {

            try {
                ServiceParticipation serviceParticiper = new ServiceParticipation();
             
                serviceParticiper.Annuler(i);
              btnparticiper.setEnabled(true);
              btnannuler.setEnabled(false);
            } catch (Exception ex) {
                System.out.println(ex + "problem");
            }

        }
        );
        
       
           
                  
           EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance()
                   .getDisplayWidth(),350), true);
           URLImage imgg= URLImage.createToStorage(img,t.getPhoto(), "http://localhost/pidev/web/images/"
           +t.getPhoto());
                        imgg.fetch();
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
                       
                       
            
            Label date_debut = new Label("\nDate Start : " + dd  ); 
            Label date_fin = new Label("\nDate End : " + df  ); 
            Label nom=new  Label("nom : " + t.getNom()  ); 
           
            Label description=new  Label("\nDescription : " + t.getDescription()  ); 
            Label nbre_places =new  Label("\nnombres de places : " + t.getNbre_places()  ); 
            this.add(new Slider());
                   EncodedImage enc = EncodedImage.createFromImage(theme.getImage("loading.png").scaled(250, 250), false);
            
                                 String stringQr = "http://localhost/pidev/web/app_dev.php/api/evenement/show/" + t.getId();
             String urlQR = "https://chart.googleapis.com/chart?cht=qr&chl=" + stringQr + "&choe=UTF-8&chs=500x500";

             URLImage imgQR = URLImage.createToStorage(enc, t.getId()+ "Qr", urlQR);
             ImageViewer imageQR = new ImageViewer(imgQR);
            C1.addAll(imgv,nom,description,date_debut,date_fin,nbre_places,imageQR,btnparticiper,btnannuler);
            
            this.add(C1);
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
            
       
                         
        
        
        
        
        }
         
    
    
    }

    
}
     
    

