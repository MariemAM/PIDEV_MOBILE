/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
        this.setLayout(BoxLayout.y());
        Container main = new Container(BoxLayout.y());
        main.getAllStyles().setMargin(20, 20, 20, 20);
        
        main.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        main.getAllStyles().setBgColor(0x2d283e);
        main.getAllStyles().setBgTransparency(255);
        
        Label total= new Label(String.valueOf(UserSession.getInstance().getTotal())+" T.N.D");
        total.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        total.setAlignment(CENTER);
        total.getAllStyles().setFgColor(0xd1d7e0);
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
       
        valider.getAllStyles().setFgColor(0xd1d7e0);
        valider.getAllStyles().setBorder(Border.createEmpty());
        valider.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        valider.getAllStyles().setBgTransparency(255);
        valider.getAllStyles().setBgColor(0x2d283e);
        valider.getAllStyles().setMargin(20, 20, 20, 20);
        valider.getAllStyles().setPadding(3, 3, 20, 20);
        valider.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        valider.addActionListener(e->{
            if(UserSession.getInstance().getPanier().isEmpty()){
                Err.setText("Empty Cart");
                return;
            }
           
            if(address.getText().length()<8 ){
                addressErr.setText("Address too short");
                System.out.println(address.getText());
                revalidate();
                return;
            }
            else{
                addressErr.setText("");
                revalidate();
            }
            boolean test;
            try {
                double d = Double.parseDouble(tel.getText());
                test=true;
            } catch (NumberFormatException nfe) {
                test=false;
            }
             if(tel.getText().length()!=8||test!=true){
                telErr.setText("Invalid phone number");
                revalidate();
                System.out.println(tel.getText());
                return;
            }
            else{
                telErr.setText("");
                revalidate();
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

        main.addAll(Err,total,addressLabel,address,addressErr,telLabel,tel,telErr);
        main.getAllStyles().setPadding(20, 20, 20, 20);
        add(main);
        add(valider);
    }
}
