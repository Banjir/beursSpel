/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.controller;

import com.me.beursmavenmvc.view.BuyMenu;
import com.me.beursmavenmvc.view.MenuView;
import com.me.beursmavenmvc.model.Model;

/**
 *
 * @author jeroen
 */
public class BuyController implements ControllerInterface{
    Model gameModel;
    MenuView menu;
    ControllerInterface parent;
    ControllerInterface child;
    
    BuyController(MainController mc) {
        parent = mc;
        gameModel = mc.getModel();
        menu = new BuyMenu( gameModel);
        menu.activeerInput("sell", ()-> sell(menu.getFieldValue(0), Double.parseDouble(menu.getFieldValue(1))));
        menu.activeerInput("buy", ()-> buy(menu.getFieldValue(0), Double.parseDouble(menu.getFieldValue(1))));
        menu.activeerInput("terug", ()-> gaNaar("parent"));
        
    }
    
     
    private  void sell(String naam, Double aantal) {
            gameModel.sell(naam, aantal);
    }

   
    private  void buy(String naam, Double bedrag) {
        gameModel.buy(naam, bedrag);
    }

   
    
    

    @Override
    public void updateKoersen() {
         menu.setActivity(true);
        gameModel.updateKoersen();
    }

    @Override
    public void updateWallet() {
        menu.setActivity(true);
        gameModel.updateWallet();
    }

    @Override
    public void gaNaar(String keuze) {
        this.menu.setActivity(false);
        
       parent.updateKoersen(); // update set parents eigen menu default naar true;
    }

   
    @Override
    public MenuView getMenu() {
        return menu;
    }

    @Override
    public Model getModel() {
        return gameModel;
    }

    
    
}
