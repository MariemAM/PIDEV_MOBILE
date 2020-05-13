/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;


import static com.codename1.ui.CN.getDisplayHeight;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.CommandeServices;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author rejeb
 */
public class OrdersForm extends MenuForm{
    public OrdersForm(Form prev){
       
        
        this.setLayout(BoxLayout.yCenter());
        this.setScrollableY(false);
        SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");  
        SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");  
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        setTitle("My Orders");
        
         Container main = new Container(BoxLayout.y()) {
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setHeight(getDisplayHeight() *85/100);
                return d;
            }                
        };
        
        main.getAllStyles().setMargin(20, 20, 20, 20);
        main.getAllStyles().setPadding(20, 50, 20, 20);
        main.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        main.getAllStyles().setBgColor(0x2d283e);
        main.getAllStyles().setBgTransparency(255);
        
        Label center=new Label("PRICE");
        center.getAllStyles().setFgColor(0xd1d7e0);
        center.getAllStyles().setAlignment(CENTER);
        
        
        Label start=new Label("DATE");
        start.getAllStyles().setFgColor(0xd1d7e0);
        start.getAllStyles().setAlignment(CENTER);
        
        Label end=new Label("STATUS");
        end.getAllStyles().setFgColor(0xd1d7e0);
        end.getAllStyles().setAlignment(CENTER);
        
        Container tableHead=BorderLayout.centerEastWest(center, end, start);
        main.add(tableHead);
        ArrayList<Commande> liste= CommandeServices.getInstance().getAllOrders(); 
        
        Container s = new Container(BoxLayout.y());
        s.setScrollableY(true);
        for(Commande c : liste){

        Container dtc =new Container(BoxLayout.y());
        
        Label date=new Label(dateformatter.format(c.getDate()));
        date.getAllStyles().setFgColor(0x202020);
        date.getAllStyles().setAlignment(LEFT);
        date.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        
        Label time=new Label(timeformatter.format(c.getDate()));
        time.getAllStyles().setFgColor(0x202020);
        time.getAllStyles().setAlignment(LEFT);
        time.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
       
        dtc.addAll(date,time);
        
        Label price=new Label(c.getPrixTotal().toString()+" T.N.D");
        price.getAllStyles().setFgColor(0x202020);
        price.getAllStyles().setAlignment(CENTER);
        
        Label status=new Label(c.getStatus());
        status.getAllStyles().setFgColor(0x202020);
        status.getAllStyles().setAlignment(RIGHT);
        
        status.addPointerReleasedListener((evt) -> {
             new OrdersDetailsForm(this,c).show();
         });
        Container content=BorderLayout.centerEastWest(price, status, dtc);
        content.getAllStyles().setBorder(Border.createEmpty());
        content.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        content.getAllStyles().setBgTransparency(255);
        content.getAllStyles().setBgColor(0xd1d7e0);
        content.getAllStyles().setMargin(20, 20, 5, 5);
        content.setLeadComponent(status);
        content.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        s.add(content);
        }       
        main.add(s);
        add(main);
    }
    
}
