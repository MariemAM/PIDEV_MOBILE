/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.User;
import java.text.SimpleDateFormat;
import java.util.Date;






/**
 *
 * @author Mariem
 */
public class InscriptionForm extends Form{

    public InscriptionForm() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");//2020-05-20 00:00:00 yyyy-MM-dd 00:00:00
   
      Form hi = new Form("Create Account", BoxLayout.y());
TableLayout tl;
int spanButton = 2;
if(Display.getInstance().isTablet()) {
    tl = new TableLayout(7, 2);
} else {
    tl = new TableLayout(14, 1);
    spanButton = 1;
}
tl.setGrowHorizontally(true);
hi.setLayout(tl);

TextField firstName = new TextField("", "First Name", 20, TextArea.ANY);
TextField surname = new TextField("", "Surname", 20, TextArea.ANY);
TextField email = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
TextField pass = new TextField("", "Password", 20, TextArea.ANY);
TextField pass2 = new TextField("", "Password", 20, TextArea.ANY);
TextField phone = new TextField("", "Phone", 20, TextArea.PHONENUMBER);
PickerComponent date = PickerComponent.createDate(new Date()).label("Date");

firstName.getAllStyles().setFgColor(0x194C1F);
firstName.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
surname.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
email.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
pass.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
phone.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
Style s = UIManager.getInstance().getComponentStyle("");
Validator val = new Validator();
val.addConstraint(phone, new NumericConstraint(true));
val.addConstraint(pass, new LengthConstraint(8, "must 8 caracter!!"));
val.addConstraint(pass2, new LengthConstraint(8, "must 8 caracter!!"));
Label l=new Label("");
Button submit = new Button("Create");
submit.getAllStyles().setMarginBottom(30);
submit.getAllStyles().setBorder(Border.createEmpty());
        submit.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        submit.getAllStyles().setBgTransparency(255);
        submit.getAllStyles().setBgColor(0x194C1F);
        submit.getAllStyles().setFgColor(0xd3d3d3);
hi.add("Name").add(firstName).
        add("Surname").add(surname).
        add("E-Mail").add(email).
        add("Password").add(pass).
        add("Confirm Password").add(pass2).
        add(l).
        add("Phone").add(phone). 
       add("Birth Date").add(date).
        add(submit);
 
     submit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
//              if(pass.getText().equals("") || pass2.getText().equals(""))
//            {l.setText("Mismatch password!!");
//           }
             if(pass.getText().equals(pass2.getText()))    
             {l.setText("Mismatch password!!");
             return;
              }
             revalidate();
              
              User u=new User();
              u.setUsername(firstName.getText());
              u.setUsernameCanonical(surname.getText());
              u.setEmail(email.getText());
              u.setPassword(pass.getText());
              u.setPhone(phone.getText());
              u.setLastLogin(date.getPicker().getDate());
             // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
             // java.sql.Date sql = new java.sql.Date(date.getPicker().getDate().getTime());
                //u.setLastLogin(sql);
              System.out.println(date.getPicker().getDate());
              //Date parsed = (Date) format.parse(d);
             // java.sql.Date sql = new java.sql.Date(parsed.getTime());
             
              
//              if( UserServices.getInstance().addUser(u))
//                            Dialog.show("Success","Connection accepted",new Command("OK"));
//                        else
//                            Dialog.show("ERROR", "Server error", new Command("OK"));
                      
              
              
              
              
              
              
              
           
          }
      });

//if(c.isSelected())
//            cb2.setSelected(false);
//        else if(cb2.isSelected())
//            c.setSelected(false);
        
        hi.show();
        
         
         
         FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_EMAIL, s);
         FontImage icon3 = FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY_OFF, s);
           FontImage icon4 = FontImage.createMaterial(FontImage.MATERIAL_PHONE, s);
        
    }
    
    
}
