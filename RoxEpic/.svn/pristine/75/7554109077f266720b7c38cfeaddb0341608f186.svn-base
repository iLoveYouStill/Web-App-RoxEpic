package domain.achat;

import java.io.Serializable;

import javax.persistence.*;

import domain.catalogue.Piece;

@Entity
@Table(name="ligne_commande")
public class LigneCommande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1754020952832041632L;
	
	@EmbeddedId
    private LigneCommandePK ligneCommandePK;
	
	@ManyToOne
	@MapsId("idCommande")
	@JoinColumn(name = "id_commande")
    private Commande commande;
	
	@ManyToOne
	@MapsId("idPiece")
	@JoinColumn(name = "id_piece")
    private Piece piece;
	
	@Column(name = "quantite", nullable = false)
	private int quantite;
	
	@Column(name = "prix_unitaire", nullable = false)
	private double prixUnitaire;
	
	public LigneCommande() {

	}
	
	public LigneCommande(Commande commande,
			Piece piece, int quantite, double prixUnitaire) {
		this.ligneCommandePK = new LigneCommandePK(commande.getId(), piece.getIdPiece());
		this.commande = commande;
		this.piece = piece;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
	}

	public LigneCommandePK getLigneCommandePK() {
		return ligneCommandePK;
	}

	public void setLigneCommandePK(LigneCommandePK ligneCommandePK) {
		this.ligneCommandePK = ligneCommandePK;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	@Override
	public String toString() {
		return ligneCommandePK.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commande == null) ? 0 : commande.hashCode());
		result = prime * result
				+ ((ligneCommandePK == null) ? 0 : ligneCommandePK.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prixUnitaire);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		LigneCommande other = (LigneCommande) obj;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
			return false;
		if (ligneCommandePK == null) {
			if (other.ligneCommandePK != null)
				return false;
		} else if (!ligneCommandePK.equals(other.ligneCommandePK))
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		if (Double.doubleToLongBits(prixUnitaire) != Double
				.doubleToLongBits(other.prixUnitaire))
			return false;
		return true;
	}
}
