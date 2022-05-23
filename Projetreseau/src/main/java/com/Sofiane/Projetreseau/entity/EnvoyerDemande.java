package com.Sofiane.Projetreseau.entity;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;

import com.Sofiane.Projetreseau.entity.enumeration.Etat;

@Entity
public class EnvoyerDemande extends InfosGeneral{
	
	private Etat etat;
	private Date dateEnvoi;
	@ManyToOne
	private Utilisateur utilisateur;
	@ManyToOne
	private Utilisateur utilisateurDemandeRecu;

	public EnvoyerDemande() {
	}

	public EnvoyerDemande(Etat etat, Date dateEnvoi) {
		this.etat = etat;
		this.dateEnvoi = dateEnvoi;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
