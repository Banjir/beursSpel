/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.view;

/**
 *
 * @author jeroen
 */
public interface MenuView {

    public void show();

    void kies(String keuze);

    boolean setActivity(boolean act);

    void activeerInput(String key, Luisteraar value);

    String getFieldValue(int fieldIndex);

}
