package tn.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entities.DetailFacture;
import tn.spring.entities.Facture;
import tn.spring.entities.Produit;
import tn.spring.repositories.DetailFactureRepository;
import tn.spring.repositories.FactureRepository;
import tn.spring.repositories.ProduitRepository;

@Service
public class DetailFactureService implements IDetailFactureService{

	@Autowired
	DetailFactureRepository detailFactureRepository;
	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ProduitRepository produitRepository;
	
	
	
	@Override
	public DetailFacture addDetailFacture(DetailFacture detailFacture) {
		return detailFactureRepository.save(detailFacture);
	}

	@Override
	public DetailFacture updateDetailFacture(DetailFacture detailFacture) {
		return detailFactureRepository.save(detailFacture);
	}

	@Override
	public DetailFacture getDetailFacture(Long idDetailFacture) {
		return detailFactureRepository.findById(idDetailFacture).get();
	}

	@Override
	public List<DetailFacture> getDetailFactures() {
		return detailFactureRepository.findAll();
	}

	@Override
	public void deleteDetailFacture(Long idDetailFacture) {
		detailFactureRepository.deleteById(idDetailFacture);
	}
	//insert detail then update ??
	@Override
	public void assignDetailFactureToFacture(Long idDetailFacture, Long idFacture) {
		DetailFacture detailFacture =detailFactureRepository.findById(idDetailFacture).get();
		Facture facture =factureRepository.findById(idFacture).get();
		detailFacture.setFacture(facture);
		detailFactureRepository.save(detailFacture);
	}
	//Put mapping
	@Override
	public void assignProduitToDetailFacture(Long idProduit, Long idDetailFacture) {
		DetailFacture detailFacture =detailFactureRepository.findById(idDetailFacture).get();
		Produit produit =produitRepository.findById(idProduit).get();
		detailFacture.setProduit(produit);
		detailFactureRepository.save(detailFacture);
	}
	/*
	 * 
	 * 
	 * 
	 */

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
