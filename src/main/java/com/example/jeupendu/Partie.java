package com.example.jeupendu;

public class Partie {

    private char[] motADeviner;
    private int nombreTentativesRestantes;

    private char[] avancement;

    public Partie(char[] motADeviner) {
        this.motADeviner = motADeviner;
        this.nombreTentativesRestantes = 9;
        avancement = new char[motADeviner.length];
        for (int i = 0; i < avancement.length; i++) {
            avancement[i] = '_';
        }
    }

    public void jouer(char proposition){
        boolean devine = false;
        for (int i = 0; i < motADeviner.length; i++) {
            if(motADeviner[i] == proposition) {
                avancement[i] = proposition;
                devine = true;
            }
        }
        if(!devine){
            nombreTentativesRestantes--;
        }
    }

    public boolean isGagne() {
        for (int i = 0; i < avancement.length; i++) {
            if(avancement[i] == '_')
                return false;
        }
        return true;
    }
    public char[] getAvancement() {
        return avancement;
    }

    public int getNombreTentativesRestantes() {
        return nombreTentativesRestantes;
    }
}
