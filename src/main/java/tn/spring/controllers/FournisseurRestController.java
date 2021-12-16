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

import tn.spring.entities.Fournisseur;
import tn.spring.entities.Produit;
import tn.spring.repositories.ProduitRepository;
import tn.spring.services.IFournisseurService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fournisseurs")
public class FournisseurRestController {
	
	@Autowired
	IFournisseurService fournisseurService;
	@Autowired
	ProduitRepository produitRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-all")
	@ResponseBody
	public List<Fournisseur> getFournisseurs(){
		return fournisseurService.getFournisseurs();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-one/{f-id}")
	@ResponseBody
	public Fournisseur getFournisseur(@PathVariable("f-id")Long idf){
		return fournisseurService.getFournisseur(idf);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	@ResponseBody
	public Fournisseur addFournisseur(@RequestBody Fournisseur fournisseur) {
		return fournisseurService.addFournisseur(fournisseur);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/remove/{id}")
	@ResponseBody
	public void deleteFournisseur(@PathVariable("id")Long id) {
		fournisseurService.deleteFournisseur(id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/modify")
	@ResponseBody
	public Fournisseur updateFournisseur(@RequestBody Fournisseur fournisseur) {
		return fournisseurService.updateFrournisseur(fournisseur);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{f-id}/assign/{p-id}")
	@ResponseBody
	public void assignFournisseurToProduit(@PathVariable("f-id")Long fournisseurId,@PathVariable("p-id")Long produitId) {
		 fournisseurService.assignFournisseurToProduit(fournisseurId, produitId);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-fourni-p/{p-id}")
	@ResponseBody
	public List<Fournisseur> getFournisseursbyproduit(@PathVariable("p-id")Long idp){
		return fournisseurService.getfournisseursbyid(idp);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-products/{id}")
	public List<Produit> getProducts(@PathVariable("id")Long fournisseurId) {
		return produitRepository.getByFournisseur(fournisseurId);
		 
	}
	
	
}
