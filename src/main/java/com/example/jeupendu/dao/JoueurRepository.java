package com.example.jeupendu.dao;

import com.example.jeupendu.business.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long> {

    public Optional<Joueur> findByPseudo(String pseudo);
}