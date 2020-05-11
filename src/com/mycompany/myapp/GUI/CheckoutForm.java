/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.utils.UserSession;

/**
 *
 * @author rejeb
 */
public class CheckoutForm extends MenuForm {
    CheckoutForm(Form prev){
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
            setTitle("Checkout");
        Container main = new Container(BoxLayout.y());
        main.getAllStyles().setMargin(20, 20, 20, 20);
        main.getAllStyles().setPadding(20, 20, 20, 20);
        main.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        main.getAllStyles().setBgColor(0xB9BFFF);
        main.getAllStyles().setBgTransparency(255);
        
        Label total= new Label(String.valueOf(UserSession.getInstance().getTotal())+" T.N.D");
        total.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        total.setAlignment(CENTER);
        total.getAllStyles().setFgColor(0xffffff);
        Label addressLabel=new Label("Address: ");
        TextField address=new TextField();
        Label addressErr=new Label();
        addressErr.getAllStyles().setFgColor(0xab031a);
       
        Label telLabel=new Label("Phone: ");
        TextField tel=new TextField();
        Label telErr=new Label();
        telErr.getAllStyles().setFgColor(0xab031a);
        Label Err=new Label();
        Err.getAllStyles().setFgColor(0xab031a);
        Button valider=new Button("Confirm");
        valider.setAlignment(CENTER);
        valider.getAllStyles().setFgColor(0x000000);
        valider.getAllStyles().setBorder(Border.createEmpty());
        valider.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        valider.getAllStyles().setBgTransparency(155);
        valider.getAllStyles().setBgColor(0x456977);
        valider.getAllStyles().setMargin(10,10, 0, 0);
        valider.getAllStyles().setPadding(0, 0, 5, 5);
        valider.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        valider.addActionListener(e->{
            if(UserSession.getInstance().getPanier().isEmpty()){
                Err.setText("Empty Cart");
                return;
            }
            if(tel.getText().length()!=8){
                telErr.setText("Invalid phone number");
                return;
            }
            if(address.getText().length()<8){
                addressErr.setText("Address too short");
                return;
            }
            UserSession.getInstance().validerPanier(tel.getText(),address.getText());
            UserSession.getInstance().newPanier();
            Dialog d = new Dialog("Success");
            TextArea popupBody = new TextArea("Your order is successful", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.showPopupDialog(valider);
            new OrdersForm(new HomeForm()).show();
      });    
        Container x = new Container(BoxLayout.xCenter()) {
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setWidth(CN.getDisplayWidth()*60/100);
                return d;
            }                
        };
        x.add(valider);
        main.addAll(Err,total,addressLabel,address,addressErr,telLabel,tel,telErr,x);
        add(main);
    }
}
