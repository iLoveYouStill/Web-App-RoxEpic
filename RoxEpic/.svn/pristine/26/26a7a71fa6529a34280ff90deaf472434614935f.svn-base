package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.*;

import domain.catalogue.Marque;

@Stateless
public class MarqueDAO implements MarqueLocal{
	
	@PersistenceContext(unitName="RoxEpic")
	private EntityManager em;
	
	@Override
	public Marque insert(Marque ma) throws EJBException {
		em.persist(ma);
		return ma;		
	}
	
	@Override
	public Marque delete(Marque ma) throws EJBException {
		em.remove(em.merge(ma));
		return ma;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Marque> getAll() throws Exception {
		return em.createQuery("select object(o) from Marque as o").getResultList();
	}
	
	
	
}
