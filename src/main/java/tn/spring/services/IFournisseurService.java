package tn.spring.services;

import java.util.List;

import tn.spring.entities.Fournisseur;

public interface IFournisseurService {

	public Fournisseur addFournisseur(Fournisseur fournisseur);
	
	public void deleteFournisseur (Long id);
	
	public List<Fournisseur> getFournisseurs();
	
	public Fournisseur getFournisseur(Long id);
	
	public Fournisseur updateFrournisseur(Fournisseur fournisseur);
	
	public void assignFournisseurToProduit(Long idFournisseur,Long idProduit);
	public List<Fournisseur> getfournisseursbyid(Long idproduit);
}
