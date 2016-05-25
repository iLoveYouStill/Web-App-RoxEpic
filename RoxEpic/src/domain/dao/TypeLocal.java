package domain.dao;

import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;
import domain.catalogue.Type;

@Local
public interface TypeLocal {
	/**
	 * retourne toutes les marques de la base de donn�es
	 * @return une liste de marques
	 * @throws BDDException en cas d'erreur d'insertion
	 */
	public abstract List<Type> getAll() throws Exception;
	
	/**
	 * @param marque
	 * @return r�sultat de la requ�te
	 * @throws BDDException
	 */	
	public abstract Type insert(Type ty) throws EJBException;
	
	public abstract Type delete(Type ty) throws Exception;
	
	
}
