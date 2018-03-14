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
    private Model gameModel;
    private MenuView menu;
    private ControllerInterface parent;
    private ControllerInterface child;
    
    
    public MainController(Model gameModel, MenuView hoofdmenu) {
        this.gameModel = gameModel;
        menu = hoofdmenu;
        
        
        
        this.child = new BuyController(this);
        // hier wordt gebruik gemaakt van de Luisteraar interface
        menu.activeerInput("1", () -> gaNaar("1"));
        menu.activeerInput("2", () -> gaNaar("2"));
        menu.activeerInput("3", () -> updateKoersen());
        menu.activeerInput("4", () -> updateWallet());
        menu.activeerInput("5", () -> logOut());
        
        
    }
    public void logOut(){
        gameModel.end();
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
    public void gaNaar(String keuze){
        this.menu.setActivity( false);
        this.child.getMenu().setActivity(true);
        child.updateWallet();
        
    }
    
    public void sell(String naam, Double aantal ) {
        
    }
    
    public void buy(String naam, Double bedrag) {
        
    }
    
    public void selectPlayer(String naam){
        
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
