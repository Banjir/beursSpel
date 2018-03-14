/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.view;

import com.me.beursmavenmvc.model.Model;
import com.me.beursmavenmvc.controller.ControllerInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jeroen
 */
public class HoofdMenu implements WalletObserver, BeursObserver, MenuView {

    private Scanner input = new Scanner(System.in);
    private String weergave;

    private final Model gameModel;

    private final Map<String, Luisteraar> activiteiten;

    public HoofdMenu( Model gameModel) {
        
        this.setWeergave("maak uw keuze \n"
                    + "1- kopen \n"
                    + "2- verkopen \n"
                    + "3- geef actuele quotes \n"
                    + "4- geef waarde wallet \n"
                    + "5- uitloggen \n" );
        
        this.gameModel = gameModel;
        this.activiteiten = new HashMap<>();

        gameModel.registerObserver((BeursObserver) this);
        gameModel.registerObserver((WalletObserver) this);

    }
    // dit eventueel vanuit interface afdwingen (java9)
     private void setWeergave(String weergave) {
        this.weergave = weergave;
    }


    @Override
    public void activeerInput(String key, Luisteraar value) {
        activiteiten.put(key, value);
    }

   
    @Override
    public void show() {

        System.out.println(this.weergave);

        kies(input.nextLine());
    }

    @Override
    public void kies(String keuze) {

        activiteiten.get(keuze).activeer();

    }

    @Override
    public String getFieldValue(int fieldIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //observermethoden
    @Override
    public void updateWallet(String wallet) {
        System.out.println("verse dikke portemonaie: " + wallet);
        show();
    }

    @Override
    public void updateBeurs(List<String> quotes) {
        System.out.println("verse quotes: " + quotes);
        show();
    }

    @Override
    public boolean setActivity(boolean act) {

        if (!act) {
            gameModel.removeObserver((BeursObserver) (this));
            gameModel.removeObserver((WalletObserver) (this));

        } else {
            gameModel.registerObserver((BeursObserver) this);
            gameModel.registerObserver((WalletObserver) this);
        }
        return act;
    }

}
