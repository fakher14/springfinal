package tn.spring.repositories;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.entities.*;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	List<Produit> findByLibelle(String libelle);
	
	@Query("select p from Produit p where stock.idStock = :id")
	List<Produit> findByStock(@Param("id")Long idStock);
	
	@Query("select p from Produit p where p.detailProduit.categorieProduit = :catP ")
	List<Produit> getByCategorie (@Param("catP")CategorieProduit categorieProduit);
	
	
	@Query("select df from DetailFacture df where (df.produit.idProduit= :id) and (df.facture.dateFacture between :date1 and :date2)")
	List<DetailFacture> getRevenueBrut(@Param("id")Long idProd , @Param("date1")Date d1 , @Param("date2")Date d2);

	@Query("select p from Produit p where rayon.idRayon = :id")
	List<Produit> findByRayon(@Param("id")Long idStock);

	@Query("select df from DetailFacture df where (df.produit.idProduit= :id) and(df.facture.client.categorieClient= :categorie)")
	List<DetailFacture> getListdetfacturess(@Param("id")Long idProd,@Param("categorie") CategorieClient c);
	@Query("select p from Produit p  join p.fournisseurs f where f.idFournisseur= :fid")
	List<Produit> getByFournisseur (@Param("fid")Long fid);
}
