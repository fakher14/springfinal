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

	
}
