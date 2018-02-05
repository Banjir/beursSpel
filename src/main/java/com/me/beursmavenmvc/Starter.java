/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc;

import com.me.beursmavenmvc.model.Game;
import com.me.beursmavenmvc.controller.MainController;

/**
 *
 * @author jeroen
 */
public class Starter {
     public static void main(String[] args) {

        Game game = new Game();
        MainController mc = new MainController(game); //view van controller registreert zich bij game
        game.start();

    }
}
