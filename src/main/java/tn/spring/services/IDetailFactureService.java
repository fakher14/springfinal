package tn.spring.services;

import java.util.List;

import tn.spring.entities.DetailFacture;

public interface IDetailFactureService {

	public DetailFacture addDetailFacture(DetailFacture detailFacture);
	
	public DetailFacture updateDetailFacture(DetailFacture detailFacture);
	
	public DetailFacture getDetailFacture(Long idDetailFacture);
	
	public List<DetailFacture> getDetailFactures();
	
	public void deleteDetailFacture(Long idDetailFacture);
	
	public void assignDetailFactureToFacture(Long idDetailFacture,Long idFacture);
	
	public void assignProduitToDetailFacture(Long idProduit,Long idDetailFacture);
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
}
