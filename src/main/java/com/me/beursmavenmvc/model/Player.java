/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.model;

import java.io.Serializable;
import java.util.Optional;

/**
 *
 * @author jeroen
 */
public class Player implements Serializable{

    private final Wallet myWallet = new Wallet();
    private String name = "De Speler";

    public Waardepapier buy(Double money, String symbol, Beurs winkel) throws GameException {
        
        myWallet.updateWaarden(winkel);
        Waardepapier papier = new Waardepapier();
        if (myWallet.spendCash(money)) {
            papier = winkel.placeBuyOrder(money, symbol);
         //   System.out.println("het papier na cashspending "+ papier);
            if (papier == null) myWallet.addCash(money);
            else myWallet.addPapier(papier);
        }

        return papier;
    }

    public Double sell(Double aantal, String symbol, Beurs winkel) {
        
         Waardepapier papier ;
        myWallet.updateWaarden(winkel);
        double opbrengst = 0;
        Optional<Waardepapier> papierOptie = myWallet
                .getPapieren()
                .stream()
                .filter(p -> p.getSymbol().equals(symbol))
                .findFirst();
                
        if (papierOptie.isPresent())  papier = papierOptie.get();
        else {
            System.out.println("\nu probeert te verkopen wat u niet heeft...\n");
            return opbrengst;}
        double voorraad = papier.getAantal();
        
        if (aantal.equals(-1.0)) {
            aantal = voorraad;
        }
        if (voorraad >= aantal ){
            papier.setAantal(voorraad - aantal);
            myWallet.addCash(aantal* papier.getKoers());
           
            if (voorraad-aantal < 0.000001) {
                myWallet.getPapieren().remove(papier);
            }
        }
        else {
            System.out.println("zoveel aandelen zijn helaas niet beschikbaar ");
        }
        
        return opbrengst;
    }

    public Wallet showBling(Beurs winkel) {
        myWallet.updateWaarden(winkel);
        return myWallet;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }
}
