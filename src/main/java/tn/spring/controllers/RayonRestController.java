package tn.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.entities.Produit;
import tn.spring.entities.Rayon;
import tn.spring.services.IProduitService;
import tn.spring.services.IRayonService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rayons")
public class RayonRestController {

	@Autowired
	IRayonService rayonService ;
	@Autowired
	IProduitService produitService ;
	
	@GetMapping("/get-all")
	@ResponseBody
	public List<Rayon>getRayons(){
		List<Rayon> listRayons = rayonService.getAllRayons();
		return listRayons ;
	}
	
	@GetMapping("/get-one/{rayon-id}")

	@ResponseBody
	public Rayon getRayon(@PathVariable("rayon-id") Long rayonId) {
	return rayonService.getById(rayonId);
	}


	@PostMapping("/add")
	@ResponseBody
	public Rayon addRayon(@RequestBody Rayon r)
	{
	return rayonService.addRayon(r);
	}
	
	@DeleteMapping("/remove/{rayon-id}")
	@ResponseBody
	public void removeRayon(@PathVariable("rayon-id") Long rayonId) { 
	 rayonService.deleteRayon(rayonId);
	}

	@PutMapping("/modify")
	@ResponseBody
	public Rayon updateRayon(@RequestBody Rayon rayon) {
	return rayonService.updateRayon(rayon);
	}

	@GetMapping("/byRayon/{id}")
	@ResponseBody
	public List<Produit> getProduitsByRayon(@PathVariable("id")Long id){
		return produitService.findByRayon(id);
	}


}
