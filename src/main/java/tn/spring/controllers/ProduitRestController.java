package tn.spring.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import tn.spring.entities.*;
import tn.spring.services.IProduitService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProduitRestController {

	@Autowired
	IProduitService produitService ;
	
	
	@GetMapping("/get-all")
	@ResponseBody
	public List<Produit> getProducts(){
		List<Produit> listproProduits = produitService.retrieveAllProduits();
		return listproProduits;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-one/{product-id}")
	@ResponseBody
	public Produit getProduct(@PathVariable("product-id")Long idProduit) {
		return produitService.findPorduitById(idProduit);
	}
	
	
	//http://localhost:8081/SpringMVC/product/add-product/{idRayon}/{idStock}
	@CrossOrigin(origins = "http://localhost:4200")

	@PostMapping(value = "/add/{idRayon}/{idStock}")
	@ResponseBody
	public Produit addProduit(@RequestBody Produit produit,@PathVariable(name = "idRayon") Long idRayon,@PathVariable(name = "idStock") Long idStock) throws IOException {

		
		return produitService.addProduit(produit, idRayon, idStock);
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/modify/{idRayon}/{idStock}")
	@ResponseBody
	public Produit updateProduit(@RequestBody Produit produit,@PathVariable("idRayon") Long idRayon,@PathVariable("idStock") Long idStock){

		return  produitService.updateProduit(produit, idRayon, idStock);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/remove/{product-id}")
	@ResponseBody
	public void deleteProduct(@PathVariable("product-id")Long id) {
		produitService.DeleteProduitById(id);
	}
	
	
	@GetMapping("/categorie/{cat}")
	@ResponseBody
	public List<Produit> getByCategorie(@PathVariable("cat")CategorieProduit categorieProduit){
		return produitService.findProduitByCategorie(categorieProduit);
	}
	
	@GetMapping("/libelle/{libelle}")
	@ResponseBody
	public List<Produit> getByLibelle(@PathVariable("libelle")String libelle){
		return produitService.findProduitsByLibelle(libelle);
	}
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/assignProdToStock/{idProduit}/{idStock}")
	@ResponseBody
	public void assignProduitToStock(@PathVariable("idProduit")Long idProd,@PathVariable("idStock")Long idStock) {
		produitService.assignProduitToStock(idProd, idStock);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/revenueBrut/{id-produit}/{date-debut}/{date-fin}")
	@ResponseBody
	public float getRevenueBrut(@PathVariable("id-produit")Long id,@PathVariable("date-debut") @DateTimeFormat(pattern = "yyyy-MM-dd")Date datestart,@PathVariable("date-fin")@DateTimeFormat(pattern = "yyyy-MM-dd")Date datefin) {
		return produitService.getRevenueBrut(id, datestart, datefin);
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/revenueBrutact/{id-produit}/{date-debut}")
	@ResponseBody
	public float getRevenueBrutact(@PathVariable("id-produit")Long id,@PathVariable("date-debut") @DateTimeFormat(pattern = "yyyy-MM-dd")Date datestart) {
		return produitService.getRevenueBrutact(id, datestart);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/Listporcen/{idp}")
	@ResponseBody
	public Map<CategorieClient, Float> porcentagedechaquecat(@PathVariable("idp") Long idp){
		return produitService.porcentagedechaquecat(idp);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/bestrevenueproduits")
	@ResponseBody
	public Map<String, String> MapBestsalesproduits() throws ParseException{
		return produitService.Listbestrevenuebruteproduitdechaquemois();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/Mostsalesproducts")
	@ResponseBody
	public Map<String, String> Mapmostsalesproduits() throws ParseException{
		return produitService.ListMostsaleproduitdechaquemois();
	}

}
