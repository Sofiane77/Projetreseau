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

import com.Sofiane.Projetreseau.entity.Groupe;
import com.Sofiane.Projetreseau.entity.Utilisateur;
import com.Sofiane.Projetreseau.entity.enumeration.EtatProfil;
import com.Sofiane.Projetreseau.repository.GroupeRepository;

@RestController
@RequestMapping("api/groupe")
public class GroupeController {
	private AuthenticationManager authenticationManager;
	private GroupeRepository groupeRepository;

	public GroupeController(AuthenticationManager authenticationManager, GroupeRepository groupeRepository) {
		this.authenticationManager = authenticationManager;
		this.groupeRepository = groupeRepository;
	}

	@GetMapping
	public List<Groupe> listerGroupes() {
		return groupeRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> ajouter(@RequestBody Groupe groupe) {
		groupeRepository.save(groupe);
		return ResponseEntity.status(HttpStatus.CREATED).body("AJOUT OK");
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		groupeRepository.deleteById(id);
		return "Delete OK";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @RequestBody Groupe groupe) {
		Optional<Groupe> optionalGroupe = groupeRepository.findById(id);
		if (optionalGroupe.isPresent()) {
			Groupe groupeAModifier = optionalGroupe.get();
			groupeAModifier.setLibelle(groupe.getLibelle());
			groupeAModifier.setDescription(groupe.getDescription());
			groupeRepository.save(groupeAModifier);
		}
		return "Update OK";
	}
	@PutMapping("/admin/desactiver_Groupe/{id}")
	public String desactiverUtilisateur(@PathVariable Long id) {
		Optional<Groupe> optionalGroupe = groupeRepository.findById(id);

		if (optionalGroupe.isPresent()) {
			Groupe GroupeModifier = optionalGroupe.get();
			
			GroupeModifier.setEtatProfil(EtatProfil.INACTIF); // desactive le user
			
			groupeRepository.save(GroupeModifier);
		}
		return "desac ok";
	}
	
	@PutMapping("/admin/activer_Groupe/{id}")
	public String activerUtilisateur(@PathVariable Long id) {
		Optional<Groupe> optionalGroupe = groupeRepository.findById(id);

		if (optionalGroupe.isPresent()) {
			Groupe GroupeModifier = optionalGroupe.get();
			
			GroupeModifier.setEtatProfil(EtatProfil.ACTIF); // active le user
			
			groupeRepository.save(GroupeModifier);
		}
		return "activation ok";
	}
	
	

}
