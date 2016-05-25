package domain.achat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.*;

import domain.client.*;

@Entity
@Table(name="commande")
public class Commande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706492375951591812L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commande")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_adresse", nullable=false)
    private Adresse adresse;
	
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", nullable=false)
	private Utilisateur utilisateur;
	
	@Column(name="montant_total", nullable=false)
    private double montantTotal;
	
	@Column(name = "date", nullable=false)
	@Temporal(TemporalType.DATE)
	private java.util.Calendar date;
	
	//cascade={CascadeType.ALL}, fetch=FetchType.EAGER
	@OneToMany(mappedBy = "commande", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<LigneCommande> lignesCommande;
		
	public Commande() {
	
	}

	public Commande(Adresse adresse, Utilisateur utilisateur, double montantTotal,
			Calendar date) {
		this.adresse = adresse;
		this.utilisateur = utilisateur;
		this.montantTotal = montantTotal;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	/*public java.util.Calendar getDate() {
		return date;
	}*/
	
	public String getDate () {
		String strdate = null;
		
		// Pattern de sortie
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (date != null)
			strdate = sdf.format(date.getTime());

		return strdate;
	}

	public void setDate(java.util.Calendar date) {
		this.date = date;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Set<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}	

	public void setLignesCommande(Set<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	@Override
	public String toString() {
		return id + " - " + getDate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Commande other = (Commande) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
