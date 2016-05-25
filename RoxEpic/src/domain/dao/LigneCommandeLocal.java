package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import domain.achat.Commande;
import domain.achat.LigneCommande;

@Local
public interface LigneCommandeLocal {
	
	public abstract List<LigneCommande> getByCom(Commande co) throws Exception;
	public abstract LigneCommande insert(LigneCommande ligneCom) throws EJBException;
	public abstract void delete(LigneCommande ligneCom) throws Exception;

}
