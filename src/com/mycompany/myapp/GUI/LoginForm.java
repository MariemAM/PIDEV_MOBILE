/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.services.UserServices;
import com.mycompany.myapp.utils.UserSession;

/**
 *
 * @author rejeb
 */
public class LoginForm extends Form{
    public LoginForm(){
        getToolbar().hideToolbar();
        getAllStyles().setBorder(Border.createEmpty());
        getAllStyles().setBackgroundType(BACKGROUND_NONE);
        getAllStyles().setBgTransparency(255);
        getAllStyles().setBgColor(0x0d0d0d);
        
        setLayout(BoxLayout.xCenter());
        Container c=new Container(BoxLayout.yCenter());  
        Label hk=new Label("HUNT KINGDOM");
        hk.getAllStyles().setFgColor(0x194C1F);
        hk.setAlignment(CENTER);
        hk.getAllStyles().setMarginBottom(100);
        hk.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Label title=new Label("Login");
        title.setAlignment(CENTER);
        Label err=new Label(" ");
        err.setAlignment(CENTER);
        err.getAllStyles().setFgColor(0xfe0000);
        title.getAllStyles().setMarginBottom(20);
        err.getAllStyles().setMarginBottom(100);
        TextComponent username = new TextComponent().label("Username");
        username.getAllStyles().setMarginBottom(10);
        TextComponentPassword pass = (TextComponentPassword) new TextComponentPassword().label("Password");
        pass.getAllStyles().setMarginBottom(30);
        Button login=new Button("Login");
        login.getAllStyles().setBorder(Border.createEmpty());
        login.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        login.getAllStyles().setBgTransparency(255);
        login.getAllStyles().setBgColor(0x194C1F);
        login.getAllStyles().setFgColor(0xd3d3d3);
        Button sp=new Button("Or Signup");
        sp.getAllStyles().setBorder(Border.createEmpty());
        sp.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        sp.getAllStyles().setBgTransparency(255);
        sp.getAllStyles().setBgColor(0x194C1F);
        sp.getAllStyles().setFgColor(0xd3d3d3);
        sp.getAllStyles().setMarginTop(10);
        login.addActionListener(l->{
            if(username.getText().equals("") || pass.getText().equals(""))
            {
                err.setText("Empty fields");
                username.getField().setText("");
                pass.getField().setText("");
                revalidate();
                return;
            }
            UserServices.getInstance().login(username.getText(), pass.getText());
            if(UserSession.getInstance()==null)
            {
                err.setText("Wrong credentials!");
                username.getField().setText("");
                pass.getField().setText("");
                revalidate();
            }
            else{
                new HomeForm().show();
            }
   
        });
        sp.addActionListener(l->{
            new InscriptionForm().show();
        });
        c.addAll(hk,title,err);
        c.addAll(username,pass,login,sp);
        addAll(c);
    }
}
