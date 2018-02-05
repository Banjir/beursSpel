/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.view;

import java.util.List;

/**
 *
 * @author jeroen
 */
public interface BeursObserver {
    void updateBeurs(List<String> quotes);
}
