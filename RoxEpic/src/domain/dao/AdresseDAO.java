package domain.dao;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.client.Adresse;


@Stateless
public class AdresseDAO implements AdresseLocal{
	
	@PersistenceContext(unitName="RoxEpic")
	EntityManager em;

	@Override
	public Adresse insert(Adresse a) throws EJBException {
		try{
			em.persist(a);
		}
		catch(EJBException e){
			throw new EJBException (e.getMessage());
		}
		return a;
	}
	
	@Override
	public void deleteAdresse (Adresse a) throws Exception{
		try{
			//Adresse ad= new Adresse();
			//ad=em.find(Adresse.class, a.getId());
			System.out.println("Suppression de l'adresse : " + a);
			em.remove(em.merge(a));
		}
		catch(Exception e){
			throw new Exception("L'adresse n'existe pas");
		}
	}
	
	@Override
	public void modifierAdresse(Adresse a) throws Exception {			
		try{
			em.merge(a);
		}
		catch(EJBException e){
			throw new EJBException (e.getMessage());
		}		
	}
	
	@Override
	public Adresse getById (int adresseId) throws Exception{
		return em.find(Adresse.class, adresseId);
	}
}
