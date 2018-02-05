/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jeroen
 */
public class Waardepapier implements Serializable{
    
    private String symbol = "leegPapier";
    private Double aantal =  0.00;
    private Double koers = 0.00;

    public Waardepapier(){
        
    }
    public Waardepapier(String symbol, Double aantal, Double koers){
        this.symbol = symbol;
        this.aantal = aantal;
        this.koers =  koers;
        
    }
    
    public Waardepapier(String symbol){
        this.symbol  = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getAantal() {
        return aantal;
    }

    public void setAantal(Double aantal) {
        this.aantal = aantal;
    }
     public void addAantal(Double aantal) {
        this.aantal += aantal;
    }

    public Double getKoers() {
        return koers;
    }

    public void setKoers(Double koers) {
        this.koers = koers;
    }

    @Override
    public String toString() {
        return "Waardepapier{" + "symbol=" + symbol + ", aantal=" + aantal + ", verkoopKoers=" + koers + " huidige waarde  " +aantal*koers+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.symbol);
        hash = 13 * hash + Objects.hashCode(this.aantal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Waardepapier other = (Waardepapier) obj;
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.aantal, other.aantal)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
