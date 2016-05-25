package domain.achat;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class LigneCommandePK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 352720101548243997L;
	
	//@Basic(optional=false)
	//@Column(name = "id_commande")
    int idCommande;
	
	//@Basic(optional=false)
	//@Column(name = "id_piece")
    int idPiece;
    
	public LigneCommandePK() {
		
	}

	public LigneCommandePK(int idCommande, int idPiece) {
		this.idCommande = idCommande;
		this.idPiece = idPiece;
	}

	public int getIdCommande() {
		return idCommande;
	}
	
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	
	public int getIdPiece() {
		return idPiece;
	}
	
	public void setIdPiece(int idPiece) {
		this.idPiece = idPiece;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCommande;
		result = prime * result + idPiece;
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
		LigneCommandePK other = (LigneCommandePK) obj;
		if (idCommande != other.idCommande)
			return false;
		if (idPiece != other.idPiece)
			return false;
		return true;
	}
}
