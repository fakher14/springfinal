package tn.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.entities.DetailFacture;
import tn.spring.entities.Facture;

import java.util.List;

@Repository
public interface DetailFactureRepository extends JpaRepository<DetailFacture, Long>{

    @Query("select df from DetailFacture df where (df.produit.idProduit= :id) ")
    List<DetailFacture> getListdetfacturebyidp(@Param("id")Long idProd );

    List<DetailFacture> findDetailFacturesByFacture(Facture facture);
}
