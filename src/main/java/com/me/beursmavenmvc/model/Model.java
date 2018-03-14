/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.model;

import com.me.beursmavenmvc.view.BeursObserver;
import com.me.beursmavenmvc.view.GameExceptionObserver;
import com.me.beursmavenmvc.view.WalletObserver;

/**
 *
 * @author jeroen
 */
public interface Model {
    void start();
    void end();
    
    void updateKoersen();
    void updateWallet();
    
    void buy(String bedrijf, Double geld);
    void sell(String bedrijf, Double aantal);
    
    void registerObserver(WalletObserver o);
    void registerObserver(BeursObserver o);
    void registerObserver(GameExceptionObserver o);
    
    void removeObserver(WalletObserver o);
    void removeObserver(BeursObserver o);
}
