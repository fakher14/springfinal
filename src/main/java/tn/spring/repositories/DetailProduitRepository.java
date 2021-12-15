package tn.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tn.spring.entities.DetailProduit;

@Repository
public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long>{
	
	
}
