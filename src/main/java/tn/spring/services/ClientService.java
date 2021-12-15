package tn.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.spring.entities.CategorieClient;
import tn.spring.entities.Client;
import tn.spring.entities.Facture;
import tn.spring.entities.Produit;
import tn.spring.repositories.ClientRepository;
import tn.spring.repositories.FactureRepository;




@Service
public class ClientService implements IClientService{

	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired 
	private FactureRepository factureRepository ;
	
	
	
	@Override
	public List<Client> retrieveAllClients() {
		
		return clientRepository.findAll() ;
		
	}

	@Override
	public Client addClient(Client c) {

		return clientRepository.save(c);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
		
	}

	@Override
	public Client updateClient(Client c) {
		return clientRepository.save(c);
	}
 
	@Override
	public Client retrieveClient(Long id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public List<Client> getClientsBetweenTwoDates(Date date1, Date date2) {

		return clientRepository.getClientsBetweenTwoDates(date1, date2);
	}
	
	
	
	@Override
	public List<Client> findClientsByCategorie(CategorieClient categorieClient) {
		
		return clientRepository.findByCategorieClient(categorieClient);
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * this is not optimised enough so i did another function like this in facture service 
	 */

	@Override
	public float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate) {
		List<Client>listDesClients = clientRepository.findByCategorieClient(categorieClient);
		
		float montantTotale = 0;
		
		for (Client client : listDesClients) {
			List<Facture>listDesFacturesParClient = factureRepository.findByIdClient(client.getIdClient(), startDate, endDate);
			float montantTotaleParClient = 0 ;
			for (Facture facture : listDesFacturesParClient) {
				montantTotaleParClient += facture.getMontantFacture() ;
			}
			montantTotale += montantTotaleParClient ;
		}
		
		
		return montantTotale;
	}
	/*
	 * this is not optimised enough so i did another function like this in facture service 
	 */
	
	/*
	 * Pannier pour un client [produit]
	 */

	@Override
	public List<Produit> addProductsToCart(Produit[] produits) {
		
		return null;
	}

	@Override
	public List<Client> changeCategory() {
		float montantTotale = 0;
		int count = 0;
		List<Client> clients = clientRepository.findAll();
		for (Client client : clients) {
			List<Facture> factures = factureRepository.findByIdClient(client.getIdClient());
			for (Facture f : factures) {
				montantTotale += f.getMontantFacture();
				count++;
			}
			if(!client.getCategorieClient().equals("Admin")) {
				if(montantTotale > 1000) {
					client.setCategorieClient(CategorieClient.Premuim);
				}
				else if(count > 5){
					client.setCategorieClient(CategorieClient.Fidele);
				}
				else
					client.setCategorieClient(CategorieClient.Ordinaire);
			}
			clientRepository.save(client);
		}

		return clients;
	}

	@Override
	public int getNbrFactureByClient() {
		List<Facture> factures = factureRepository.findAll();
		int count = 0;
		for (Facture facture : factures) {
			count++;
		}
		return count;
	}

	@Override
	public float nbrClientByCategorie(CategorieClient cat) {
		List<Client> clients = clientRepository.findAll();
		int count = 0;
		int countClients = 0;
		float percent = 0;
		for (Client client : clients) {
			if(client.getCategorieClient().equals(cat)) {
				count++;
			}
			countClients++;
		}
		percent = ((float)count / countClients)*100;

		return percent;
	}

	@Override
	public float revenuClient(Client client) {
		float montantTotale = 0;
		List<Facture> factures = factureRepository.findByIdClient(client.getIdClient());
		for (Facture facture : factures) {
			montantTotale += facture.getMontantFacture();
		}
		return montantTotale;
	}
	@Override
	public Client bestClients(){
		List<Client> clients = clientRepository.findAll();
		Client bestClient = clients.get(0);
		float total;
		for (Client client : clients) {
			if(bestClient != client) {
				total = this.revenuClient(client);
				if(total > this.revenuClient(bestClient)) {
					bestClient = client;
				}
			}
		}
		if(this.revenuClient(bestClient) != 0)
			return bestClient;
		else {
			return null;
		}
	}

	@Override
	public Client findOneById(Long idClient) {
		return clientRepository.findById(idClient).get();
	}

	
	/*
	 * Pannier pour un client [produit]
	 */
	
	
	
	
	
	
	
	
	
	
	

}
