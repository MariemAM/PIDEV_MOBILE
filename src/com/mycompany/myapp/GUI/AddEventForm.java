/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.utils.Statics;

import java.util.Date;
import java.io.IOException;
import java.io.OutputStream;
/**
 *
 * @author Yosrio
 */
public class AddEventForm extends Form {
    String path ;
    Button btnimage;
    Image img  =null;
    
    public AddEventForm(Form previous) {
        
        setTitle("Add a new Event");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Nom");
        TextField tfdesc = new TextField("","Description");
        TextField tfnbplaces= new TextField("", "Nb places");
        
        
        Picker datePickerdd = new Picker();
        datePickerdd.setType(Display.PICKER_TYPE_DATE);
        
        
        Picker datePickerdf = new Picker();
        datePickerdf.setType(Display.PICKER_TYPE_DATE);
        
        
        Button AjouterImg = new Button("ajouter image ");
        TextField ph = new TextField("","picture");
        AjouterImg.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path);
                        ph.setText(path);
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        
        
      
        
        Button btnValider = new Button("Add Event");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfdesc.getText().length()==0)||(tfnbplaces.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                if (Integer. parseInt( tfnbplaces.getText())<=1){
                     Dialog.show("Alert", "nb places should be greater than 1 ", new Command("OK"));
                }
                
                else
                {
                    try {
                        
                        Evenement t = new Evenement(Statics.userId,tfName.getText(),tfdesc.getText(),datePickerdd.getDate(),datePickerdf.getDate(),Integer.parseInt(tfnbplaces.getText()),ph.getText());
                        
                        if( ServiceEvenement.getInstance().addEvent(t,ph.getText()))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Nb Places must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfdesc,ph,AjouterImg,tfnbplaces,datePickerdd,datePickerdf,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }        
}
