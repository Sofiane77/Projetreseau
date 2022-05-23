package com.Sofiane.Projetreseau.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Sofiane.Projetreseau.entity.Utilisateur;
import com.Sofiane.Projetreseau.entity.enumeration.EtatProfil;
import com.Sofiane.Projetreseau.entity.enumeration.Role;
import com.Sofiane.Projetreseau.entity.enumeration.Sexe;
import com.Sofiane.Projetreseau.repository.UtilisateurRepository;



@Service
public class DbInit  implements CommandLineRunner{

	private UtilisateurRepository utilisateurRepository;
	private PasswordEncoder passwordEncoder;	
	
	public DbInit(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
		this.utilisateurRepository = utilisateurRepository;
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public void run(String... args) throws Exception {
		
		
	}
	
}
