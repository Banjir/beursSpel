/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.view;

import com.me.beursmavenmvc.model.Model;
import com.me.beursmavenmvc.view.WalletObserver;
import com.me.beursmavenmvc.view.MenuView;
import com.me.beursmavenmvc.controller.BuyController;
import java.util.Scanner;

/**
 *
 * @author jeroen
 */
public class BuyMenu implements MenuView, WalletObserver, GameExceptionObserver {

    BuyController controller;
    Model game;
    
    String weergave = "wie wil je kopen? ";
    String weergave2 = "Voor hoeveel geld($) wil je kopen?";
    Scanner input = new Scanner(System.in);
    boolean actief;

    public BuyMenu(BuyController bc, Model gameModel) {
        this.controller = bc;
        this.game = gameModel;
        game.registerObserver((WalletObserver)this);
        game.registerObserver((GameExceptionObserver)this);

    }

    @Override
    public void setWeergave(String weergave) {

        weergave = "wie wil je kopen? ";
        weergave2 = "Voor hoeveel geld($) wil je kopen?";

    }

    @Override
    public void show() {
        System.out.println("wilt u handelen? j/n  ");
        String handelen = input.nextLine();
        if (!handelen.equalsIgnoreCase("j")) {
           controller.gaNaar("1");
        } else {

            System.out.println("wilt u een aankoop of verkoop doen? a/v  ");
            String keuze = input.nextLine();
            if (keuze.equalsIgnoreCase("a")) {

                System.out.println(this.weergave);
                String bedrijf = input.nextLine();
                System.out.println(this.weergave2);
                Double geld = 0.0;
                try {
                    geld = Double.parseDouble(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("geef getal ");
                    show();
                }

                controller.buy(bedrijf, geld);
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

                controller.sell(bedrijf, geld);
            }
        }
    }

    @Override
    public void kies(String keuze) {

        switch (keuze) {
            case "1":

                controller.gaNaar("1");
                break;

            default:
                controller.gaNaar("1");
                break;

        }

    }

    @Override
    public void updateWallet(String wallet) {
        if (this.actief) {
            System.out.println("uw nieuwe portemonaie na kopen: " + wallet);

            show();
        }

    }

    @Override
    public boolean setActivity(boolean act) {
        this.actief = act;
        return this.actief;
    }

    @Override
    public void showException(String exception) {
        System.out.println("exception van game " + exception);
    }

}
