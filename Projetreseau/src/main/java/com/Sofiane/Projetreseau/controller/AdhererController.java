package com.Sofiane.Projetreseau.controller;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sofiane.Projetreseau.entity.Adherer;
import com.Sofiane.Projetreseau.entity.Groupe;

import com.Sofiane.Projetreseau.entity.Utilisateur;
import com.Sofiane.Projetreseau.repository.AdhererRepository;
import com.Sofiane.Projetreseau.repository.GroupeRepository;
import com.Sofiane.Projetreseau.repository.UtilisateurRepository;

@RestController
@RequestMapping("api/adherer")
public class AdhererController {
	private AuthenticationManager authenticationManager;
	private AdhererRepository adhererRepository;
	private UtilisateurRepository utilisateurRepository;
	private GroupeRepository groupeRepository;

	public AdhererController(AuthenticationManager authenticationManager, AdhererRepository adhererRepository,
			UtilisateurRepository utilisateurRepository, GroupeRepository groupeRepository) {
		this.authenticationManager = authenticationManager;
		this.adhererRepository = adhererRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.groupeRepository = groupeRepository;
	}

	@PostMapping("/adherer-groupe/{idGroupe}/{idUtilisateur}")
	public String AdhererGroupe(@RequestBody Adherer adherer, @PathVariable Long idGroupe,
			@PathVariable Long idUtilisateur) {
		Optional<Groupe> optionalGroupe = groupeRepository.findById(idGroupe);
		if (optionalGroupe.isPresent()) {
			Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(idUtilisateur);
			if (optionalUtilisateur.isPresent()) {
				Groupe groupe = optionalGroupe.get();
				Utilisateur utilisateur = optionalUtilisateur.get();
				adherer.setGroupe(groupe);
				adherer.setUtilisateur(utilisateur);
				adhererRepository.save(adherer);
				return "adheration ok";
			}
			return "user n'existe pas";
		}
		return "groupe n'existe pas";
	}

}
