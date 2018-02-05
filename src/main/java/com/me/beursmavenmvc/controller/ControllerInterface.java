/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.controller;

/**
 *
 * @author jeroen
 */
public interface ControllerInterface {
    public void updateKoersen();
    public void updateWallet();
    public void gaNaar(String keuze);
    
    public void sell(String naam, Double aantal );
    
    public void buy(String naam, Double bedrag);
    
    public void selectPlayer(String naam);
    
   
}
