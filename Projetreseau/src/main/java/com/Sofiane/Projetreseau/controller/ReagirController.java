package com.Sofiane.Projetreseau.controller;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sofiane.Projetreseau.entity.Publication;
import com.Sofiane.Projetreseau.entity.Reagir;
import com.Sofiane.Projetreseau.entity.Utilisateur;
import com.Sofiane.Projetreseau.repository.PublicationRepository;
import com.Sofiane.Projetreseau.repository.ReagirRepository;
import com.Sofiane.Projetreseau.repository.UtilisateurRepository;

@RestController
@RequestMapping("api/reagir")
public class ReagirController {
	private AuthenticationManager authenticationManager;
	private ReagirRepository reagirRepository;
	private PublicationRepository publicationRepository;
	private UtilisateurRepository utilisateurRepository;

	public ReagirController(AuthenticationManager authenticationManager, ReagirRepository reagirRepository,
			PublicationRepository publicationRepository, UtilisateurRepository utilisateurRepository) {
		this.authenticationManager = authenticationManager;
		this.reagirRepository = reagirRepository;
		this.publicationRepository = publicationRepository;
		this.utilisateurRepository = utilisateurRepository;
	}

	@PostMapping("/reagir-publication/{idPublication}/{idUtilisateur}")
	public String ReagirPublication(@RequestBody Reagir reagir, @PathVariable Long idPublication,
			@PathVariable Long idUtilisateur) {

		Optional<Publication> optionalPublication = publicationRepository.findById(idPublication);
		if (optionalPublication.isPresent()) {
			Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(idUtilisateur);
			if (optionalUtilisateur.isPresent()) {
				Publication publication = optionalPublication.get();
				Utilisateur utilisateur = optionalUtilisateur.get();
				reagir.setPublication(publication);
				reagir.setUtilisateur(utilisateur);
				reagirRepository.save(reagir);
				return "Affecter OK";
			}

			return "user n'existe pas";
		}

		return "publication n'existe pas";
	}
}
