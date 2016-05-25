package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.*;

import domain.catalogue.Categorie;

@Stateless
public class CategorieDAO implements CategorieLocal{
	
	@PersistenceContext(unitName="RoxEpic")
	private EntityManager em;
	
	@Override
	public Categorie insert(Categorie ca) throws EJBException {
		em.persist(ca);
		return ca;		
	}
	
	@Override
	public Categorie delete(Categorie ca) throws EJBException {
		em.remove(em.merge(ca));
		return ca;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> getAll() throws Exception {
		return em.createQuery("select object(o) from Categorie as o").getResultList();
	}
	
	@Override
	public Categorie getCatById(int id) throws Exception{
		Categorie cat=new Categorie();
		cat=em.find(Categorie.class, id);
		return cat;
	}
	
}
