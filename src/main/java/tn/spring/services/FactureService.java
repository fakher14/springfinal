package tn.spring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.spring.entities.*;
import tn.spring.repositories.*;
@Service
public class FactureService implements IFactureService{

	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private DetailFactureRepository detailFactureRepository ;
	
	@Autowired
	private ProduitRepository produitRepository ;
	
	@Autowired
	private StockRepository stockRepository ;
	

	@Override
	public List<Facture> retrieveAllFactures() {
		return factureRepository.findAll();
	}

	@Override
	public void cancelFacture(Long id) {
	 Facture facture = factureRepository.getById(id);
	 facture.setActive(false);
	 factureRepository.save(facture);
	 
		
	}

	@Override
	public Facture retrieveFacture(Long id) {
		return factureRepository.findById(id).get();
	}

	

	@Override
	public Facture updatFacture(Facture facture,Long idClient) {
		Client client = clientRepository.findById(idClient).get();
		List<DetailFacture> detailFactures = detailFactureRepository.findDetailFacturesByFacture(facture);
		facture.setDetailFactures(detailFactures);
		facture.setClient(client);
		return factureRepository.save(facture);
	}

	@Transactional
	public void deleteFacture(Long idFacture) {
	Facture facture = factureRepository.findById(idFacture).get();
			List<DetailFacture> detailFactures = detailFactureRepository.findDetailFacturesByFacture(facture);
			detailFactureRepository.deleteAll(detailFactures);
		factureRepository.delete(facture);


	}

	@Override
	public void assignFactureToClient(Long idFacture,Long idClient) {
		Facture facture = factureRepository.findById(idFacture).get();
		facture.setClient(clientRepository.findById(idClient).get());
		factureRepository.save(facture);
		
	}
	
	
	
	@Override
	@Scheduled(cron = "* * * 1 1 *")
	public float revenuDuMagasin() {
		List<Facture> factures = factureRepository.findAll();
		float totalRevenue=0f;
		for (Facture facture : factures) {
			totalRevenue += facture.getMontantFacture() - facture.getMontantFacture()*facture.getMontantRemise();
		}
		System.out.println(totalRevenue);
		return totalRevenue;
	}

	@Override
	public List<Facture> getFacturesByClient(Long idClient) {
		return factureRepository.findByIdClient(idClient);
	}


	/* getting updated about the revenue */


	@Override
	public List<Float> getChiffreByMonth () throws ParseException {

		long months[] = {01,02,03,04,05,06,07,8,9,10,11,12};
		List<Float> chiffreByMonthList = new ArrayList<>() ;
		for (int i = 0; i < months.length; i++) {
			chiffreByMonthList.add(chiffreByMonth(months[i]));
		}
		return chiffreByMonthList ;
	}


	@Override
	public Float chiffreByMonth(Long month) throws ParseException {

		float chiffre = 0 ;
		List<Facture> factureThisMonth = getFacturesByMonth(month)	;
		for(Facture facture :factureThisMonth){
			chiffre += facture.getMontantFacture();
		}
		return chiffre;
	}
	@Override
	public List<Facture> getFacturesByMonth(Long month) throws ParseException {
		String d1 = "2021-"+month+"-01";
		String d2 = "2021-"+month+"-30";
		Date date1 = new  SimpleDateFormat("yyyy-MM-dd").parse(d1);
		Date date2 = new  SimpleDateFormat("yyyy-MM-dd").parse(d2);
	return factureRepository.findThisMonth(date1,date2);
	}

	@Override
	public Float getChiffreByProfession(Profession profession) {
		List<Facture> facturesParProfession = factureRepository.findFacturesByClientProfession(profession);
		float chiffre = 0 ;
		for (Facture facture : facturesParProfession){
			chiffre += facture.getMontantFacture();
		}
		return chiffre ;
	}

	@Override
	public Map<Profession,Float> getChiffreOfEveryProfession() {
		Map<Profession,Float>professionFloatMap = new HashMap<>();
		float chiffre = 0 ;
		for(Profession profession :Profession.values()){
			chiffre = getChiffreByProfession(profession);
			professionFloatMap.put(profession,chiffre);
		}
		return professionFloatMap ;
	}

	@Override
	public List<Produit> getAlarmingProducts(Long nbrDeJours,CategorieProduit categorieProduit) {
		List<Produit> alarmingProducts = new ArrayList<>();
		List<Produit> produitsAlimentaire = produitRepository.getByCategorie(categorieProduit);

		for(Produit produit : produitsAlimentaire){
			long timeDiff = produit.getDetailProduit().getDateDerniéreModification().getTime() - produit.getDetailProduit().getDateCreation().getTime() ;
			long difference_In_Days
					= (timeDiff
					/ (1000 * 60 * 60 * 24))
					% 365;

			if (difference_In_Days > nbrDeJours){
				alarmingProducts.add(produit);
			}
		}
		return alarmingProducts ;
	}

	/* getting updated about the revenue */

	@Transactional
	public Facture addFacture(Facture facture, Long idClient) {
		
		List<DetailFacture> detailFactures = facture.getDetailFactures();
		float prixTotaleDeFacture = 0;
		float montantRemiseFacture = 0 ;
		Produit p ;
		
		
		for (DetailFacture detailFacture : detailFactures) {
			
			int pourcentage = detailFacture.getPourcentageRemise();
			
			p= produitRepository.findById(detailFacture.getProduit().getIdProduit()).get();
			
			detailFacture.setProduit(p);
			
			float prixTotal = p.getPrixUnitaire()*detailFacture.getQte();
			
			float montant = prixTotal*(float)pourcentage/100;
		
			detailFacture.setPourcentageRemise(pourcentage);
			detailFacture.setPrixTotal(prixTotal);
			detailFacture.setMontantRemise(montant);
			
			prixTotaleDeFacture += prixTotal - montant ;
			montantRemiseFacture +=  montant;
			
			/*
			 * na99es fel qte te3 el produit li mawjoud fel stock
			 */
			
			Stock stock = p.getStock();

			while (stock.getQte()>0 && stock.getQte()>=detailFacture.getQte()){
				stock.setQte(stock.getQte() - detailFacture.getQte());
				stockRepository.save(stock);
				break;
			}

			
			
			/*
			 * 
			 */
			
			
		}
		facture.setMontantFacture(prixTotaleDeFacture);
		
		facture.setMontantRemise(montantRemiseFacture);
		
		facture.setClient(clientRepository.findById(idClient).get());
		
		facture.setDetailFactures(detailFactures);
		
		/* Date date =new Date(System.currentTimeMillis()); */
		
		facture.setDateFacture(facture.getDateFacture());
		
		
		factureRepository.save(facture);
		
		for (DetailFacture detailFacture : detailFactures) {
			detailFacture.setFacture(facture);
			detailFactureRepository.save(detailFacture);
		}
		
		
		return facture;
	}

	@Override
	public float getChiffreDaffairesParCategorie(CategorieClient categorieClient, Date d1, Date d2) {
		List<Facture> listFactures = factureRepository.findByCategorieAndDate(categorieClient, d1, d2);
		float montantTotale = 0 ;
		for (Facture facture : listFactures) {
			montantTotale += facture.getMontantFacture() ;
		}
		return montantTotale;
	}

	
	
	
	
	
	
	
	
	

}
