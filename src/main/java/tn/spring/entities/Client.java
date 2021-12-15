package tn.spring.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table( name = "Client")


public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@JoinColumn(name="idClient")
private Long idClient; 
private String nom;
private String prenom;
private String email;
private String password;
private String url ;
@Temporal(TemporalType.DATE)
private Date dateNaissance;
@Enumerated(EnumType.STRING)
private Profession profession;
@Enumerated(EnumType.STRING)
private CategorieClient categorieClient;


@OneToMany(cascade = CascadeType.ALL,mappedBy = "client",fetch = FetchType.LAZY)
private Set<Reclamation> reclamations;

@OneToMany(cascade = CascadeType.ALL,mappedBy = "client",fetch = FetchType.LAZY)
private List<Facture> factures;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setReclamations(Set<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public String getUrl() {
		return url;
	}

	public Set<Reclamation> getReclamations() {
		return reclamations;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public CategorieClient getCategorieClient() {
		return categorieClient;
	}

	public void setCategorieClient(CategorieClient categorieClient) {
		this.categorieClient = categorieClient;
	}

	public List<Facture> getFactures() {
		return factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}
}
