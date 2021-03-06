package domain.dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.*;

import domain.achat.Commande;
import domain.achat.LigneCommande;
import domain.catalogue.Piece;

@Stateless
public class LigneCommandeDAO implements LigneCommandeLocal {
	
	@PersistenceContext(unitName="RoxEpic")
	private EntityManager em;
	
	@Override
	public LigneCommande insert(LigneCommande ligneCom) throws EJBException {
		
		try{
			Commande commande = em.find(Commande.class, ligneCom.getCommande().getId());
			Piece piece = em.find(Piece.class, ligneCom.getPiece().getIdPiece());
			ligneCom.setCommande(commande);
			ligneCom.setPiece(piece);
			//em.merge(ligneCom);
			em.persist(ligneCom);
		}
		catch(EJBException e){
			throw new EJBException(e.getMessage());
		}
		return ligneCom;
	}
	
	@Override
	public void delete(LigneCommande ligneCom) throws Exception {
		LigneCommande ligneDeCommande = new LigneCommande();
		try{
			ligneDeCommande=em.find(LigneCommande.class, ligneCom.getLigneCommandePK());
			em.remove(ligneDeCommande);
		}
		catch(Exception e){
			throw new Exception("La ligne de commande n'existe pas");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LigneCommande> getByCom(Commande co) throws Exception {
		List<LigneCommande> listLigneDeCo;
		try{
			Query q = em.createQuery("SELECT l FROM LigneCommande l WHERE l.commande=:param1");
			q.setParameter("param1", co);
			listLigneDeCo=(List<LigneCommande>)q.getResultList();
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return listLigneDeCo;	
	}
	
}
