package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import domain.catalogue.Marque;
import domain.catalogue.Modele;
import domain.catalogue.Type;

@Local
public interface ModeleLocal {

	public abstract Modele insert (Modele mo) throws EJBException;
	public abstract List<Modele> getBy(Marque ma, Type ty) throws Exception;
	public abstract void delete(Modele mo) throws Exception;

}
