package com.Sofiane.Projetreseau.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Adherer extends InfosGeneral {
	
	private String dateAdhesion;
	@ManyToOne
	private Groupe groupe;
	@ManyToOne
	private Utilisateur utilisateur;

	public Adherer() {
	}

	public Adherer(String dateAdhesion) {
		this.dateAdhesion = dateAdhesion;
	}

	public String getDateAdhesion() {
		return dateAdhesion;
	}

	public void setDateAdhesion(String dateAdhesion) {
		this.dateAdhesion = dateAdhesion;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
