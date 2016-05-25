package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import domain.achat.Commande;

@Local
public interface CommandeLocal {

	public abstract List<Commande> getAll() throws Exception;
	public abstract Commande insert(Commande co) throws EJBException;
	public abstract void delete(Commande co) throws Exception;
	
}
