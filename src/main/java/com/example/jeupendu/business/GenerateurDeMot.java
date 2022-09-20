package com.example.jeupendu.business;

import java.util.Random;

public class GenerateurDeMot {

    private static String[] mots = { "bonjour", "maison", "telephone"};

    public static char[] randomMot(){
        Random random = new Random();
        String mot = mots[random.nextInt(mots.length)];
        return mot.toCharArray();
    }
}
