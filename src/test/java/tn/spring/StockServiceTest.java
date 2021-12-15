package tn.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.spring.entities.Stock;
import tn.spring.services.IStockService;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

	@Autowired
	IStockService stockService ;
	
	@Test
	public void testAddStock() {
		Stock s = new Stock();
		s.setLibelleStock("testlibelle2" );
		s.setQte(17);
		s.setQteMin(65);
		Stock savedStock = stockService.addStock(s);
		assertNotNull(savedStock);
		
	}
	
}

