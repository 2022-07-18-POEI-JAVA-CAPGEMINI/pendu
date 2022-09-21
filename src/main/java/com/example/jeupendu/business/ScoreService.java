package com.example.jeupendu.business;

import com.example.jeupendu.dao.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    JoueurRepository joueurRepository;

    public void saveFinDePartie(String pseudo, int pointsPartie) {

        Optional<Joueur> op = joueurRepository.findByPseudo(pseudo);
        if(op.isEmpty()){
            Joueur joueur = new Joueur();
            joueur.setPseudo(pseudo);
            joueur.setScore(pointsPartie);
            joueurRepository.save(joueur);
        } else {
            Joueur joueur = op.get();
            joueur.setScore(joueur.getScore() + pointsPartie);
            joueurRepository.save(joueur);
        }
    }
    public List<Joueur> getJoueurs() {
        return joueurRepository.findAllByOrderByScoreDesc();
    }
}
