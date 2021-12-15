package tn.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entities.Rayon;
import tn.spring.repositories.RayonRepository;
@Service
public class RayonService implements IRayonService{

	@Autowired
	RayonRepository rayonRepository ;

	@Override
	public Rayon getById(Long idRayon) {
		return rayonRepository.findById(idRayon).get();
	}

	@Override
	public List<Rayon> getAllRayons() {
		return rayonRepository.findAll();
	}

	@Override
	public Rayon addRayon(Rayon rayon) {
		return rayonRepository.save(rayon);
	}

	@Override
	public void deleteRayon(Long id) {
		rayonRepository.deleteById(id);
	}

	@Override
	public Rayon updateRayon(Rayon rayon) {
		return rayonRepository.save(rayon);
	}




}
