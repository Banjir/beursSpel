/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.beursmavenmvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jeroen
 */
public class Beurs implements Serializable{

    private final RestTemplate restTemplate = new RestTemplate();
    private List<Quote> koersen = new ArrayList<>();
    private String baseUrl = "https://api.iextrading.com/1.0/tops/last?symbols=";
    private String[] bedrijven = {"AAPL", "AMZN", "GOOG", "FB", "MSFT", "TWTR", "SNAP", "BP", "ING", "KO"};


    public Waardepapier placeBuyOrder(Double geld, String bedrijf) {
        Waardepapier wp = null;
        Optional<Quote> aanbieding1 = koersen.stream().filter(q -> q.getSymbol().equalsIgnoreCase(bedrijf)).findFirst();
        if (aanbieding1.isPresent()) {
            Quote aanbieding = aanbieding1.get();

            Double koersBedrijf = aanbieding.getPrice();
            Double aantal = geld / koersBedrijf;

            wp = new Waardepapier(bedrijf, aantal, koersBedrijf);
        } else {
            
         //   wp = new Waardepapier();
            System.out.println("placeBuyOrder onbekend bedrijf "+ bedrijf + " resulteert in waardepapier " + wp);
        }
        return wp;
    }

    public List<Quote> updateKoersen() {
        String bedrijvenString = Arrays.toString(bedrijven).replaceAll("\\[|\\]|", "");

        String url = baseUrl + bedrijvenString;
        try {
            Quote[] quoteArray = restTemplate.getForObject(url, Quote[].class);

            koersen = Arrays.asList(quoteArray);
        } catch (Exception e) {
            System.out.println("exception in updateKoers geen restconnectie " + e.getMessage());
            System.out.println("Werkt met laatst bekende koersen");
        }

        return koersen;
    }
    public List<Quote> getKoersen() {

        
        return koersen;

    }

    public List<Quote> getKoersen(String[] symbolen) {

        List<String> symbolenLijst = Arrays.asList(symbolen);
        return koersen
                .stream()
                .filter(quote -> symbolenLijst.contains(quote.getSymbol()))
                .collect(Collectors.toList());

    }

    public Quote getKoers(String symbool) {
        Quote koers;
        Optional<Quote> koersoptie = koersen
                .stream()
                .filter(quote -> quote.getSymbol().equalsIgnoreCase(symbool))
                .findFirst();
        if (koersoptie.isPresent()) koers = koersoptie.get();
        else koers = new Quote();
        
        return koers;

    }

}
