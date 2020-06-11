/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.PromoServices;
import static com.mycompany.myapp.utils.SmsSender.ACCOUNT_SID;
import static com.mycompany.myapp.utils.SmsSender.AUTH_TOKEN;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author Mariem
 */
public class AllPromotionForm extends MenuForm {

    public AllPromotionForm(Form prev) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        getToolbar().addMaterialCommandToOverflowMenu("Back", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        getToolbar().addMaterialCommandToOverflowMenu("new", FontImage.MATERIAL_PLUS_ONE, e ->new AddPromoForm(getCurrentForm()));
        getToolbar().addMaterialCommandToOverflowMenu("view", FontImage.MATERIAL_VISIBILITY, e ->new PromotionnelProductsForm(getCurrentForm()));
         Toolbar.setGlobalToolbar(true);
        this.setLayout( new BoxLayout(BoxLayout.Y_AXIS));
         setTitle("Promotions");
//         setTitle("Search");
//        add(new InfiniteProgress());
//      //  Display.getInstance().scheduleBackgroundTask(()-> {
//    // this will take a while...
//    //Contact[] cnts = Display.getInstance().getAllContacts(true, true, true, true, false, false);
//    Display.getInstance().callSerially(() -> {
//        removeAll();
//       // Iterable<Contact> cnts = null;
//       revalidate();
//       });
//});
//getToolbar().addSearchCommand(e -> { 
//    //removeAll();
//    
//    String text = (String)e.getSource();
//    System.out.println(text);
//    if(text == null || text.length() == 0) {
//        // clear search
//        for(Component cmp : getContentPane()) {
//            cmp.setHidden(false);
//            cmp.setVisible(true);
//        }
//        getContentPane().animateLayout(150);
//    } else {
//        text = text.toLowerCase();
//        
//        for(Component cmp : getContentPane()) {
//            //Container mb = (Container)cmp;
//          //  String line1 = mb.;
//            //String line2 = mb.getTextLine2();
//            //boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
//                //    line2 != null && line2.toLowerCase().indexOf(text) > -1;
//           // mb.setHidden(!show);
//           // mb.setVisible(show);
//        }
//        getContentPane().animateLayout(150);
//    }
//}, 4);
       Container root = new Container(BoxLayout.yCenter());
        root.getAllStyles().setMargin(20, 20, 20, 20);
        root.getAllStyles().setPadding(20, 50, 20, 20);
        root.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(120));
        root.getAllStyles().setBgColor(0x2d283e);
        root.getAllStyles().setBgTransparency(255);

        ArrayList<Promotion> liste = PromoServices.getInstance().getAllTasks();
        for (Promotion c : liste) {
            Container cont = new Container(new BoxLayout(CENTER));
            Container cont2 = new Container(new BoxLayout(CENTER));
            Label stdate = new Label(formatter.format(c.getDateDebut()));
            Container datex = BorderLayout.centerEastWest(null, stdate, new Label("Start Date : "));
            Label eddate = new Label(formatter.format(c.getDateFin()));
            Container dateLivx = BorderLayout.centerEastWest(null, eddate, new SpanLabel("End Date : "));
            Label name = new Label(c.getNom());
            Container p = BorderLayout.centerEastWest(null, name, new Label("Name: "));
            Label rt = new Label(String.valueOf(c.getTauxReduction()+ "%"));
            Container statusx = BorderLayout.centerEastWest(null, rt, new Label("Rate: "));
            name.getAllStyles().setFgColor(0x202020);
            rt.getAllStyles().setFgColor(0x202020);
            eddate.getAllStyles().setFgColor(0x202020);
            stdate.getAllStyles().setFgColor(0x202020);

            cont2.setLeadComponent(stdate);

            stdate.addPointerReleasedListener(evt -> {

                new ProductsPerPromoForm(getCurrentForm(), c).show();

            });
            Container actions=new Container(BoxLayout.xRight());
            
         Button overflow = new Button();
         overflow.setUIID("overflow");
         FontImage.setMaterialIcon(overflow, FontImage.MATERIAL_MORE_VERT);
        
         overflow.addPointerReleasedListener((ActionEvent e) -> {
         Button edit=new Button("edit");
         edit.setUIID("edit");
         FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT, 5);
            Button delete=new Button("delete");
            delete.setUIID("delete");
             FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 5);
            
        
          edit.addPointerReleasedListener((ActionListener) (ActionEvent evt) -> {
              new editPromoForm(getCurrentForm(),c).show();
         });
          delete.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   Button showDialog = new Button("Tint");
                   showDialog.addActionListener((e) -> Dialog.show("Warning", "Are you sure to delete the promotion "+c.getNom()+"!!", "YES", "NO"));
                    if(Dialog.show("Warning", "Are you sure to delete the promotion!!", "YES", "NO")) {
                    PromoServices.getInstance().deletePromotion(c);
                    getCurrentForm().show();
                    new AllPromotionForm(prev).show();
                    
                       
                    } else {}
                }
            });
          
         Dialog d = new Dialog("", BoxLayout.y());
         d.addAll(edit, delete);
         d.showPopupDialog(overflow);
        });

         Button message = new Button();
         message.setUIID("message");
         FontImage.setMaterialIcon(message, FontImage.MATERIAL_MESSAGE);
         
         message.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                     Dialog dlg = new Dialog("message");
       Style dlgStyle = dlg.getDialogStyle();
       dlgStyle.setBorder(Border.createEmpty());
       dlgStyle.setBgTransparency(255);
       dlgStyle.setBgColor(0xffffff);

       Label title = dlg.getTitleComponent();
       FontImage.setMaterialIcon(title, FontImage.MATERIAL_MESSAGE);
       //title.setIcon(FontImage.MATERIAL_VISIBILITY);
       title.getUnselectedStyle().setFgColor(0xff);
       title.getUnselectedStyle().setAlignment(Component.LEFT);

       dlg.setLayout(BoxLayout.y());
       Label blueLabel = new Label();
       blueLabel.setShowEvenIfBlank(true);
       blueLabel.getUnselectedStyle().setBgColor(0xff);
       blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
       blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
       dlg.add(blueLabel);
       TextArea ta = new TextArea("Check out our new discounts "+c.getNom()+" up to "+c.getTauxReduction()+"%" );
       ta.setEditable(false);
       ta.setUIID("DialogBody");
       ta.getAllStyles().setFgColor(0);
       dlg.add(ta);

       Label grayLabel = new Label();
       grayLabel.setShowEvenIfBlank(true);
       grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
       grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
       grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
       dlg.add(grayLabel);

       Button ok = new Button(new Command("OK"));
       ok.getAllStyles().setBorder(Border.createEmpty());
       ok.getAllStyles().setFgColor(0);
       ok.addPointerReleasedListener(i->{
           for(User u:PromoServices.getInstance().getAPImessage())
           {
               Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
           Message message = Message
                .creator(new PhoneNumber(u.getPhone()), // integration
                        new PhoneNumber("+17606546225"), // from
                        ta.getText())
                .create();
         System.out.println(message.getSid());
         System.out.println("API Done");
           
           }         
       });
       dlg.add(ok);
       dlg.showDialog();
                }
            });
         
             ShareButton share = new ShareButton();
             share.setTextToShare("Check out our new discounts "+c.getNom()+" up to "+c.getTauxReduction()+"%");
             share.setAlignment(LEFT);
             overflow.setAlignment(RIGHT);
             overflow.getAllStyles().setFgColor(0x202020);
             actions.addAll(message,share,overflow);
            
            cont2.addAll(p, datex, dateLivx, statusx);
            cont2.getAllStyles().setBorder(Border.createEmpty());
            cont2.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            cont2.getAllStyles().setBgTransparency(255);
            cont2.getAllStyles().setBgColor(0xd1d7e0);
            cont2.getAllStyles().setMargin(20, 20, 20, 20);
            cont2.getAllStyles().setPadding(20, 50, 20, 20);
            cont2.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                    strokeOpacity(120));
            

            cont.addAll(cont2,actions);
            cont.getAllStyles().setBorder(Border.createEmpty());
            cont.getAllStyles().setBackgroundType(BACKGROUND_NONE);
            cont.getAllStyles().setBgTransparency(255);
            cont.getAllStyles().setBgColor(0xd1d7e0);
            cont.getAllStyles().setMargin(20, 20, 20, 20);
            cont.getAllStyles().setPadding(20, 50, 20, 20);
            cont.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                    strokeOpacity(120));
            root.addAll(cont);
            // ct.add(t);

        }

        add(root);
    }

}
