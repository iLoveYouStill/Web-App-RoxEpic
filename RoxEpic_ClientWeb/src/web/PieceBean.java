package web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import domain.catalogue.Categorie;
import domain.catalogue.Modele;
import domain.catalogue.Piece;
import service.ServiceException;
import service.ServiceLocal;

@SessionScoped
@ManagedBean
public class PieceBean implements Serializable{
	
	@EJB
	private ServiceLocal svc=null;
	
	private static final long serialVersionUID = 1L;
	private int piece;
	private boolean exist = false;
	private List<Piece> pieces;
	
	private String selectFiltre = "lesDeux";
	private int selectCategorie;
	
	// Valeurs uniquement en phase de développement
	private int selectMarque = 1;
	private int selectType = 1;
	
	@ManagedProperty(value="#{modeleBean}")
	private ModeleBean modeleBean;
	
	public void setModeleBean(ModeleBean modeleBean) {
		this.modeleBean = modeleBean;
	}
 
    public PieceBean(){
    	
    }

	public List<Piece> getPieces() {
    	return this.pieces;
    }
    
	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public ServiceLocal getSvc() {
		return svc;
	}

	public void setSvc(ServiceLocal svc) {
		this.svc = svc;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public String getTypePiece() {
		return selectFiltre;
	}

	public void setTypePiece(String typePiece) {
		this.selectFiltre = typePiece;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	public String getSelectFiltre() {
		return selectFiltre;
	}

	public void setSelectFiltre(String selectFiltre) {
		this.selectFiltre = selectFiltre;
	}

	public int getSelectCategorie() {
		return selectCategorie;
	}

	public void setSelectCategorie(int selectCategorie) {
		this.selectCategorie = selectCategorie;
	}

	public int getSelectMarque() {
		return selectMarque;
	}

	public void setSelectMarque(int selectMarque) {
		this.selectMarque = selectMarque;
	}

	public int getSelectType() {
		return selectType;
	}

	public void setSelectType(int selectType) {
		this.selectType = selectType;
	}

	public void recherchePieces() {
	    if (modeleBean.getSelectModele() == 0)
	    	setPieces(null);
	    else {
		    Modele mo=new Modele();
		    Categorie cat = new Categorie();
		    mo.setId(modeleBean.getSelectModele());
	    
		    if (this.getSelectCategorie() == 0)
		    	cat=null;
		    else
		    	cat.setId(this.getSelectCategorie());
		    
		    switch (selectFiltre) {
			    case "origine":
			    	try {
			    		setPieces(svc.getOriginalesBy(mo, cat));
					} catch (ServiceException e1) {
						e1.printStackTrace();
					}
			    	break;
			    case "equivalente":
		    		try{
		    			setPieces(svc.getEquivalentesBy(mo, cat));
		    		} catch (ServiceException e) {
		    			e.printStackTrace();
		    		}
			    	break;
			    case "lesDeux":
		    		try {
		    			setPieces(svc.getOriginalesBy(mo, cat));
						this.pieces.addAll(svc.getEquivalentesBy(mo, cat));
					} catch (ServiceException e) {
						e.printStackTrace();
					}
			    	break;
		    }
		    
		    // affichage Pagination
			if(this.pieces.size() > 10)
				setExist(true);
			else
				setExist(false);
	    }
	}
}
