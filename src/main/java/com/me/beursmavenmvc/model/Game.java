/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.model;

import com.me.beursmavenmvc.view.BeursObserver;
import com.me.beursmavenmvc.view.WalletObserver;
import com.me.beursmavenmvc.view.GameExceptionObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author jeroen
 */
public class Game implements Model{

    private GameHelper helper;
    private Beurs beurs;
    private Player player;
    
    List<BeursObserver> beursObservers= new ArrayList<>();
    List<WalletObserver> walletObservers= new ArrayList<>();
    List<GameExceptionObserver> exceptionObservers= new ArrayList<>();


    public Game() {
        helper = new GameHelper();
        beurs = new Beurs();

    }

    public void start() {
        String wie = helper.getUserInput("Geef naam speler: ");
        player = helper.loadPlayer(wie);
        
        updateKoersen();
        
       

        

    }   

    

    

    @Override
    public void start(String speler) {
        player = helper.loadPlayer(speler);
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateKoersen() {
        List<Quote> quotes = beurs.updateKoersen();
         
        List<String> koersen = quotes.stream().map(q ->q.toString()).collect(Collectors.toList());
        
        beursObservers.forEach(e -> e.updateBeurs(koersen));
        
        
    }

    @Override
    public void updateWallet() {
        Wallet w = player.showBling(beurs);
        walletObservers.forEach(e -> e.updateWallet(w.toString()));
    }

    @Override
    public void buy(String bedrijf, Double geld) {
        try {
            player.buy(geld, bedrijf, beurs);
        } catch (GameException ex) {
          //  Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          exceptionObservers.forEach(e -> e.showException(ex.getMessage()));
            
        }
        updateWallet();
                
    }

    @Override
    public void sell(String bedrijf, Double aantal) {
        player.sell(aantal, bedrijf, beurs);
        updateWallet();
    }

    @Override
    public void registerObserver(WalletObserver o) {
        this.walletObservers.add(o);
    }

    @Override
    public void removeObserver(WalletObserver o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerObserver(BeursObserver o) {
        this.beursObservers.add(o);
    }

    @Override
    public void removeObserver(BeursObserver o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void registerObserver(GameExceptionObserver o) {
        this.exceptionObservers.add(o);
    }

}
