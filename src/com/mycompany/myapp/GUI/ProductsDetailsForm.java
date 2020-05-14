/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit_Promo;

/**
 *
 * @author Mariem
 */
public class ProductsDetailsForm  extends MenuForm {

    public ProductsDetailsForm(Produit_Promo p,Form prev) {
         getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        this.setTitle("Details");
        this.setLayout(BoxLayout.y());
        MultiButton mb1=new MultiButton();
        mb1.setTextLine1(p.getNom());
        mb1.setTextLine2(p.getPrix_promo().toString());
        mb1.setTextLine3(p.getPrix().toString());
        mb1.setTextLine4(p.getDesc());
        ShareButton button=new ShareButton();
        button.setText("Inviter vos amis");
        button.setTextToShare("Reduction sur "+p.getNom()+"prix avant promotion: "+p.getPrix()+"prix apres promotion:"+p.getPrix_promo());
        this.add(mb1);
        this.add(button);
        this.show();
    }
    
    
}
