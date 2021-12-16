package tn.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entities.Fournisseur;
import tn.spring.entities.Produit;
import tn.spring.repositories.FournisseurRepository;
import tn.spring.repositories.ProduitRepository;
@Service
public class FournisseurService implements IFournisseurService {

	@Autowired
	FournisseurRepository fournisseurRepository;
	
	@Autowired
	ProduitRepository produitRepository ;
	
	
	@Override
	public Fournisseur addFournisseur(Fournisseur fournisseur) {
		return fournisseurRepository.save(fournisseur);
	}

	@Override
	public void deleteFournisseur(Long id) {
		fournisseurRepository.deleteById(id);
		
	}

	@Override
	public List<Fournisseur> getFournisseurs() {
		return fournisseurRepository.findAll();
	}

	@Override
	public Fournisseur getFournisseur(Long id) {
		
		return fournisseurRepository.findById(id).get();
	}

	@Override
	public Fournisseur updateFrournisseur(Fournisseur fournisseur) {
		return fournisseurRepository.save(fournisseur);
	}

	@Override
	public void assignFournisseurToProduit(Long idFournisseur, Long idProduit) {
		Produit produit = produitRepository.findById(idProduit).get();
		produit.getFournisseurs().add(fournisseurRepository.findById(idFournisseur).get());
		produitRepository.save(produit);
		
	}

	@Override
	public List<Fournisseur> getfournisseursbyid(Long idproduit) {
	Produit produit= produitRepository.findById(idproduit).orElse(null);
	  
		return produit.getFournisseurs();
	}
	 public List<Produit> getProduits(Long idf) {
			
			return produitRepository.getByFournisseur(idf);
		}

}
