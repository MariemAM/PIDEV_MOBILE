/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Produit_Promo;

/**
 *
 * @author Mariem
 */
public class ProductsDetailsForm extends MenuForm {

    public ProductsDetailsForm(Produit_Promo p, Form prev) {
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        this.setTitle("Details");
        this.setLayout(BoxLayout.y());
        MultiButton mb1 = new MultiButton();
        mb1.setTextLine1(p.getNom());
        mb1.setTextLine2(p.getPrix_promo().toString()+" T.N.D");
        Label l=new Label(p.getPrix().toString());
        l.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        mb1.setTextLine3(l.getText()+"T.N.D");
        mb1.setTextLine4(p.getDesc());
        mb1.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(120));
        mb1.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        mb1.getAllStyles().setBgTransparency(255);
        mb1.getAllStyles().setBgColor(0x2d283e);
        mb1.getAllStyles().setFgColor(0xd1d7e0);
        mb1.getAllStyles().setMargin(20, 20, 20, 20);
        ShareButton button = new ShareButton();
        button.setText("  Share with friends");
        button.setTextToShare("" + p.getNom() + "Price before discount: " + p.getPrix() + "Price after discount:" + p.getPrix_promo());
        button.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        button.getAllStyles().setBgTransparency(255);
        button.getAllStyles().setBgColor(0x2d283e);
        button.getAllStyles().setFgColor(0xd1d7e0);
        button.getAllStyles().setMargin(20, 20, 20, 20);
        this.add(mb1);
        this.add(button);
        this.show();
    }

}
