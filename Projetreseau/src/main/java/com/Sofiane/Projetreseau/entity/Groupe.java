package com.Sofiane.Projetreseau.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.Sofiane.Projetreseau.entity.enumeration.EtatProfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Groupe extends InfosGeneral {

	private String libelle;
	private String description;
	@Enumerated(EnumType.STRING)
	private EtatProfil etatProfil;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private List<Adherer> adherers = new ArrayList<>();

	public Groupe() {
	}

	public Groupe(String libelle, String description) {
		this.libelle = libelle;
		this.description = description;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
