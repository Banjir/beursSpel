/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.model;

import com.me.beursmavenmvc.view.BeursObserver;
import com.me.beursmavenmvc.view.WalletObserver;
import com.me.beursmavenmvc.view.GameExceptionObserver;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author jeroen
 */
public class Game implements Model{

    private GameHelper helper;
    private Beurs beurs;
    private Player player;
    
    Set<BeursObserver> beursObservers= new HashSet<>();
    Set<WalletObserver> walletObservers= new HashSet<>();
    Set<GameExceptionObserver> exceptionObservers= new HashSet<>();


    public Game() {
        helper = new GameHelper();
        beurs = new Beurs();

    }

    public void start() {
        String wie = helper.getUserInput("Geef naam speler: ");
        player = helper.loadPlayer(wie);
        
        updateKoersen();               

    }   
    

        

  
    public void start(String speler) {
        player = helper.loadPlayer(speler);
    }

    @Override
    public void end() {
         helper.savePlayer(player);
        System.exit(0);
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
        this.walletObservers.remove(o);
    }

    @Override
    public void registerObserver(BeursObserver o) {
        this.beursObservers.add(o);
    }

    @Override
    public void removeObserver(BeursObserver o) {
        this.beursObservers.remove(o);
    }
    
    @Override
    public void registerObserver(GameExceptionObserver o) {
        this.exceptionObservers.add(o);
    }

}
