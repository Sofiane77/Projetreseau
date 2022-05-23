package com.Sofiane.Projetreseau.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Sofiane.Projetreseau.entity.Utilisateur;
import com.Sofiane.Projetreseau.entity.enumeration.EtatProfil;
import com.Sofiane.Projetreseau.repository.UtilisateurRepository;
import com.Sofiane.Projetreseau.security.LoginModel;
import com.Sofiane.Projetreseau.security.SecurityProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
@RequestMapping("api/utilisateur")
public class UtilisateurController {
	private AuthenticationManager authenticationManager;
	private UtilisateurRepository utilisateurRepository;
	private PasswordEncoder passwordEncoder;

	

	public UtilisateurController(AuthenticationManager authenticationManager,
			UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.utilisateurRepository = utilisateurRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<?> seConnecter(@RequestBody LoginModel loginModel) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginModel.getNom(), loginModel.getPassword());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
		String jetonJwt = generateToken(utilisateur);
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jetonJwt).body("Authentification SUCCES");
	}

	private String generateToken(Utilisateur utilisateur) {
		Date dateExpiration = new Date(System.currentTimeMillis() + SecurityProperties.EXPIRE_IN);
		return JWT.create().withSubject(utilisateur.getNom()).withExpiresAt(dateExpiration).withIssuedAt(new Date())
				.withClaim("role", "ROLE_" + utilisateur.getRole().toString())
				.sign(Algorithm.HMAC512(SecurityProperties.SECRET));

	}

	@GetMapping
	public List<Utilisateur> listerUtilisateur() {
		return utilisateurRepository.findAll();
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
		Optional<Utilisateur> optionalEtudiant = utilisateurRepository.findById(id);
		if (optionalEtudiant.isPresent()) {
			Utilisateur utilisateurAModifier = optionalEtudiant.get();
			utilisateurAModifier.setAvatar(utilisateur.getAvatar());
			utilisateurAModifier.setNom(utilisateur.getNom());
			utilisateurAModifier.setPrenom(utilisateur.getPrenom());
			utilisateurAModifier.setPassword(utilisateur.getPassword());
			utilisateurAModifier.setSexe(utilisateur.getSexe());
			utilisateurAModifier.setEmail(utilisateur.getEmail());
			utilisateurAModifier.setTelephone(utilisateur.getTelephone());
			utilisateurAModifier.setDescription(utilisateur.getDescription());
			utilisateurAModifier.setRole(utilisateur.getRole());
			utilisateurRepository.save(utilisateurAModifier);
		}
		return "Update OK";
	}

	@PostMapping
	public ResponseEntity<?> ajouter(@RequestBody Utilisateur utilisateur) {
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		utilisateurRepository.save(utilisateur);
		return ResponseEntity.status(HttpStatus.CREATED).body("AJOUT OK");
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		utilisateurRepository.deleteById(id);
		return "Delete OK";
	}
	@PutMapping("/admin/desactiver_utilisateur/{id}")
	public String desactiverUtilisateur(@PathVariable Long id) {
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

		if (optionalUtilisateur.isPresent()) {
			Utilisateur utilisateurModifier = optionalUtilisateur.get();
			
			utilisateurModifier.setEtatProfil(EtatProfil.INACTIF); // desactive le user
			
			utilisateurRepository.save(utilisateurModifier);
		}
		return "desac ok";
	}
	
	@PutMapping("/admin/activer_utilisateur/{id}")
	public String activerUtilisateur(@PathVariable Long id) {
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

		if (optionalUtilisateur.isPresent()) {
			Utilisateur utilisateurModifier = optionalUtilisateur.get();
			
			utilisateurModifier.setEtatProfil(EtatProfil.ACTIF); // active le user
			
			utilisateurRepository.save(utilisateurModifier);
		}
		return "activation ok";
	}

	
}
