package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import domain.catalogue.Categorie;
import domain.catalogue.Modele;
import domain.catalogue.Piece;

@Local
public interface PieceLocal {

	public abstract Piece insert(Piece piece) throws EJBException;
	public abstract Piece associe(Piece piOrigine, Modele mo) throws EJBException;
	public abstract Piece getById(int pieceId) throws Exception;
	public abstract void update(Piece piece) throws Exception;
	public abstract List<Piece> getOriginalesBy(Modele mo, Categorie ca) throws Exception;
	public abstract List<Piece> getEquivalentesBy(Modele mo, Categorie ca) throws Exception;
	public abstract List<Piece> getByCat(Categorie ca) throws Exception;
	
}
