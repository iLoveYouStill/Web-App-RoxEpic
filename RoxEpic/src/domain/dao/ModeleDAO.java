package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.catalogue.Marque;
import domain.catalogue.Modele;
import domain.catalogue.Type;

@Stateless
public class ModeleDAO implements ModeleLocal {

	@PersistenceContext(unitName="RoxEpic")
	EntityManager em;
	
	@Override
	public Modele insert(Modele mo) throws EJBException {
		try{
			em.persist(mo);
		}
		catch(EJBException e){
			throw new EJBException (e.getMessage());
		}
		
		return mo;
	}

	@Override
	public void delete(Modele mo) throws Exception {
		Modele mod= new Modele();
		try{
			mod=em.find(Modele.class, mo.getIdModele());
			em.remove(mod);
		}
		catch(Exception e){
			throw new Exception("Le mod�le n'existe pas");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Modele> getBy(Marque ma, Type ty) throws Exception {
		
		List<Modele> lc=null;
		ma=em.find(Marque.class, ma.getIdMarque());
		ty=em.find(Type.class, ty.getIdType());
		try{
			
			Query q= em.createQuery("SELECT m FROM Modele m WHERE m.marque= :param1 AND m.type= :param2");
			q.setParameter("param1",ma);
			q.setParameter("param2",ty);
			lc=(List<Modele>)q.getResultList();
			
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return lc;
		
	}

}
