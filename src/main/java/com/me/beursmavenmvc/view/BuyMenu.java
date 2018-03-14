/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.view;

import com.me.beursmavenmvc.model.Model;
import com.me.beursmavenmvc.controller.BuyController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jeroen
 */
public class BuyMenu implements MenuView, WalletObserver, GameExceptionObserver {

   
    private final Map<String, Luisteraar> activiteiten;
    private final Model game;

    private  String weergave;
    private  String weergave2 ;
    
    private final Scanner input = new Scanner(System.in);
    private final String[] velden = new String[2];
    
    public BuyMenu( Model gameModel) {
    
        this.game = gameModel;
        activiteiten = new HashMap<>();
        
        weergave = "wie wil je kopen? ";
        weergave2 = "Voor hoeveel geld($) wil je kopen?";

    }
    
     @Override
    public void activeerInput(String key, Luisteraar value) {
        activiteiten.put(key, value);
    }
    
    @Override
    public void kies(String keuze) {
        
        activiteiten.get(keuze).activeer();

    }

    

    @Override
    public void show() {
        System.out.println("wilt u handelen? j/n  ");
        String handelen = input.nextLine();
        if (!handelen.equalsIgnoreCase("j")) {
           this.kies("terug");
        } else {

            System.out.println("wilt u een aankoop of verkoop doen? a/v  ");
            String keuze = input.nextLine();
            if (keuze.equalsIgnoreCase("a")) {
                System.out.println(this.weergave);
                velden[0] = input.nextLine();
                System.out.println(this.weergave2);
                Double geld = 0.0; // try catch logischer aan kant van controller?
                try {
                    String geldString = input.nextLine();
                    geld = Double.parseDouble(geldString);
                    velden[1] = geldString;
                    
                } catch (NumberFormatException e) {
                    System.out.println("geef getal ");
                    show();
                }

              //  controller.buy(bedrijf, geld);
              this.kies("buy");
            } else {
                System.out.println("wie wilt u verkopen?");
                String bedrijf = input.nextLine();
                System.out.println("hoeveel aandelen wilt u verkopen? ");
                Double geld = 0.0;
                try {
                    geld = Double.parseDouble(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("geef getal ");
                    show();
                }
                   kies("sell");
            
            }
        }
    }
 

    @Override
    public void updateWallet(String wallet) {

        System.out.println("uw nieuwe portemonaie na kopen: " + wallet);

        show();

    }

    @Override
    public boolean setActivity(boolean act) {

        if (act) {
            game.registerObserver((WalletObserver) this);

        } else {

            game.removeObserver((WalletObserver) (this));
        }
        return act;
    }

    @Override
    public void showException(String exception) {
        System.out.println("exception van game " + exception);
    }

    @Override
    public String getFieldValue(int fieldIndex) {
        return velden[fieldIndex];
    }

    

}
