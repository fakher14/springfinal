package tn.spring.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.entities.CategorieClient;
import tn.spring.entities.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>  {

	Client findByDateNaissanceGreaterThan(Date dateN);
	
	@Query("select c from Client c where c.dateNaissance between :date1 AND :date2")
	List<Client> getClientsBetweenTwoDates(@Param("date1")Date date1 ,@Param("date2")Date date2);
	
	List<Client> findByCategorieClient(CategorieClient categorieClient);
	
	
	
}
