package tn.spring.controllers;

import java.util.Date;
import java.util.List;

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

import tn.spring.entities.CategorieClient;
import tn.spring.entities.Client;
import tn.spring.services.IClientService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients")
public class ClientRestController {

	@Autowired
	IClientService clientService ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-all")
	@ResponseBody
	public List<Client>getClients(){
		List<Client> listClients = clientService.retrieveAllClients();
		return listClients ;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-one/{client-id}")
	@ResponseBody
	public Client retrieveClient(@PathVariable("client-id") Long clientId) {
	return clientService.retrieveClient(clientId);
	}

	
	// http://localhost:8081/SpringMVC/client/add-client
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	@ResponseBody
	public Client addClient(@RequestBody Client c)
	{
	Client client = clientService.addClient(c);
	return client;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/remove/{client-id}")
	@ResponseBody
	public void removeClient(@PathVariable("client-id") Long clientId) { 
	 clientService.deleteClient(clientId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/modify")
	@ResponseBody
	public Client modifyClient(@RequestBody Client client) {
	return clientService.updateClient(client);
	}
	
	//get clients between two dates
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/date1/{date1}/date2/{date2}")
	@ResponseBody
	public List<Client> getBetweenTwoDates
			(@PathVariable("date1")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d1,
			@PathVariable("date2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d2){
		return clientService.getClientsBetweenTwoDates(d1, d2);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("categories/{cat}")
	@ResponseBody
	public List<Client> getByCategorie(@PathVariable("cat")CategorieClient categorieClient){
		return clientService.findClientsByCategorie(categorieClient);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("client/{id}")
	@ResponseBody
	public Client findbyId(@PathVariable("id")Long idClient){
		return clientService.findOneById(idClient);
	}
	
	// get chiffre d'affaire par categorie des clients et entre les deux dates
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getTotal/{catg}/{d1}/{d2}")
	@ResponseBody
	public float getChiffreAffaireParCategorieClient
			(@PathVariable("catg")CategorieClient categorieClient,@PathVariable("d1")
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date d1,
			@PathVariable("d2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d2) 
	{
		return clientService.getChiffreAffaireParCategorieClient(categorieClient, d1, d2) ;
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/changeCat")
	@ResponseBody
	public List<Client> changeCategorie(){
		return clientService.changeCategory();
	}

	//best clients
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/best")
	@ResponseBody
	public Client best(){
		return clientService.bestClients();
	}


	//Nombre des clients par categorie
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/nombre/{cat}")
	@ResponseBody
	public float nbrClients(@PathVariable("cat")CategorieClient categorieClient) {
		return clientService.nbrClientByCategorie(categorieClient);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getNbrFactureByClient")
	public int getNbrFactureByClient() {
		return clientService.getNbrFactureByClient();
	}



	
}
