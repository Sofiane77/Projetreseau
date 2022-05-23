package com.Sofiane.Projetreseau.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Sofiane.Projetreseau.entity.Utilisateur;
import com.Sofiane.Projetreseau.repository.UtilisateurRepository;

@Service
public class DetailsUtilisateurService implements UserDetailsService {
	private UtilisateurRepository utilisateurRepository;

	public DetailsUtilisateurService(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByNom(nom);
		if(optionalUtilisateur.isPresent()) {
			return optionalUtilisateur.get();
		}
		throw new UsernameNotFoundException("username : " + nom + "n'est pas en bdd");
		
	}

}
