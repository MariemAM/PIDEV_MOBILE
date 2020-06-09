/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Produit_Promo;
import com.mycompany.myapp.services.Product_PromoService;
import java.util.Hashtable;
import java.util.Vector;


/**
 *
 * @author Mariem
 */
public class PromotionnelProductsForm extends MenuForm {

    public PromotionnelProductsForm(Form prev) {
         getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        this.setTitle("My Offers");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Storage store = Storage.getInstance();
        Vector produits = new Vector();
       Hashtable produit = new Hashtable();

       Image icon=null;
       Style s = UIManager.getInstance().getComponentStyle("Button");
       icon=FontImage.createMaterial(FontImage.MATERIAL_FILTER,s);
       Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Tabs t=new Tabs();
       t.addTab("filter", icon, c);
        for (Produit_Promo e : Product_PromoService.getInstance().getAllPromoProducts()) {
            produit.put("nom", e.getNom());
            produit.put("prix", e.getPrix());
            produit.put("prixp", e.getPrix_promo());
            produit.put("desc", e.getDesc());
            produits.add(produit);
            MultiButton mb1 = new MultiButton();
            mb1.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
            strokeOpacity(120));
            mb1.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            mb1.getAllStyles().setBgTransparency(255);
            mb1.getAllStyles().setBgColor(0x2d283e);
            mb1.getAllStyles().setFgColor(0xd1d7e0);
            mb1.getAllStyles().setMargin(20, 20, 20, 20);

            mb1.setTextLine1(e.getNom());
            mb1.setTextLine2(e.getPrix_promo().toString()+" T.N.D");
            mb1.setTextLine3(e.getPrix().toString()+" T.N.D");
            mb1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new ProductsDetailsForm(e,getCurrentForm());
                }
            });
            this.add(mb1);
           
        }

        boolean verifstorage = store.writeObject("infoproduits", produits);
        if (verifstorage == true) {
            System.out.println("Success");
        } else {
            System.out.println("Echec");
        }
        this.show();
        
    }

}
