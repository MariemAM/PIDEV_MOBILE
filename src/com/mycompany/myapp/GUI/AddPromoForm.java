/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.services.PromoServices;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author Mariem
 */
public class AddPromoForm extends MenuForm {

    public AddPromoForm(Form prev) {
        getToolbar().hideToolbar();
        getAllStyles().setBorder(Border.createEmpty());
        getAllStyles().setBackgroundType(BACKGROUND_NONE);
        getAllStyles().setBgTransparency(255);
        getAllStyles().setBgColor(0x0d0d0d);
        setLayout(BoxLayout.xCenter());
        Container c = new Container(BoxLayout.yCenter());
        Label l = new Label("Name:");
        l.getAllStyles().setBgColor(0x2d283e);
        l.getAllStyles().setFgColor(0xd3d3d3);
        TextComponent name = new TextComponent();
        name.getAllStyles().setMarginBottom(100);
        Label ra = new Label("Rate:");
        ra.getAllStyles().setBgColor(0x2d283e);
        ra.getAllStyles().setFgColor(0xd3d3d3);
        TextComponent rate = new TextComponent();
        Validator val = new Validator();
        val.addConstraint(rate, new NumericConstraint(true));
        Label s = new Label("Start Date:");
        s.getAllStyles().setBgColor(0x2d283e);
        s.getAllStyles().setFgColor(0xd3d3d3);
        //PickerComponent startdate = PickerComponent.createDate(null);
        Picker startdate = new Picker();
        startdate.setType(Display.PICKER_TYPE_DATE);
        
        Label end = new Label("End Date:");
        end.getAllStyles().setBgColor(0x2d283e);
        end.getAllStyles().setFgColor(0xd3d3d3);
        //PickerComponent enddate = PickerComponent.createDate(null);
        Picker enddate = new Picker();
        enddate.setType(Display.PICKER_TYPE_DATE);
       
        rate.getAllStyles().setMarginBottom(100);
        Label err = new Label(" ");
        err.setAlignment(CENTER);
        err.getAllStyles().setFgColor(0xfe0000);
        err.getAllStyles().setMarginBottom(100);
        Button r = new Button("Register");
        Button cancel = new Button("Cancel");
        cancel.getAllStyles().setMarginBottom(100);
        cancel.getAllStyles().setBorder(Border.createEmpty());
        cancel.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        cancel.getAllStyles().setBgTransparency(255);
        cancel.getAllStyles().setBgColor(0x2d283e);
        cancel.getAllStyles().setFgColor(0xd3d3d3);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                prev.showBack();}
        });

        r.getAllStyles().setMarginBottom(100);
        r.getAllStyles().setBorder(Border.createEmpty());
        r.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        r.getAllStyles().setBgTransparency(255);
        r.getAllStyles().setBgColor(0x2d283e);
        r.getAllStyles().setFgColor(0xd3d3d3);
        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (name.getText().equals("") || rate.getText().equals("") || startdate.getDate()==null || enddate.getDate()==null) {
                    err.setText("Empty fields!!");
                    revalidate();
                }
                else{
                System.out.println(startdate.getDate());
                    
                PromoServices.getInstance().createPromotion(name.getText(), Integer.parseInt(rate.getText()),startdate.getDate(),enddate.getDate());
                Label error = new Label("successfully added");
                Dialog d = new Dialog("", BoxLayout.y());
                d.addAll(error);
                d.showPopupDialog(r);
                new AllPromotionForm(new HomeForm()).show();}}

        });
        c.addAll(l, name, ra, rate, s, startdate, end, enddate, err, r, cancel);
        addAll(c);
        this.show();

    }

}
