package domain.dao;



import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.achat.Commande;
import domain.client.Adresse;
import domain.client.Utilisateur;


@Stateless
public class UtilisateurDAO implements UtilisateurLocal {
		
	@PersistenceContext(unitName="RoxEpic")
	EntityManager em;

	@Override
	public Utilisateur insert(Utilisateur u) throws EJBException {
		try{
			em.persist(u);
		}
		catch(EJBException e){
			throw new EJBException (e.getMessage());
		}
		return u;
	}
	
	@Override
	public void delete(Utilisateur u) throws Exception {
		Utilisateur util= new Utilisateur();
		try{
			util=em.find(Utilisateur.class, u.getId());
			em.remove(util);
		}
		catch(Exception e){
			throw new Exception("L'utilisateur n'existe pas");
		}
		
	}

	@Override
	public Utilisateur identifier(String identifiant, String motDePasse) throws Exception {
		Utilisateur u = null;
		try{
			Query q= em.createQuery("SELECT u FROM Utilisateur u WHERE u.email= :param1");
			q.setParameter("param1", identifiant);
			//q.setParameter("param2", motDePasse);
			u=(Utilisateur)q.getSingleResult();
			
			if(authentification(motDePasse, u.getMotDePasse(), u.getGrainDeSel()))
				return u;
			else
				throw new Exception("Identification incorrecte");
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Adresse> adressesGetByUtilisateur(Utilisateur u) throws Exception {
		Set<Adresse> adresses;
		try{
			Query q=em.createQuery("SELECT a FROM Utilisateur AS u JOIN u.adresses AS a WHERE u= :param");
			q.setParameter("param", u);
			adresses= new HashSet<Adresse>(q.getResultList());
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return adresses;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Commande> commandesGetByUtilisateur(Utilisateur u) throws Exception {
		Set<Commande> commandes;
		try{
			Query q=em.createQuery("SELECT a FROM Utilisateur AS u JOIN u.commandes AS a WHERE u= :param");
			q.setParameter("param", u);
			commandes= new HashSet<Commande>(q.getResultList());
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return commandes;
	}
	
	@Override
	public void modifier(Utilisateur u) throws Exception {			
		try{
			em.merge(u);
		}
		catch(EJBException e){
			throw new EJBException (e.getMessage());
		}		
	}
	
	// Méthodes d'identification
	public boolean authentification(String motDePasseSaisi, byte[] motDePasseCrypte, byte[] grainDeSel) throws NoSuchAlgorithmException, InvalidKeySpecException {
		  // Encrypt the clear-text motDePasse using the same grainDeSel that was used to
		  // encrypt the original motDePasse
		  byte[] cryptageMotDePasseSaisi = crypteMotDePasse(motDePasseSaisi, grainDeSel);
	
		  // Authentication succeeds if encrypted motDePasse that the user entered
		  // is equal to the stored hash
		  return Arrays.equals(motDePasseCrypte, cryptageMotDePasseSaisi);
	 }

	 public byte[] crypteMotDePasse(String motDePasse, byte[] grainDeSel) throws NoSuchAlgorithmException, InvalidKeySpecException {
		  // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
		  // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
		  String algorithme = "PBKDF2WithHmacSHA1";
		  // SHA-1 generates 160 bit hashes, so that's what makes sense here
		  int longueurDeLaCle = 160;
		  // Pick an iteration count that works for you. The NIST recommends at
		  // least 1,000 iterations:
		  // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
		  // iOS 4.x reportedly uses 10,000:
		  // http://blog.crackmotDePasse.com/2010/09/smartphone-forensics-cracking-blackberry-backup-motDePasses/
		  int iterations = 20000;
	
		  KeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), grainDeSel, iterations, longueurDeLaCle);
	
		  SecretKeyFactory f = SecretKeyFactory.getInstance(algorithme);
	
		  return f.generateSecret(spec).getEncoded();
	 }

	 public byte[] generationGrainDeSel() throws NoSuchAlgorithmException {
		  // VERY important to use SecureRandom instead of just Random
		  SecureRandom aleatoire = SecureRandom.getInstance("SHA1PRNG");
	
		  // Generate a 8 byte (64 bit) grainDeSel as recommended by RSA PKCS5
		  byte[] grainDeSel = new byte[8];
		  aleatoire.nextBytes(grainDeSel);
	
		  return grainDeSel;
	 }	
}
