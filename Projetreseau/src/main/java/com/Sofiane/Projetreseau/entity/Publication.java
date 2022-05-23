package com.Sofiane.Projetreseau.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Sofiane.Projetreseau.entity.enumeration.Visibilite;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Publication extends InfosGeneral {

	private Visibilite visibilite;
	private String text;
	@JsonIgnore
	@OneToMany(mappedBy = "publication")
	private List<Reagir> reagir = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "publication")
	private List<Commentaire> commentaires = new ArrayList<>();
	@ManyToOne
	private Utilisateur utilisateur;

	public Publication() {
	}

	public Publication(Visibilite visibilite, String text) {
		this.visibilite = visibilite;
		this.text = text;
		
	}

	public Visibilite getVisibilite() {
		return visibilite;
	}

	public void setVisibilite(Visibilite visibilite) {
		this.visibilite = visibilite;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
