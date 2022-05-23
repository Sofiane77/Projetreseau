package com.Sofiane.Projetreseau.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.Sofiane.Projetreseau.entity.Publication;


public interface PublicationRepository extends JpaRepository<Publication, Long> {
	
}
