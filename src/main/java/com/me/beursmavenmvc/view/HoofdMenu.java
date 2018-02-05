/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.view;

import com.me.beursmavenmvc.model.Model;
import com.me.beursmavenmvc.controller.ControllerInterface;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jeroen
 */
public class HoofdMenu implements WalletObserver, BeursObserver, MenuView {
//    private HoofdMenu parent;
//    private List<Menu> children;

    private Scanner input = new Scanner(System.in);
    private String weergave;

    private Model gameModel;
    private ControllerInterface controller;
    private boolean actief = true;

    public HoofdMenu(ControllerInterface controller, Model gameModel) {

        this.gameModel = gameModel;
        this.controller = controller;

        gameModel.registerObserver((BeursObserver) this);
        gameModel.registerObserver((WalletObserver) this);

    }

    public void setWeergave(String weergave) {
        this.weergave = weergave;
    }

    public void show() {

        System.out.println(this.weergave);

        kies(input.nextLine());
    }

    public void kies(String keuze) {

        switch (keuze) {
            case "1":
                
                controller.gaNaar("1");
                break;
            case "2":
                controller.gaNaar("1");
                break;
            case "3":
                controller.updateKoersen();
                break;
            case "4":
                controller.updateWallet();
                break;
            default:
//                    doorgaan = false;

        }

    }

    @Override
    public void updateWallet(String wallet) {
        if (this.actief) {
            System.out.println("verse dikke portemonaie: " + wallet);
            show();
        }
    }

    @Override
    public void updateBeurs(List<String> quotes) {
        if (this.actief) {
            System.out.println("verse quotes: " + quotes);
            show();
        }
    }

    public boolean setActivity(boolean act) {
        this.actief = act;

        return this.actief;
    }

}
