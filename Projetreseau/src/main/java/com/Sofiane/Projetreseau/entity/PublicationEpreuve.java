package com.Sofiane.Projetreseau.entity;

import javax.persistence.Column;
import javax.persistence.Entity;


import com.Sofiane.Projetreseau.entity.enumeration.CategorieEpreuve;

@Entity
public class PublicationEpreuve extends Publication {

	@Column(unique = true)
	private String lien;
	private String datePassage;
	private String classe;
	private CategorieEpreuve categorieEpreuve;

	public PublicationEpreuve() {
	}

	public PublicationEpreuve(String lien, String datePassage, String classe, CategorieEpreuve categorieEpreuve) {
		this.lien = lien;
		this.datePassage = datePassage;
		this.classe = classe;
		this.categorieEpreuve = categorieEpreuve;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getDatePassage() {
		return datePassage;
	}

	public void setDatePassage(String datePassage) {
		this.datePassage = datePassage;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public CategorieEpreuve getCategorieEpreuve() {
		return categorieEpreuve;
	}

	public void setCategorieEpreuve(CategorieEpreuve categorieEpreuve) {
		this.categorieEpreuve = categorieEpreuve;
	}

}
