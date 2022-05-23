package com.Sofiane.Projetreseau.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sofiane.Projetreseau.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	Optional<Utilisateur> findByNom(String nom);
}
