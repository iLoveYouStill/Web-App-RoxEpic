package domain.dao;

import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;
import domain.catalogue.Categorie;

@Local
public interface CategorieLocal {
	/**
	 * retourne toutes les marques de la base de données
	 * @return une liste de marques
	 * @throws BDDException en cas d'erreur d'insertion
	 */
	public abstract List<Categorie> getAll() throws Exception;
	
	/**
	 * @param marque
	 * @return résultat de la requête
	 * @throws BDDException
	 */	
	public abstract Categorie insert(Categorie ca) throws EJBException;
	
	public abstract Categorie delete(Categorie ca) throws Exception;
	
	public abstract Categorie getCatById(int id) throws Exception;
}
