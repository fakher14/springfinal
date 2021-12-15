package tn.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.entities.Produit;
import tn.spring.entities.Rayon;

import java.util.List;

@Repository
public interface RayonRepository extends JpaRepository<Rayon, Long>{


}
