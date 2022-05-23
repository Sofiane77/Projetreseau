package com.Sofiane.Projetreseau.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Reagir extends InfosGeneral {
	@Column(name = "like_publication")
	private String like;
	@ManyToOne
	private Utilisateur utilisateur;
	@ManyToOne
	private Publication publication;

	public Reagir() {
	}

	public Reagir(String like) {
		this.like = like;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

}
