package tn.spring.controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import tn.spring.entities.*;
import tn.spring.services.IFactureService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/factures")

public class FactureRestController {

	@Autowired
	IFactureService factureService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-all")
	@ResponseBody
	public List<Facture> getFactures(){
		return factureService.retrieveAllFactures();
	}
	

	@GetMapping("get-one/{fac-id}")
	@ResponseBody
	public Facture getFacture(@PathVariable("fac-id")Long factureId) {
		return factureService.retrieveFacture(factureId);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{fac-id}/assignToClient/{client-id}")
	@ResponseBody
	public void addFactureToClient(@PathVariable("fac-id")Long idFacture,@PathVariable("client-id")Long idClient) {
		 factureService.assignFactureToClient(idFacture, idClient);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/modify/{client-id}")
	@ResponseBody
	public Facture updateFacture(@RequestBody Facture facture,@PathVariable("client-id")Long idClient) {
		return factureService.updatFacture(facture,idClient);
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/remove/{fac-id}")
	@ResponseBody
	public void deleteFacture (@PathVariable("fac-id")Long id) {
		factureService.deleteFacture(id);
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/cancel/{fac-id}")
	@ResponseBody
	public void cancelFacture(@PathVariable("fac-id")Long id) {
		 factureService.cancelFacture(id);
	}
	
	@GetMapping("/getByClient/{id}")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Facture> getFactureByClient(@PathVariable("id")Long idClient){
		return factureService.getFacturesByClient(idClient);
	}
	@PostMapping("/add/{id}")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public Facture addFactureAv(@RequestBody Facture facture,@PathVariable("id")Long idClient) {
		return factureService.addFacture(facture, idClient);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getChiffreDaffaire/{catg}/{d1}/{d2}")
	@ResponseBody
	public float getChiffreAffaireParCategorieClient
			(@PathVariable("catg")CategorieClient categorieClient,@PathVariable("d1")
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date d1,
			@PathVariable("d2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d2) 
	{
		return factureService.getChiffreDaffairesParCategorie(categorieClient, d1, d2);
	}
	
	
	@GetMapping("/getDataByMonth")
	@ResponseBody
	public List<Float> getChiffreByMonth() throws ParseException {
		return factureService.getChiffreByMonth();
	}

	@GetMapping("/getChiffreByMonth/{month}")
	@ResponseBody
	public Float getChiffreByMonth(@PathVariable("month")long month) throws ParseException {
		return factureService.chiffreByMonth(month);
	}

	@GetMapping("/getFactureByMonth/{month}")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Facture> getThisMonth(@PathVariable("month")Long month) throws ParseException {
		return factureService.getFacturesByMonth(month);
	}

	@GetMapping("/getChiffreOfProfesions")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public Map<Profession,Float> getChiffreOfProffesions(){
		return factureService.getChiffreOfEveryProfession();
	}

	@GetMapping("/getChiffreParProf/{profession}")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public Float getChiffreByProff(@PathVariable("profession")Profession profession){
		return factureService.getChiffreByProfession(profession);
	}

	@GetMapping("/alarmingProducts/{nbrDeJours}/{cat}")
	@ResponseBody
	public List<Produit> getAlarmingProducts (@PathVariable("nbrDeJours")Long nbr, @PathVariable("cat")CategorieProduit categorieProduit){
		return factureService.getAlarmingProducts(nbr,categorieProduit);
	}
}
