package tn.spring.entities;
import java.io.Serializable;
import java.sql.Clob;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table( name = "Produit")
public class Produit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idProduit")
	private Long idProduit;
	private String code;
	private String libelle;
	private Float prixUnitaire;


	

	private int quantite;
	
	private float remise;
	@Lob
	@Column(name="iamge",length =50000)
	private String url;

	
	
	@ManyToOne
	//@JsonIgnore
	@JoinColumn(name = "rayonId")
	private Rayon rayon;
	
	
	
	@ManyToOne
	//@JsonIgnore
	@JoinColumn(name = "stockId")
	private Stock stock;
	
	
	
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "idDetailProduit")
	private DetailProduit detailProduit ;
	
	
	
	@OneToMany( cascade = CascadeType.ALL ,mappedBy = "produit")
	@JsonIgnore
	private Set<DetailFacture> detailFactures = new HashSet<>();


	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(joinColumns = 
				@JoinColumn(name ="produitId"),
			inverseJoinColumns = 
				@JoinColumn(name = "fournisseurId"))
	private List<Fournisseur> fournisseurs;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public DetailProduit getDetailProduit() {
		return detailProduit;
	}

	public void setDetailProduit(DetailProduit detailProduit) {
		this.detailProduit = detailProduit;
	}

	public Set<DetailFacture> getDetailFactures() {
		return detailFactures;
	}

	public void setDetailFactures(Set<DetailFacture> detailFactures) {
		this.detailFactures = detailFactures;
	}

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

/*
 * 
 * 
    @JoinTable(
        name="CUST_PHONE",
        joinColumns=
            @JoinColumn(name="CUST_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="PHONE_ID", referencedColumnName="ID")
    )

 * 
 */

	
	
}
