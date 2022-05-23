package com.Sofiane.Projetreseau.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sofiane.Projetreseau.entity.Publication;

import com.Sofiane.Projetreseau.repository.PublicationRepository;

@RestController
@RequestMapping("api/publication")
public class PublicationController {
	private AuthenticationManager authenticationManager;
	private PublicationRepository publicationRepository;

	public PublicationController(AuthenticationManager authenticationManager,
			PublicationRepository publicationRepository) {
		this.authenticationManager = authenticationManager;
		this.publicationRepository = publicationRepository;
	}

	@GetMapping
	public List<Publication> listerPublications() {
		return publicationRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> ajouter(@RequestBody Publication publication) {
		publicationRepository.save(publication);
		return ResponseEntity.status(HttpStatus.CREATED).body("AJOUT OK");
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		publicationRepository.deleteById(id);
		return "Delete OK";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @RequestBody Publication publication) {
		Optional<Publication> optionalPublication = publicationRepository.findById(id);
		if (optionalPublication.isPresent()) {
			Publication publicationAModifier = optionalPublication.get();
			publicationAModifier.setVisibilite(publication.getVisibilite());
			publicationAModifier.setText(publication.getText());

			publicationRepository.save(publicationAModifier);
		}
		return "Update OK";
	}
}
