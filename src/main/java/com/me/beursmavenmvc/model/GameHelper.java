
package com.me.beursmavenmvc.model;

import com.me.beursmavenmvc.model.Player;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroen
 */
public class GameHelper {
    Scanner input = new Scanner(System.in);
    
    public String getUserInput(String vraag) {
        System.out.println("* " + vraag);
        return input.nextLine();
    }    
    
    public boolean savePlayer(Player player) {
        
        try (FileOutputStream fout = new FileOutputStream(player.getName());
            ObjectOutputStream oos = new ObjectOutputStream(fout);){
                        
            oos.writeObject(player);            
            return true;
            
        } catch (IOException ex) {
            Logger.getLogger(GameHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
        
    }
    public Player loadPlayer(String name) {
        
        try (FileInputStream fin = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fin);){
            Player player = (Player)ois.readObject();
            System.out.println("loaded player "+  player.getName()   +  " from file");
            return player;
        
        } catch (IOException ex) {
            System.out.println("U bent nog niet bekend, welkom "+name);
            return new Player().setName(name);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameHelper.class.getName()).log(Level.SEVERE, null, ex);
            return new Player().setName(name);
        }
        
        
    }
    
    
}
