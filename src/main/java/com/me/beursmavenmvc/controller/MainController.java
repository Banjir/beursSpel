/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.controller;

import com.me.beursmavenmvc.view.HoofdMenu;
import com.me.beursmavenmvc.view.MenuView;
import com.me.beursmavenmvc.model.Model;

/**
 *
 * @author jeroen
 */
public class MainController implements ControllerInterface  {
    Model gameModel;
    MenuView menu;
    MainController parent;
    BuyController child;
    
    
    public MainController(Model gameModel) {
        this.gameModel = gameModel;
        menu = new HoofdMenu(this, gameModel);
        
        menu.setWeergave("maak uw keuze \n"
                    + "1- kopen \n"
                    + "2- verkopen \n"
                    + "3- geef actuele quotes \n"
                    + "4- geef waarde wallet \n"
                    + "5- uitloggen \n" );
        
        this.child = new BuyController(this);
        
    }
    public void updateKoersen() {
        menu.setActivity(true);
        gameModel.updateKoersen();
    }
    public void updateWallet() {
        menu.setActivity(true);
        gameModel.updateWallet();
    }
    public void gaNaar(String keuze){
        this.menu.setActivity( false);
        this.child.menu.setActivity(true);
        child.updateWallet();
        
    }
    
    public void sell(String naam, Double aantal ) {
        
    }
    
    public void buy(String naam, Double bedrag) {
        
    }
    
    public void selectPlayer(String naam){
        
    }
    

    
    
}
