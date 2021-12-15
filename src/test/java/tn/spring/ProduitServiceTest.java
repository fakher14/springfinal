package tn.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.spring.entities.DetailProduit;
import tn.spring.entities.Produit;
import tn.spring.services.IDetailProduitService;
import tn.spring.services.IProduitService;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceTest {

	
	@Test
	public void testAddProduit () {
		Produit produit = new Produit();
		produit.setCode("helloTest");
		produit.setLibelle("testLibelle");
		produit.setPrixUnitaire(12f);
		Date date = new Date (System.currentTimeMillis());
		produit.getDetailProduit().setDateCreation(date);
		produit.getDetailProduit().setDateDerniereModification(date);

		
		assertNotNull(produit);
		
		System.out.println(date);
	}

}
