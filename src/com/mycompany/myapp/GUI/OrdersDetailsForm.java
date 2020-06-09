/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.LigneCommande;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author rejeb
 */
public class OrdersDetailsForm extends MenuForm {
    public OrdersDetailsForm(Form prev,Commande c){
      
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        setTitle("Order Details");
        this.setLayout(BoxLayout.y());
        Container ct= new Container(BoxLayout.yCenter());
        ct.getAllStyles().setMargin(20, 20, 20, 20);
        ct.getAllStyles().setPadding(20, 50, 20, 20);
        ct.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        ct.getAllStyles().setBgColor(0x2d283e);
        ct.getAllStyles().setBgTransparency(255);
        Container cont=new Container(new BoxLayout(CENTER));
        Label date = new Label(formatter.format(c.getDate()));
        Container datex=BorderLayout.centerEastWest(null, date, new Label("Date: "));
        Label dateLiv;
        if(c.getDateLivraison()!=null){
             dateLiv= new Label(formatter.format(c.getDateLivraison()));
        }
        else{
             dateLiv = new Label("Pending");
        }
        Container dateLivx=BorderLayout.centerEastWest(null, dateLiv, new SpanLabel("Date Livraison: "));
        Label price = new Label(c.getPrixTotal().toString()+" T.N.D");
        Container pricex=BorderLayout.centerEastWest(null, price, new Label("Total: "));
        Label status = new Label(c.getStatus());
        Container statusx=BorderLayout.centerEastWest(null, status, new Label("Status: "));
        Label address = new Label(c.getAddresse());
        Container addressx=BorderLayout.centerEastWest(null, address, new Label("Address: "));
        Label tel = new Label(c.getTel());
        Container telx=BorderLayout.centerEastWest(null, tel, new Label("Phone: "));
        price.getAllStyles().setFgColor(0x202020);
        status.getAllStyles().setFgColor(0x202020);
        address.getAllStyles().setFgColor(0x202020);
        tel.getAllStyles().setFgColor(0x202020);
        date.getAllStyles().setFgColor(0x202020);
        dateLiv.getAllStyles().setFgColor(0x202020);
        cont.addAll(datex,dateLivx,pricex,statusx,addressx,telx);
        cont.getAllStyles().setBorder(Border.createEmpty());
        cont.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        cont.getAllStyles().setBgTransparency(255);
        cont.getAllStyles().setBgColor(0xd1d7e0);
        cont.getAllStyles().setMargin(20, 20, 20, 20);
        cont.getAllStyles().setPadding(20, 50, 20, 20);
        cont.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        ct.add(cont);
        Label qtet=new Label("Quantity");
        Label prixt=new Label("Price");
        Label nomt=new Label("Name");
        qtet.getAllStyles().setFgColor(0xd1d7e0);
        prixt.getAllStyles().setFgColor(0xd1d7e0);
        nomt.getAllStyles().setFgColor(0xd1d7e0);
        qtet.setAlignment(CENTER);
        prixt.setAlignment(CENTER);
        nomt.setAlignment(CENTER);
        Container titles=BorderLayout.centerEastWest(qtet,prixt ,nomt );
        ct.add(titles);
        List<LigneCommande> liste= c.getLcs();
        for(LigneCommande lc : liste){

        Label nom=new Label(lc.getPname());
        nom.setAlignment(CENTER);
        Label prix = new Label(lc.getPrice().toString()+" T.N.D");
        prix.setAlignment(CENTER);
        Label qte = new Label(lc.getQuantity().toString());
        qte.setAlignment(CENTER);
        qte.getAllStyles().setFgColor(0x202020);
        prix.getAllStyles().setFgColor(0x202020);
        nom.getAllStyles().setFgColor(0x202020);
        Container container=BorderLayout.centerEastWest(qte, prix, nom);
        container.getAllStyles().setMargin(20, 20, 20, 20);
        container.getAllStyles().setPadding(20, 20, 20, 20);
        container.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
        strokeOpacity(120));   
        container.getAllStyles().setBgColor(0xd1d7e0);
        container.getAllStyles().setBgTransparency(255);
        ct.add(container);
        }
        Button btn=new Button("Reclamation");
        btn.addActionListener(l->{
            Message m = new Message("Problem: ");
        Display.getInstance().sendMessage(new String[] {"support@hk.tn"}, "Reclamation for order n°: "+c.getId(), m);
        });
        ct.add(btn);
        add(ct);
        
    }
    
}
