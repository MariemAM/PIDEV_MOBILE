/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.mycompany.myapp.services.UserServices;
import java.util.Date;






/**
 *
 * @author Mariem
 */
public class InscriptionForm extends Form{

    public InscriptionForm() {
        getToolbar().hideToolbar();
        getAllStyles().setBorder(Border.createEmpty());
        getAllStyles().setBackgroundType(BACKGROUND_NONE);
        getAllStyles().setBgTransparency(255);
        getAllStyles().setBgColor(0x0d0d0d);
        setLayout(BoxLayout.yCenter());
        Container hi = new Container(BoxLayout.y());
        hi.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        hi.getAllStyles().setBgTransparency(255);
        hi.getAllStyles().setBgColor(0x0d0d0d);
        
        TableLayout tl;
        int spanButton = 2;
        if(Display.getInstance().isTablet()) {
            tl = new TableLayout(6, 2);
        } else {
            tl = new TableLayout(14, 1);
            spanButton = 1;
        }
        tl.setGrowHorizontally(true);
        hi.setLayout(tl);
Container top=new Container(BoxLayout.xCenter());
Label hk=new Label("SIGNUP");
        hk.getAllStyles().setFgColor(0x194C1F);
        hk.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
top.add(hk);
hi.add(top);
TextField firstName = new TextField("", "Username", 20, TextArea.ANY);
TextField email = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
TextField pass = new TextField("", "Password", 20, TextArea.ANY);
TextField pass2 = new TextField("", "Confirm Password", 20, TextArea.ANY);
TextField phone = new TextField("", "Phone", 20, TextArea.PHONENUMBER);

firstName.getAllStyles().setFgColor(0x194C1F);
firstName.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));

email.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
pass.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
phone.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
Style s = UIManager.getInstance().getComponentStyle("");
Validator val = new Validator();
val.addConstraint(phone, new NumericConstraint(true));
val.addConstraint(pass, new LengthConstraint(8, "must be 8 caracters"));
val.addConstraint(pass2, new LengthConstraint(8, "must be 8 caracters"));
Label l=new Label("");
Button submit = new Button("Join");
        submit.getAllStyles().setMarginTop(30);
        submit.getAllStyles().setBorder(Border.createEmpty());
        submit.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        submit.getAllStyles().setBgTransparency(255);
        submit.getAllStyles().setBgColor(0x194C1F);
        submit.getAllStyles().setFgColor(0xd3d3d3);
        Button login = new Button("Or login");
        login.getAllStyles().setMarginTop(30);
        login.getAllStyles().setBorder(Border.createEmpty());
        login.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        login.getAllStyles().setBgTransparency(255);
        login.getAllStyles().setBgColor(0x194C1F);
        login.getAllStyles().setFgColor(0xd3d3d3);
        hi.add("Username").add(firstName).
        add("E-Mail").add(email).
        add("Password").add(pass).
        add("Confirm Password").add(pass2).
        add(l).
        add("Phone").add(phone). 
        add(submit).add(login);
 
     submit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             if(!pass.getText().equals(pass2.getText()))    
             {l.setText("Mismatch password!!");
              revalidate();
             return;
              }   
              User u=new User();
              u.setUsername(firstName.getText());
              u.setEmail(email.getText());
              u.setPassword(pass.getText());
              u.setPhone(phone.getText());
              if(UserServices.getInstance().addUser(u)){
              UserServices.getInstance().login(u.getUsername(),u.getPassword());
              new HomeForm().show();}
          }
      });
     login.addActionListener(e->{
     new LoginForm().show();
     });

        add(hi);
        
         
         
         FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_EMAIL, s);
         FontImage icon3 = FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY_OFF, s);
           FontImage icon4 = FontImage.createMaterial(FontImage.MATERIAL_PHONE, s);
        
    }
    
    
}
