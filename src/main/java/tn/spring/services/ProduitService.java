package tn.spring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.hibernate.boot.model.naming.ImplicitNameSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.description.type.RecordComponentDescription.ForLoadedRecordComponent;
import tn.spring.entities.*;
import tn.spring.repositories.*;

@Service
public class ProduitService implements IProduitService {

	

	@Autowired
	ProduitRepository produitRepository ;
	@Autowired
	StockRepository stockRepository ;
	@Autowired
	RayonRepository rayonRepository ;
	@Autowired
	DetailProduitRepository detailProduitRepository;
	@Autowired
	FactureRepository factureRepository ;
	
	@Autowired
	DetailFactureRepository detailFactureRepository ;


	@Override
	public List<Produit> retrieveAllProduits() {
		return produitRepository.findAll();
	}
	
	@Override
	public Produit findPorduitById(Long id) {
		return produitRepository.findById(id).get();
	}
	
	@Override
	public List<Produit> findProduitsByLibelle(String libelle) {
		
		return produitRepository.findByLibelle(libelle);
	}
	
	@Override
	public Produit addProduit(Produit p, Long idRayon, Long idStock ) {
		
	
		p.setRayon(rayonRepository.findById(idRayon).get());
		p.setStock(stockRepository.findById(idStock).get());



		Date date =new Date(System.currentTimeMillis());
		DetailProduit dProduit = new DetailProduit(date,date);
		dProduit.setCategorieProduit(p.getDetailProduit().getCategorieProduit());
		detailProduitRepository.save(dProduit);
		
		dProduit.setProduit(p);

		p.setQuantite(1);
		p.setDetailProduit(dProduit);
		
		return produitRepository.save(p);
		 
		
	}



	@Override
	public Produit updateProduit(Produit prod, Long idRayon, Long idStock ) {
		//to retrieve the object u must get it with the findById 
		Produit p = produitRepository.findById(prod.getIdProduit()).get();
		p.setRayon(rayonRepository.findById(idRayon).get());
		p.setStock(stockRepository.findById(idStock).get());
		p.setCode(prod.getCode());
		p.setLibelle(prod.getLibelle());
		p.setPrixUnitaire(prod.getPrixUnitaire());
		
		
		DetailProduit dProduit = detailProduitRepository.findById(p.getDetailProduit().getIdDetailProduit()).get();
		
		
		Date date =new Date(System.currentTimeMillis());
		dProduit.setDateDerniereModification(date);
		dProduit.setCategorieProduit(prod.getDetailProduit().getCategorieProduit());
		detailProduitRepository.save(dProduit);
		
		//this one is for testing 
		dProduit.setProduit(p);
		
		p.setDetailProduit(dProduit);
		return produitRepository.save(p);
		
		

		
		
	}

	@Override
	public void DeleteProduitById(Long idProduit) {
		Produit prod = produitRepository.findById(idProduit).get();
		produitRepository.delete(prod);
		detailProduitRepository.delete(prod.getDetailProduit());
		//alaaBenFradj
		
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).get();
		produit.setStock(stockRepository.findById(idStock).get());
		produitRepository.save(produit);
		
	}

	@Override
	public float getRevenueBrut(Long idP, Date d1, Date d2) {
		
		float revenueTot=0;
		List<DetailFacture> list = produitRepository.getRevenueBrut(idP, d1, d2);
		
		for (DetailFacture detailFacture : list) {
			revenueTot += detailFacture.getQte() * produitRepository.findById(idP).get().getPrixUnitaire();
		}
		
		return revenueTot ;
	}

	@Override
	public List<Produit> findProduitByCategorie(CategorieProduit categorieProduit) {
		
		return produitRepository.getByCategorie(categorieProduit);
	}

	@Override
	public List<Produit> findByRayon(Long idRayon) {
		return produitRepository.findByRayon(idRayon);
	}

	@Override
	public List<Produit> findProduitsByStockid(Long idStock) {
		return produitRepository.findByStock(idStock);
	}
	public float getRevenueBrutact(Long idP, Date d1) {
		float revenueTot=0;
		Date d2 =new Date(System.currentTimeMillis());
		List<DetailFacture> list = produitRepository.getRevenueBrut(idP, d1, d2);
		
		for (DetailFacture detailFacture : list) {
			revenueTot += detailFacture.getQte() * produitRepository.findById(idP).get().getPrixUnitaire();
		}
		
		return revenueTot ;
	}

	@Override
	public Map<CategorieClient, Float> porcentagedechaquecat(Long idp) {
		Produit p= produitRepository.findById(idp).orElse(null);
		Date d1=produitRepository.findById(idp).orElse(null).getDetailProduit().getDateCreation();
		Date d2 =new Date(System.currentTimeMillis());
		float qtetotat=0;
		List<DetailFacture>det=detailFactureRepository.getListdetfacturebyidp(idp);
		for (DetailFacture detailFacture: det) {
			qtetotat+=detailFacture.getQte();
		}


		Map<CategorieClient, Float> pourcentageMap = new HashMap<CategorieClient, Float>();

		float pourcentage;
		CategorieClient[] categories= {CategorieClient.Fidele,CategorieClient.Ordinaire,CategorieClient.Premuim};
		for (CategorieClient categorieClient : categories) {
			float Qteparcat=0;
			List<DetailFacture> list = produitRepository.getListdetfacturess(idp, categorieClient);
			for (DetailFacture detailFacture:list) {
				Qteparcat +=detailFacture.getQte();

			}
			pourcentage=(Qteparcat/qtetotat)*100;


			pourcentageMap.put(categorieClient, pourcentage);
		}



		return pourcentageMap;
	}

	@Override
	public float getrevenuebrutepatmois(Long idp,Long month) throws ParseException {
		String d1 = "2021-"+month+"-01";
		String d2 = "2021-"+month+"-30";
		Date date1 = new  SimpleDateFormat("yyyy-MM-dd").parse(d1);
		Date date2 = new  SimpleDateFormat("yyyy-MM-dd").parse(d2);
		return this.getRevenueBrut(idp, date1, date2);
	}

	@Override
	public Produit bestrevenuebrutparmois(Long month) throws ParseException {
	List<Produit>productsList=	produitRepository.findAll() ;  
	
	Produit P1 =productsList.get(0);
	float rb=getrevenuebrutepatmois(P1.getIdProduit(), month);
	
	for (Produit p:productsList) {
		if(P1!=p) {
		if (getrevenuebrutepatmois(p.getIdProduit(), month)>(getrevenuebrutepatmois(P1.getIdProduit(),month))) {
			P1=p;
			rb=getrevenuebrutepatmois(P1.getIdProduit(), month);
		}}
	}  
	if(rb==0)
	{return null;}
	
	else  {
		
		return P1;
	}
	
		
	}
  
	@Override
	public Map<String, String> Listbestrevenuebruteproduitdechaquemois() throws ParseException {
		long months[] = {01,02,03,04,05,06,07,8,9,10,11,12};
		String months1[] = {"January", "February", "March", "April", "May", "June", 
		           "July", "August", "September", "October", "November", "December"};
		
		Map<String,String>listproductMap=new  TreeMap<String, String>(new Comparator<String>()
	    {
	        public int compare(String o1, String o2)
	        {
	            return o1.compareTo(o2);
	        } 
	}) ;
	
		for (int i = 0; i < months.length; i++) {
			if(bestrevenuebrutparmois(months[i])!=null) {
				listproductMap.put(months1[i], bestrevenuebrutparmois(months[i]).getLibelle());}
			else {
				listproductMap.put(months1[i], "");
			}
			
		}
				
				return listproductMap;
	}

	@Override
	public int qtesaleProduitpamois(Long month,Long idProd) throws ParseException {
		String d1 = "2021-"+month+"-01";
		String d2 = "2021-"+month+"-30";
		Date date1 = new  SimpleDateFormat("yyyy-MM-dd").parse(d1);
		Date date2 = new  SimpleDateFormat("yyyy-MM-dd").parse(d2);
		List<Produit>list=	produitRepository.findAll();
		int qtetotal=0;
		for (Produit p:list) {
			List<DetailFacture>detailFactures=produitRepository.getRevenueBrut(idProd, date1, date2);
			qtetotal+=detailFactures.size();
		}
		
		return qtetotal;
		
	}

	@Override
	public Produit mostsaleProduitparmois(Long monthLong) throws ParseException {
		List<Produit>list=	produitRepository.findAll();
		Produit P1 =list.get(0);
		int qte=qtesaleProduitpamois(monthLong, P1.getIdProduit());
		for (Produit p:list) {
			if(P1!=p) {
			if (qtesaleProduitpamois(monthLong, p.getIdProduit())>(getrevenuebrutepatmois(P1.getIdProduit(),monthLong))) {
				P1=p;
				qte=(int) getrevenuebrutepatmois(P1.getIdProduit(),monthLong);
			}}
		}  
		if(qte==0)
		{return null;}
		
		else  {
			
			return P1;
		}
	
	}

	@Override
	public Map<String, String> ListMostsaleproduitdechaquemois() throws ParseException {
		long months[] = {01,02,03,04,05,06,07,8,9,10,11,12};
		String months1[] = {"January", "February", "March", "April", "May", "June", 
		           "July", "August", "September", "October", "November", "December"};
		Map<String,String>listproductMap=new TreeMap<String, String>() ;
	
		for (int i = 0; i < months.length; i++) {
			if(mostsaleProduitparmois(months[i])!=null) {
				listproductMap.put(months1[i], mostsaleProduitparmois(months[i]).getLibelle());}
			else {
				listproductMap.put(months1[i], "");
			}
			
		}
				
				return listproductMap;
	}
	 public void removeFournisseur(Long idf,Long idP)
	    {
	         Produit p=produitRepository.findById(idP).get();
	       List<Fournisseur> four= p.getFournisseurs().stream().filter(c->c.getIdFournisseur()!=idf).collect(Collectors.toList());
	         p.setFournisseurs( four);
	         produitRepository.save(p);
	         
	    }

	}

	
	
	



	

