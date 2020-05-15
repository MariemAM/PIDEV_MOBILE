/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Mariem
 */
public class HomeForm extends MenuForm {
    
    public HomeForm(){
        super();

        setTitle("Home");
        setLayout(BoxLayout.y()); 
       
        setTransitionOutAnimator(CommonTransitions.createEmpty());
    }
}
