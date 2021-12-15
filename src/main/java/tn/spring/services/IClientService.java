package tn.spring.services;
import java.util.Date;
import java.util.List;

import tn.spring.entities.CategorieClient;
import tn.spring.entities.Client;
import tn.spring.entities.Produit;

public interface IClientService {

	List<Client> retrieveAllClients();

	Client addClient(Client c);

	void deleteClient(Long id);

	Client updateClient(Client c);

	Client retrieveClient(Long id);

	List<Client> findClientsByCategorie(CategorieClient categorieClient);

	Client findOneById(Long idClient);

	/*
	 * advanced queries
	 */

	List<Client> getClientsBetweenTwoDates (Date date1 , Date date2);

	float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate);

	List<Produit> addProductsToCart(Produit[] produits);

	List<Client> changeCategory();

	int getNbrFactureByClient();

	float nbrClientByCategorie(CategorieClient cat);

	float revenuClient(Client client);

	Client bestClients();


}
