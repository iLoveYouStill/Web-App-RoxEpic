package domain.catalogue;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Classe persistante de Piece
 * @author Kevin Delavega
 * @version 1.1
 */
@Entity
@Table(name="piece")
public class Piece implements Serializable {

	private static final long serialVersionUID = -8903250504109943911L;
	
	/* Données membres */
	
	/** Id de la pièce */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_piece")
	private int idPiece;
	
	/** Libellé de la pièce */
	@Column(name="libelle", nullable=false, unique=true, length=50)
    private String libelle;
	
	/** Référence de la pièce */
	@Column(name="reference", nullable=false, length=50)
    private String reference;
	
	/** Prix de la pièce */
	@Column(name="prix", nullable=false, length=50)
    private double prix;
	
	/** Stock de la pièce */
	@Column(name="stock", nullable=false, length=50)
    private int stock;
	
	/** Pièce d'origine */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_piece_origine")
    private Piece originale = null;
	
	/** Pièces équivalentes */
	/*@OneToMany(mappedBy="originale", fetch=FetchType.EAGER)
    private Set<Piece> equivalentes = new HashSet<Piece> ();*/
	
	/** Catégorie de la pièce */
	@ManyToOne
	@JoinColumn(name = "id_categorie", nullable=false)
    private Categorie categorie;
	
	/** Modèles correspondants */
	@ManyToMany (fetch=FetchType.EAGER) //(mappedBy="pieces", fetch=FetchType.EAGER)
	@JoinTable(name="compose", joinColumns=@JoinColumn(name="id_piece"), inverseJoinColumns=@JoinColumn(name="id_modele"))
    private Set<Modele> modeles  = new HashSet<Modele> ();

	/**
	 * Constructeur vide de Pièce
	 */
	public Piece() {
	
	}

	/**
	 * Constructeur avec paramètres
	 *
	 * @param libelle de la pièce
	 * @param reference de la pièce
	 * @param prix de la pièce
	 * @param stock de la pièce
	 * @param originale référence vers la pièce originale (si pièce équivalente)
	 * @param categorie de la pièce
	 */
	public Piece(String libelle, String reference, double prix, int stock,
			Piece originale, Categorie categorie) {
		this.libelle = libelle;
		this.reference = reference;
		this.prix = prix;
		this.stock = stock;
		this.originale = originale;
		this.categorie = categorie;
	}
	
	/**
	 * Constructeur avec paramètres
	 *
	 * @param id de la pièce
	 * @param libelle de la pièce
	 * @param reference de la pièce
	 * @param prix de la pièce
	 * @param stock de la pièce
	 * @param originale référence vers la pièce originale (si pièce équivalente)
	 * @param categorie de la pièce
	 */
	public Piece(int id, String libelle, String reference, double prix, int stock,
			Piece originale, Categorie categorie) {
		this.idPiece = id;
		this.libelle = libelle;
		this.reference = reference;
		this.prix = prix;
		this.stock = stock;
		this.originale = originale;
		this.categorie = categorie;
	}

	/**
	 * Récupère l'id de la pièce
	 *
	 * @return l'id de la pièce
	 */
	public int getIdPiece() {
		return idPiece;
	}

	/**
	 * Récupère le libelle de la pièce
	 *
	 * @return le libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Définit le libelle
	 *
	 * @param libelle nouveau libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Recupère la référence de la pièce
	 *
	 * @return reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Définit la référence
	 *
	 * @param reference nouvelle reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Récupère le prix
	 *
	 * @return prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Définit le prix
	 *
	 * @param prix nouveau prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Récupère le stock
	 *
	 * @return stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Définit le stock
	 *
	 * @param stock nouveau stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Récupère la pièce originale (si pièce équivalente)
	 *
	 * @return originale
	 */
	public Piece getOriginale() {
		return originale;
	}

	/**
	 * Définit la pièce originale (si pièce équivalente)
	 *
	 * @param originale nouvelle pièce originale
	 */
	public void setOriginale(Piece originale) {
		this.originale = originale;
	}

	/**
	 * Récupère la catégorie
	 *
	 * @return categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * Définit la catégorie
	 *
	 * @param categorie nouvelle categorie
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * Récupère les pièces équivalentes
	 *
	 * @return equivalentes
	 */
	/*public Set<Piece> getEquivalentes() {
		return equivalentes;
	}*/

	/**
	 * Définit les pièces équivalentes d'une pièce originale
	 *
	 * @param equivalentes nouvelles pièces equivalentes
	 */
	/*public void setEquivalentes(Set<Piece> equivalentes) {
		this.equivalentes = equivalentes;
	}*/
	
	/**
	 * Récupère la liste de modèles
	 *
	 * @return modeles
	 */
	public Set<Modele> getModeles() {
		return modeles;
	}

	/**
	 * Définit la liste de modèles
	 *
	 * @param modeles nouveaux modèles
	 */
	public void setModeles(Set<Modele> modeles) {
		this.modeles = modeles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return libelle;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPiece;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (idPiece != other.idPiece)
			return false;
		return true;
	}
}
