package com.Sofiane.Projetreseau.controller;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sofiane.Projetreseau.entity.Commentaire;
import com.Sofiane.Projetreseau.entity.Publication;
import com.Sofiane.Projetreseau.entity.Reagir;
import com.Sofiane.Projetreseau.entity.Utilisateur;
import com.Sofiane.Projetreseau.repository.CommentaireRepository;
import com.Sofiane.Projetreseau.repository.PublicationRepository;
import com.Sofiane.Projetreseau.repository.UtilisateurRepository;

@RestController
@RequestMapping("api/commentaire")
public class CommentaireController {
	private AuthenticationManager authenticationManager;
	private CommentaireRepository commentaireRepository;
	private PublicationRepository publicationRepository;
	private UtilisateurRepository utilisateurRepository;

	public CommentaireController(AuthenticationManager authenticationManager,
			CommentaireRepository commentaireRepository, PublicationRepository publicationRepository,
			UtilisateurRepository utilisateurRepository) {
		this.authenticationManager = authenticationManager;
		this.commentaireRepository = commentaireRepository;
		this.publicationRepository = publicationRepository;
		this.utilisateurRepository = utilisateurRepository;
	}

	@PostMapping("/commentaire-publication/{idPublication}/{idUtilisateur}")
	public String CommenterPublication(@RequestBody Commentaire commentaire, @PathVariable Long idPublication,
			@PathVariable Long idUtilisateur) {

		Optional<Publication> optionalPublication = publicationRepository.findById(idPublication);
		if (optionalPublication.isPresent()) {
			Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(idUtilisateur);
			if (optionalUtilisateur.isPresent()) {
				Publication publication = optionalPublication.get();
				Utilisateur utilisateur = optionalUtilisateur.get();
				commentaire.setPublication(publication);
				commentaire.setUtilisateur(utilisateur);
				commentaireRepository.save(commentaire);
				return "commentaire  OK";
			}

			return "user existe pas";
		}

		return "publication n'existe pas";
	}
}
