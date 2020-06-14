/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class HomeForm extends Form {
    Form current;

     public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddEvent = new Button("Add Event");
        Button btnListEvents = new Button("List Events");
        
        btnAddEvent.addActionListener(e-> new AddEventForm(current).show());
        btnListEvents.addActionListener(e-> new AfficheEvent(current).show());
        addAll(btnAddEvent,btnListEvents);
        
        
    }
}
