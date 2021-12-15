package tn.spring.services;

import java.util.List;

import tn.spring.entities.Rayon;

public interface IRayonService {

	public Rayon getById(Long idRayon);
	
	public List<Rayon> getAllRayons ();
	
	public Rayon addRayon (Rayon rayon);
	
	public void deleteRayon (Long id);
	
	public Rayon updateRayon (Rayon rayon);

}
