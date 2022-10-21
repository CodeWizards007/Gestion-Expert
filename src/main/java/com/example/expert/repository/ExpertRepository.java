package com.example.expert.repository;

import com.example.expert.entity.Expert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
	//Requete de recherche d'un candidat par son nom
	//http://localhost:8181/candidats/search/expertByNom?nom=Sarra
	@Query("select e from Expert e where e.nom like :nom")
	public Page<Expert> candidatByNom(@Param("nom") String n, Pageable pageable);

	Optional<Expert> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
