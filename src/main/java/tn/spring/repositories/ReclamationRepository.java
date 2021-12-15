package tn.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tn.spring.entities.Reclamation;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
    @Query("select r from Reclamation r where r.client.idClient = :id")
    public List<Reclamation>findReclamationsByClientId(@Param("id")Long idClient);
}
