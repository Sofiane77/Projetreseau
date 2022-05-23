package com.Sofiane.Projetreseau.entity;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;

@Entity
public class Commentaire extends InfosGeneral{

	private String texte;
	private String datePoster;
	@ManyToOne
	private Publication publication;
	@ManyToOne
	private Utilisateur utilisateur;

	public Commentaire() {
	}

	public Commentaire(String texte, String datePoster) {
		this.texte = texte;
		this.datePoster = datePoster;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getDatePoster() {
		return datePoster;
	}

	public void setDatePoster(String datePoster) {
		this.datePoster = datePoster;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
