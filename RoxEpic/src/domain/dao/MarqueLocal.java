package domain.dao;

import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;
import domain.catalogue.Marque;

@Local
public interface MarqueLocal {
	/**
	 * retourne toutes les marques de la base de donn�es
	 * @return une liste de marques
	 * @throws BDDException en cas d'erreur d'insertion
	 */
	public abstract List<Marque> getAll() throws Exception;
	
	/**
	 * @param marque
	 * @return r�sultat de la requ�te
	 * @throws BDDException
	 */	
	public abstract Marque insert(Marque ma) throws EJBException;
	
	public abstract Marque delete(Marque ma) throws Exception;
	
	
}
