package tn.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entities.DetailProduit;
import tn.spring.repositories.DetailProduitRepository;
@Service
public class DetailProduitService implements IDetailProduitService{

	
	@Autowired
	private DetailProduitRepository detailProduitRepository ;
	
	@Override
	public DetailProduit addDetailProduit(DetailProduit detailProduit) {
		
		
		return detailProduitRepository.save(detailProduit);
	}
	
}
