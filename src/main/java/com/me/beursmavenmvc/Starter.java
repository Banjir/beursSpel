/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc;

import com.me.beursmavenmvc.model.Game;
import com.me.beursmavenmvc.controller.MainController;
import com.me.beursmavenmvc.model.Model;
import com.me.beursmavenmvc.view.HoofdMenu;
import com.me.beursmavenmvc.view.MenuView;

/**
 *
 * @author jeroen
 */
public class Starter {
     public static void main(String[] args) {

        Model game = new Game();
        MenuView menu = new HoofdMenu(game);
        MainController mc = new MainController(game, menu); 
        game.start();

    }
}
