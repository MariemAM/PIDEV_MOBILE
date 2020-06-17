/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rejeb
 */
public class CommandeServices {
    
    public ArrayList<Commande> cmds;
    
    public static CommandeServices instance=null;
    
    public boolean resultOK;
    
    private ConnectionRequest req;
    
    private CommandeServices() {
         req = new ConnectionRequest();
    }
     
    public static CommandeServices getInstance() {
        if (instance == null) {
            instance = new CommandeServices();
        }
        return instance;
    }
     public ArrayList<Commande> parseOrders(String jsonText) throws ParseException {
        try {
            cmds=new ArrayList<>();
            JSONParser j = new JSONParser();

            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
           
            for(Map<String,Object> obj : list){
                
                Commande c = new Commande();
                
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                
                
                Date date=this.format(obj.get("date").toString());
                c.setDate(date);
                Object o= obj.get("dateLivraison");
                if(o!=null){
                     Date date_liv=this.format(o.toString());
                     c.setDateLivraison(date_liv);
                }

                c.setStatus(obj.get("status").toString());
                
                String address=obj.get("adresse").toString();
                c.setAddresse(address);
                
                String tel=obj.get("tel").toString();
                c.setTel(tel);
                String charge=obj.get("chargeId").toString();
                c.setChargeId(charge);
                
                Double prix = Double.parseDouble(obj.get("prixTotal").toString());
                c.setPrixTotal(prix);
                c=parceOrdersDetails(c, obj.get("lignes_commande")); 
                cmds.add(c);
                
            }
     
        } catch (IOException ex) {
            
        }
        
        return cmds;
    }
    private Commande parceOrdersDetails(Commande c,Object obj){
         List<Map<String,Object>> list = (List<Map<String,Object>>)obj;
         for(Map<String,Object> o : list){
             LigneCommande lc=new LigneCommande();
                float id = Float.parseFloat(o.get("id").toString());
                lc.setId((int)id);
                float qte = Float.parseFloat(o.get("quantite").toString()); 
                lc.setQuantity((int)qte);
                Double prix = Double.parseDouble(o.get("prix").toString()); 
                lc.setPrice(prix);
                Map<String,Object> prod = (Map<String,Object>) o.get("produit");
                lc.setPname(prod.get("nom").toString());
                
                c.addLcs(lc);
                
         }
         System.out.println(c);
        return c;
    }
    private Date format(String input) throws ParseException{
         DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
                if ( input.endsWith( "Z" ) ) 
                {
                    input = input.substring( 0, input.length() - 1) + "GMT-00:00"; 
                } 
                else {
                    int inset = 6;

                    String s0 = input.substring( 0, input.length() - inset );
                    String s1 = input.substring( input.length() - inset, input.length() );

                    input = s0 + "GMT" + s1;
                }
                Date date=format.parse(input);
                return date;
     }
    public ArrayList<Commande> getAllOrders(){
        String url = Statics.BASE_URLi+"/orders/";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    cmds = parseOrders(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return cmds;
    }
}
