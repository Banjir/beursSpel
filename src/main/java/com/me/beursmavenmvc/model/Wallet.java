/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jeroen
 */
public class Wallet implements Serializable{

    private Double cash = 1000.00;
    private List<Waardepapier> papieren = new ArrayList<>();

    private Double waardeVanPapieren = 0.0;

    public void updateWaarden(Beurs beurs) {
      //  System.out.println("wallet.updateWaarden");
        beurs.updateKoersen();
        papieren.forEach(p -> {
            geefPapierActueleKoers(p, beurs);
        });
        berekenWaarde();
    }

    private void geefPapierActueleKoers(Waardepapier papier, Beurs beurs) {
        Quote quote = beurs.getKoers(papier.getSymbol());
        papier.setKoers(quote.getPrice());
    }

    private Double berekenWaarde() {
       // System.out.println("bereken waarde van uw papieren in wallet " + papieren);
        double temp = 0;
        for (Waardepapier w : papieren) {
            temp += w.getAantal() * w.getKoers();
        }
        waardeVanPapieren = Math.round(temp * 100) / 100.0;

        return waardeVanPapieren;
    }

    public boolean spendCash(Double amount) throws GameException {

        if (amount <= cash) {
            cash -= amount;
            return true;
        } else {
            System.out.println("zoveel cash heeft u niet beschikbaar..");
            throw new GameException("zoveel cash heeft u niet beschikbaar exception..");
            //return false;
        }
    }

    public Double addCash(Double meerCash) {
        cash += meerCash;

        return cash;
    }



    public List<Waardepapier> getPapieren() {
        return papieren;
    }



    public void addPapier(Waardepapier papier) {
       // System.out.println("wallet.addPapier "+ papier);
        Optional<Waardepapier> optie = papieren.stream().filter(p -> p.getSymbol().equalsIgnoreCase(papier.getSymbol())).findFirst();
        if (optie.isPresent()) {
            Waardepapier bestaatReeds = optie.get();
            bestaatReeds.addAantal(papier.getAantal());
        } else {
           // System.out.println("nieuw papier "+ papier);
            this.papieren.add(papier);
        }
        this.berekenWaarde();
    }


    @Override
    public String toString() {
        String mooi = "\nMijn portemonaie: \n";   // mooier met StringbUilder maar nog niet gehad
               
        for (Waardepapier p : papieren) mooi += p +"\n";
        
        mooi += "**************************************************************************** \n"
                + "** waarde papieren totaal $ " + waardeVanPapieren  + "\n"
                + "** cash beschikbaar: $ " + cash + "\n";
        return mooi;
       // return "Wallet{" + "cash=" + cash + ", papieren=" + papieren + ", waardeVanPapieren=" + waardeVanPapieren + '}';
    }
}
