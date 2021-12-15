package tn.spring.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import tn.spring.entities.CategorieClient;
import tn.spring.entities.CategorieProduit;
import tn.spring.entities.Produit;
import tn.spring.entities.Stock;

public interface IProduitService {
	
	public List<Produit> retrieveAllProduits();
	
	public Produit addProduit(Produit p, Long idRayon , Long idStock );
	
	public void DeleteProduitById(Long idProduit);
	
	public Produit updateProduit(Produit p,Long idRayon , Long idStock);
	
	public Produit findPorduitById(Long id);
	
	public List<Produit> findProduitsByStockid(Long idStock);
	
	public float getRevenueBrutact(Long idP, Date d1);
	
	public void assignProduitToStock(Long idProduit,Long idStock);
	
	public List<Produit> findProduitsByLibelle (String libelle);

	public float getRevenueBrut(Long idP , Date d1 , Date d2);
	
	public List<Produit> findProduitByCategorie(CategorieProduit categorieProduit);

	public List<Produit> findByRayon(Long idRayon);
	public Map<CategorieClient, Float> porcentagedechaquecat(Long idp);
	public float getrevenuebrutepatmois(Long idp,Long month) throws ParseException;
	public Produit bestrevenuebrutparmois(Long month) throws ParseException;
	public Map<String, String> Listbestrevenuebruteproduitdechaquemois() throws ParseException;
	public int qtesaleProduitpamois(Long month,Long idpLong) throws ParseException;
	public Produit mostsaleProduitparmois(Long monthLong) throws ParseException;
	public Map<String, String> ListMostsaleproduitdechaquemois() throws ParseException ;
}
