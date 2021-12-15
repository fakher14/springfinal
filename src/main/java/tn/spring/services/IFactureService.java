package tn.spring.services;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.models.auth.In;
import tn.spring.entities.*;

public interface IFactureService {

	public List<Facture> retrieveAllFactures();
	
	public void cancelFacture(Long id);
	
	public Facture retrieveFacture(Long id);
	
	public Facture updatFacture (Facture facture , Long idClient);
	
	public void deleteFacture (Long idFacture );
	
	
	
	/*
	 * advanced queries
	 */
	
	public float getChiffreDaffairesParCategorie(CategorieClient categorieClient,Date d1 , Date d2);
	
	public Facture addFacture(Facture facture, Long idClient);
	
	public void assignFactureToClient(Long  idFacture,Long idClient);
	
	public float revenuDuMagasin();

	public List<Facture> getFacturesByClient(Long idClient);

	public List<Float> getChiffreByMonth () throws ParseException;

	public Float chiffreByMonth(Long month) throws ParseException;

	public List<Facture> getFacturesByMonth(Long month) throws ParseException;

	Float getChiffreByProfession(Profession profession);

	Map<Profession,Float> getChiffreOfEveryProfession ();

	List<Produit> getAlarmingProducts (Long nbrDeJours, CategorieProduit categorieProduit);
}
