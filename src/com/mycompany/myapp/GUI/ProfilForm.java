/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;

/**
 *
 * @author bhk
 */
public class ProfilForm extends MenuForm{

    public void addSideMenu(){      
       
        Toolbar tb = getToolbar();
       
        Container topBar = BorderLayout.east(new Label());
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline")); 
        topBar.setUIID("SideCommand");
        
        tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e->new HomeProfilForm().show());
        tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_ACCOUNT_BOX, e->new ListPostForm().show());
        tb.addMaterialCommandToSideMenu("Claims", FontImage.MATERIAL_INFO, e->new ListClaimForm().show());
        tb.addMaterialCommandToSideMenu("Capture", FontImage.MATERIAL_INFO, e->new SettingsForm().show());
       
    
        refreshTheme();}

}
