/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.controller;

import com.me.beursmavenmvc.model.Model;
import com.me.beursmavenmvc.view.MenuView;

/**
 *
 * @author jeroen
 */
public interface ControllerInterface {
    
     public MenuView getMenu() ;
     public Model getModel() ;
    
    public void updateKoersen();
    public void updateWallet();
    public void gaNaar(String keuze);
    
//    public void sell(String naam, Double aantal );
//    
//    public void buy(String naam, Double bedrag);
//    
//    public void selectPlayer(String naam);
    
   
}
