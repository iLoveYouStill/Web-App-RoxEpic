package domain.dao;

import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.Local;

import domain.achat.Commande;
import domain.client.Adresse;
import domain.client.Utilisateur;


@Local
public interface UtilisateurLocal {

	public abstract Utilisateur insert (Utilisateur u) throws EJBException;
	public abstract Utilisateur identifier (String identifiant, String motDePasse) throws Exception;
	public abstract void delete(Utilisateur u) throws Exception;
	public abstract Set<Adresse> adressesGetByUtilisateur (Utilisateur u) throws Exception;
	public abstract void modifier(Utilisateur u) throws Exception;
	public abstract Set<Commande> commandesGetByUtilisateur(Utilisateur u) throws Exception;	
}
