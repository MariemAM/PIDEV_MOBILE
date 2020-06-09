/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.getDisplayHeight;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;

/**
 *
 * @author rejeb
 */
public class MenuForm extends Form{

    public MenuForm() {
        getAllStyles().setBorder(Border.createEmpty());
        getAllStyles().setBackgroundType(BACKGROUND_NONE);
        getAllStyles().setBgTransparency(255);
        getAllStyles().setBgColor(0x0d0d0d);
        Toolbar tb = this.getToolbar(); 
        
        Label logo=new Label("HUNT KINGDOM");
        logo.getStyle().setFgColor(0xa6a6a6);
        logo.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Container topBar = BorderLayout.centerCenter(logo);
        tb.getAllStyles().setBorder(Border.createEmpty());
        tb.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        tb.getAllStyles().setBgTransparency(255);
        tb.getAllStyles().setBgColor(0x0d0d0d);
        
        topBar.setUIID("SideCommand");
        topBar.getStyle().setPaddingTop(10);
        topBar.getStyle().setPaddingBottom(20);
        tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu(" Home ", FontImage.MATERIAL_HOME, e -> {
            Form f=new HomeForm();
            f.setTransitionOutAnimator(CommonTransitions.createEmpty());
            f.show();
        }); 
        tb.addMaterialCommandToSideMenu(" Shop ", FontImage.MATERIAL_SHOP, e -> {
            Form f=new ShopTestForm(getCurrentForm());
            f.setTransitionOutAnimator(CommonTransitions.createEmpty());
            f.show();
        });
        tb.addMaterialCommandToSideMenu(" Cart ", FontImage.MATERIAL_SHOPPING_CART, e -> {
            Form f=new CartForm(getCurrentForm());
            f.setTransitionOutAnimator(CommonTransitions.createEmpty());
            f.show();});
        tb.addMaterialCommandToSideMenu(" Orders ", FontImage.MATERIAL_DASHBOARD, e -> {
            Form f=new OrdersForm(getCurrentForm());
            f.setTransitionOutAnimator(CommonTransitions.createEmpty());
            f.show();});
         tb.addMaterialCommandToSideMenu("Promote ", FontImage.MATERIAL_REDEEM, e -> {
            Form f=new AllPromotionForm(getCurrentForm());
            f.setTransitionOutAnimator(CommonTransitions.createEmpty());
            f.show();
         });
        Container c=new Container(BoxLayout.y()){
            @Override
            protected Dimension calcPreferredSize() {
                Dimension d = super.calcPreferredSize(); 
                d.setHeight(getDisplayHeight() *1/ 2);
                return d;
            }                
        };
        c.setUIID("SideCommand");
        tb.addComponentToSideMenu(c);
        
    }
    
}
