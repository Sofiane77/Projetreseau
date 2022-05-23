package com.Sofiane.Projetreseau.entity;

import javax.persistence.Entity;


@Entity
public class PublicationMedia extends Publication {

	private String referenceMedia;

	public PublicationMedia() {
	}

	public PublicationMedia(String referenceMedia) {
		this.referenceMedia = referenceMedia;
	}

	public String getReferenceMedia() {
		return referenceMedia;
	}

	public void setReferenceMedia(String referenceMedia) {
		this.referenceMedia = referenceMedia;
	}

}
