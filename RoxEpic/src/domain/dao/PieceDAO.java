package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.catalogue.Categorie;
import domain.catalogue.Modele;
import domain.catalogue.Piece;

@Stateless
public class PieceDAO implements PieceLocal {
	
	@PersistenceContext(unitName="RoxEpic")
	EntityManager em;

	@Override
	public Piece insert(Piece piece) throws EJBException {
		em.persist(piece);
		return piece;
	}

	@Override
	public Piece associe(Piece piOrigine, Modele mo) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Piece piece) throws Exception {
		em.merge(piece);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Piece> getOriginalesBy(Modele mo, Categorie ca) {
		List<Piece> pieces;
		Query query;
		
		if(ca == null)
			query = em.createQuery("SELECT p FROM Piece p JOIN p.modeles m WHERE m=:param2 AND p.originale IS NULL");
		else {
			query = em.createQuery("SELECT p FROM Piece p JOIN p.modeles m WHERE (p.categorie=:param1 AND m=:param2) AND p.originale IS NULL");
			query.setParameter("param1", ca);
		}
		query.setParameter("param2", mo);
		pieces=(List<Piece>)query.getResultList();
		return pieces;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Piece> getEquivalentesBy(Modele mo, Categorie ca) {
		List<Piece> pieces;
		Query query;
		
		if(ca == null)
			query = em.createQuery("SELECT p FROM Piece p WHERE p.originale IN (SELECT p FROM Piece p JOIN p.modeles m WHERE m=:param2)");
		else {
			query = em.createQuery("SELECT p FROM Piece p WHERE p.originale IN (SELECT p FROM Piece p JOIN p.modeles m WHERE p.categorie=:param1 AND m=:param2)");
			query.setParameter("param1", ca);
		}
		query.setParameter("param2", mo);
		pieces=(List<Piece>)query.getResultList();
		return pieces;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Piece> getByCat(Categorie ca) throws Exception {
		List<Piece> pieces;
		try{
			Query q= em.createQuery("SELECT p FROM Piece p WHERE p.categorie=:param1");
			q.setParameter("param1", ca);
			pieces=(List<Piece>)q.getResultList();
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return pieces;
	}
	
	@Override
	public Piece getById(int pieceId) throws Exception {
		return em.find(Piece.class, pieceId);
		/*Piece piece;
		try{
			//Query q= em.createQuery("SELECT new domain.catalogue.Piece(p.id, p.libelle, p.reference, p.prix, p.stock, p.originale, p.categorie) FROM Piece p WHERE p.id=:param1");
			Query q= em.createQuery("SELECT p FROM Piece p WHERE p.id=:param1");
			q.setParameter("param1", pieceId);
			logger.info(q.getResultList());
			piece = (Piece) q.getSingleResult();
			piece = em.find(Piece.class, piece.getId());
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return piece;*/
	}

}
