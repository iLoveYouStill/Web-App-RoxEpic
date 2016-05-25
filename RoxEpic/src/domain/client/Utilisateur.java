package domain.client;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import domain.achat.Commande;

@Entity
@Table(name="utilisateur")
public class Utilisateur implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utilisateur", unique=true)
	private int idUtilisateur;

	@Column(name="nom",nullable = false, length = 50)
	private String nom;
	
	@Column(name="prenom",nullable = false, length = 50)
	private String prenom;
	
	@Column(name="motDePasse",nullable = false)
	private byte[] motDePasse;
	
	@Column(name="grainDeSel",nullable = false)
	private byte[] grainDeSel;
	
	@Column(name="email",nullable = false, length = 50, unique=true)
	private String email;
	
	@Column(name="telephone",nullable = false, length = 10)
	private int telephone;
	
	@OneToMany(mappedBy="utilisateur", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    private Set<Adresse> adresses;
	
	@OneToMany(mappedBy="utilisateur", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    private Set<Commande> commandes;
	
	public Utilisateur() {	
	}
	
	public Utilisateur(String nom, String prenom, byte[] motDePasse, String email, int telephone) {	
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.email = email;
		this.telephone = telephone;
	}
	
	public Utilisateur(int idUtilisateur, String nom, String prenom, byte[] motDePasse, String email, int telephone) {
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.email = email;
		this.telephone = telephone;
	}
	
	public int getId() {
		return idUtilisateur;
	}

	public void setId(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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

	public byte[] getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(byte[] motDePasse) {
		this.motDePasse = motDePasse;
	}

	public byte[] getGrainDeSel() {
		return grainDeSel;
	}

	public void setGrainDeSel(byte[] grainDeSel) {
		this.grainDeSel = grainDeSel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	public Set<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(Set<Adresse> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		return prenom + " - " + nom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUtilisateur;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (idUtilisateur != other.idUtilisateur)
			return false;
		return true;
	}
}
