package com.example.jeupendu.controller;

import com.example.jeupendu.business.GenerateurDeMot;
import com.example.jeupendu.business.Partie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MvcController {

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
            return "gagne.html";
        } else {
            if(partie.getNombreTentativesRestantes() == 0){
                session.setAttribute("partie", null);
                return "perdu.html";
            } else {
                return accueil( session,  model);
            }
        }
    }

}
