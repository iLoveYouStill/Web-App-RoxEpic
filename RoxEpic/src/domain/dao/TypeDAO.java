package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.*;

import domain.catalogue.Type;

@Stateless
public class TypeDAO implements TypeLocal{
	
	@PersistenceContext(unitName="RoxEpic")
	private EntityManager em;
	
	@Override
	public Type insert(Type ty) throws EJBException {
		em.persist(ty);
		return ty;		
	}
	
	@Override
	public Type delete(Type ty) throws EJBException {
		em.remove(em.merge(ty));
		return ty;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAll() throws Exception{
		return em.createQuery("select object(o) from Type as o").getResultList();
	}
	
	
	
}
