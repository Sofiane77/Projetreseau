package com.Sofiane.Projetreseau.entity;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;

@Entity
public class EnvoyerMessage extends InfosGeneral{

	private Long idMessage;
	private String contenu;
	private String dateEnvoi;
	private String lu;
	@ManyToOne
	private Utilisateur utilisateur;
	@ManyToOne
	private Utilisateur utilisateurRecu;

	public EnvoyerMessage() {
	}

	public EnvoyerMessage(Long idMessage, String contenu, String dateEnvoi, String lu) {
		this.idMessage = idMessage;
		this.contenu = contenu;
		this.dateEnvoi = dateEnvoi;
		this.lu = lu;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(String dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
