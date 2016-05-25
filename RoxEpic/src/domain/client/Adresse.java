package domain.client;

import java.io.Serializable;

import javax.persistence.*;

import domain.achat.Commande;

//import domain.achat.Commande;

@Entity
@Table(name="adresse")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_adresse")
	private int idAdresse;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="id_utilisateur", nullable=true)
    private Utilisateur utilisateur;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="id_commande", nullable=true)
    private Commande commande;
	
	@Column(name="libelle", length = 50)
	private String libelle;
	
	@Column(name="rue", length = 50)
	private String rue;
	
	@Column(name="complement", nullable = true, length = 50)
	private String complement;
	
	@Column(name="codePostal", length = 5)
	private int codePostal;
	
	@Column(name="ville", length = 50)
	private String ville;
	
	@Column(name="pays", length = 50)
	private String pays;

	public Adresse() {		
	}
		
	public Adresse(Utilisateur utilisateur, String libelle,  
			String rue, String complement, int codePostal, String ville,
			String pays) {
		this.utilisateur = utilisateur;
		this.libelle = libelle;
		this.rue = rue;
		this.complement = complement;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	public int getId() {
		return idAdresse;
	}

	public void setId(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return libelle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAdresse;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Adresse other = (Adresse) obj;
		if (idAdresse != other.idAdresse)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}
}
