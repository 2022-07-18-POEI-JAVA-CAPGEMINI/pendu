package com.example.jeupendu.controller;

import com.example.jeupendu.business.GenerateurDeMot;
import com.example.jeupendu.business.Joueur;
import com.example.jeupendu.business.Partie;
import com.example.jeupendu.business.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MvcController {

    @Autowired
    ScoreService scoreService;

    @GetMapping("pendu")
    public String accueil(HttpSession session, Model model){

        Partie partie = (Partie)session.getAttribute("partie");
        if(partie == null){

            partie = new Partie(GenerateurDeMot.randomMot());
            session.setAttribute("partie", partie);
        }

        model.addAttribute("nombreTentativesRestantes", partie.getNombreTentativesRestantes());
        model.addAttribute("avancement", String.valueOf(partie.getAvancement()));
        model.addAttribute("motADeviner", String.valueOf(partie.getMotADeviner()));
        return "pendu.html";
    }

    @PostMapping("pendu")
    public String play(@RequestParam(defaultValue = ".") char proposition, HttpSession session, Model model){

        Partie partie = (Partie)session.getAttribute("partie");
        partie.jouer(proposition);

        if(partie.isGagne()){
            session.setAttribute("partie", null);
            String pseudo = (String)session.getAttribute("pseudo");
            scoreService.saveFinDePartie(pseudo, 1);
            return "gagne.html";
        } else {
            if(partie.getNombreTentativesRestantes() == 0){
                session.setAttribute("partie", null);
                String pseudo = (String)session.getAttribute("pseudo");
                scoreService.saveFinDePartie(pseudo, -1);
                return "perdu.html";
            } else {
                return accueil( session,  model);
            }
        }
    }

    @GetMapping("start")
    public String start(){
        return "start.html";
    }
    @PostMapping("start")
    public String start(String pseudo, HttpSession session, Model model){

        session.setAttribute("pseudo", pseudo);

        return accueil(session, model);
    }

    @GetMapping("classement")
    public String classement(Model model) {

        List<Joueur> joueurs = scoreService.getJoueurs();

        model.addAttribute("joueurs", joueurs);
        return "classement.html";
    }
}
