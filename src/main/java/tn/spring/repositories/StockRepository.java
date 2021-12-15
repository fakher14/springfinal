package tn.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

	@Query("SELECT st FROM Stock st WHERE st.qte<=st.qteMin")
	List<Stock> getStocksWithWarnings(); 
	
	
	
	
	
}
