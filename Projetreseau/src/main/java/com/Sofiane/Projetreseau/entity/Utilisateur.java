package com.Sofiane.Projetreseau.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Sofiane.Projetreseau.entity.enumeration.EtatProfil;
import com.Sofiane.Projetreseau.entity.enumeration.Role;
import com.Sofiane.Projetreseau.entity.enumeration.Sexe;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utilisateur extends InfosGeneral implements UserDetails {

	@Column(unique = true)
	@JsonIgnore
	private String password;
	@Column(unique = true, nullable = false)
	private String nom;
	@Column(unique = true, nullable = false)
	private String prenom;
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	@Column(unique = true, nullable = false)
	private String email;
	private String telephone;
	private String avatar;
	private String description;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private EtatProfil etatProfil;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private List<Reagir> reagir = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private List<Commentaire> commentaires = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private List<Publication> publications = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private List<EnvoyerMessage> envoyerMessages = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateurRecu")
	private List<EnvoyerMessage> messageRecu = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private List<EnvoyerDemande> envoyerDemandes = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateurDemandeRecu")
	private List<EnvoyerDemande> demandeRecu = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private List<Adherer> adherers = new ArrayList<>();

	public Utilisateur() {
	}

	public Utilisateur(String password, String nom, String prenom, Sexe sexe, String email, String telephone,
			String avatar, String description, Role role, EtatProfil etatProfil) {
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.email = email;
		this.telephone = telephone;
		this.avatar = avatar;
		this.description = description;
		this.role = role;
		this.etatProfil = etatProfil;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Reagir> getReagir() {
		return reagir;
	}

	public void setReagir(List<Reagir> reagir) {
		this.reagir = reagir;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public List<EnvoyerMessage> getEnvoyerMessages() {
		return envoyerMessages;
	}

	public void setEnvoyerMessages(List<EnvoyerMessage> envoyerMessages) {
		this.envoyerMessages = envoyerMessages;
	}

	public List<EnvoyerDemande> getEnvoyerDemandes() {
		return envoyerDemandes;
	}

	public void setEnvoyerDemandes(List<EnvoyerDemande> envoyerDemandes) {
		this.envoyerDemandes = envoyerDemandes;
	}

	public List<Adherer> getAdherers() {
		return adherers;
	}

	public void setAdherers(List<Adherer> adherers) {
		this.adherers = adherers;
	}

	public EtatProfil getEtatProfil() {
		return etatProfil;
	}

	public void setEtatProfil(EtatProfil etatProfil) {
		this.etatProfil = etatProfil;
	}

	public List<EnvoyerMessage> getMessageRecu() {
		return messageRecu;
	}

	public void setMessageRecu(List<EnvoyerMessage> messageRecu) {
		this.messageRecu = messageRecu;
	}

	public List<EnvoyerDemande> getDemandeRecu() {
		return demandeRecu;
	}

	public void setDemandeRecu(List<EnvoyerDemande> demandeRecu) {
		this.demandeRecu = demandeRecu;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.toString());
		return Collections.singletonList(grantedAuthority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (this.etatProfil.equals(EtatProfil.ACTIF)) {
			return true;
		}
		return false;
	}

}
