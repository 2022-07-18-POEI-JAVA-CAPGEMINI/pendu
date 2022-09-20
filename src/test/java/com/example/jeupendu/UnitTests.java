package com.example.jeupendu;

import com.example.jeupendu.business.Partie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    @Test
    void testJouer() {
        char[] motADeviner = { 'm', 'o', 't', 'u', 's' };

        Partie partie = new Partie(motADeviner);

        partie.jouer('t');

        assertTrue(partie.getAvancement()[0] == Partie.SYMBOLE_NON_TROUVE);
        assertTrue(partie.getAvancement()[2] == 't');
    }

    @Test
    void testTentatives() {
        char[] motADeviner = { 'm', 'o', 't', 'u', 's' };

        Partie partie = new Partie(motADeviner);
        int tentatives = partie.getNombreTentativesRestantes();
        partie.jouer('t');
        assertTrue(partie.getNombreTentativesRestantes() == tentatives);

        partie.jouer('a');
        assertTrue(partie.getNombreTentativesRestantes() == tentatives-1);
    }

    @Test
    void testGagne() {
        char[] motADeviner = {'m', 'o', 't', 'u', 's'};

        Partie partie = new Partie(motADeviner);
        assertFalse(partie.isGagne());
        partie.jouer('m');
        assertFalse(partie.isGagne());
        partie.jouer('o');
        assertFalse(partie.isGagne());
        partie.jouer('t');
        assertFalse(partie.isGagne());
        partie.jouer('u');
        assertFalse(partie.isGagne());
        partie.jouer('s');
        assertTrue(partie.isGagne());
    }

}
