package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.*;

import domain.achat.Commande;

@Stateless
public class CommandeDAO implements CommandeLocal {
	
	@PersistenceContext(unitName="RoxEpic")
	private EntityManager em;
	
	@Override
	public Commande insert (Commande co)throws EJBException {
		
		try{
			em.persist(co);
		}
		catch(EJBException e){
			throw new EJBException(e.getMessage());
		}
		return co;
	}
	
	@Override
	public void delete(Commande co) throws Exception {
		Commande commande = new Commande();
		try{
			commande=em.find(Commande.class,  co.getId());
			em.remove(commande);
		}
		catch(Exception e){
			throw new Exception("La commande n'existe pas");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getAll() throws Exception {
		
		List<Commande> listCo;
		try{
			listCo = em.createQuery("select object(o) from Commande as o").getResultList();
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return  listCo;
	}
	
}
