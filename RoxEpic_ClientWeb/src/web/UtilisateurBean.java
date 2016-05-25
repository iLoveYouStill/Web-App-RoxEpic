package web;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import domain.client.Adresse;
import domain.client.Utilisateur;
import service.ServiceException;
import service.ServiceLocal;

@SessionScoped
@ManagedBean
public class UtilisateurBean extends MessageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private String email;
	private String motDePasse;
	private String nom;
	private String prenom;
	private int telephone;
	private int pageDep;
	
	@ManagedProperty(value="#{adresseBean}")
	private AdresseBean adresseBean;
	
	@ManagedProperty(value="#{commandeBean}")
	private CommandeBean commandeBean;

	@EJB
    private ServiceLocal svc = null;
	
    public Utilisateur getUtilisateur() {
		return utilisateur;
	}
    
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public int getTelephone() {
		return telephone;
	}
	
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	public int getPageDep() {
		return pageDep;
	}
	
	public void setPageDep(int pageDep) {
		this.pageDep = pageDep;
	}
	
	public ServiceLocal getSvc() {
		return svc;
	}
	
	public void setSvc(ServiceLocal svc) {
		this.svc = svc;
	}
	
	public AdresseBean getAdresseBean() {
		return adresseBean;
	}
	
	public void setAdresseBean(AdresseBean adresseBean) {
		this.adresseBean = adresseBean;
	}
	
	public CommandeBean getCommandeBean() {
		return commandeBean;
	}
	
	public void setCommandeBean(CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}
	
	public void definePageDep(int pageDep){
		//System.out.println(pageDep);
		this.pageDep = pageDep;	
	}
	
	public String ajouter(int page){
		Utilisateur utilisateur = new Utilisateur();	
		utilisateur.setEmail(getEmail());
		try {
			byte[] grainDeSel = generationGrainDeSel();
			utilisateur.setMotDePasse(crypteMotDePasse(getMotDePasse(), grainDeSel));
			utilisateur.setGrainDeSel(grainDeSel);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		utilisateur.setNom(getNom());
		utilisateur.setPrenom(getPrenom());
		utilisateur.setTelephone(getTelephone());
		
		try {
			utilisateur=svc.insert(utilisateur);
			this.utilisateur=utilisateur;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
			
		Adresse adresse = new Adresse();
		adresse.setCodePostal(adresseBean.getCodePostal());
		adresse.setComplement(adresseBean.getComplement());
		adresse.setLibelle(adresseBean.getLibelle());
		adresse.setPays(adresseBean.getPays());
		adresse.setRue(adresseBean.getRue());
		adresse.setVille(adresseBean.getVille());
		adresse.setUtilisateur(utilisateur);
				
		try {
			svc.insertAdresse(adresse);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		if (page==2){
			return "livraison";	
		}
		else{
			return "recherche";
		}
	}
	
	public void modifierUtilisateur() {
		
		utilisateur.setNom(getNom());
		utilisateur.setPrenom(getPrenom());
		try {
			byte[] grainDeSel = generationGrainDeSel();
			utilisateur.setMotDePasse(crypteMotDePasse(getMotDePasse(), grainDeSel));
			utilisateur.setGrainDeSel(grainDeSel);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		utilisateur.setEmail(getEmail());
		utilisateur.setTelephone(getTelephone());
		try {
			svc.modifierUtilisateur(utilisateur);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Vos modifications ont été enregistrées"));
    }
	
	public void identifier(int page) {
		
		if (page==1){
			
			try {
				setUtilisateur(svc.identifier(email, motDePasse));
				setPrenom(utilisateur.getPrenom());
				setNom(utilisateur.getNom());
				setTelephone(utilisateur.getTelephone());
				//setMotDePasse(utilisateur.getMotDePasse());
				setEmail(utilisateur.getEmail());
				adresseBean.getAdressesByUtilisateur(utilisateur);
				adresseBean.setUtilisateur(utilisateur);				
				commandeBean.setUtilisateur(utilisateur);
				commandeBean.getCommandesByUtilisateur(utilisateur);
				commandeBean.createTreeCommandes();
				
				this.setMessage("Identification réussie");
				this.setTitre("Succès");
				this.afficheMessage();
			} catch (ServiceException e) {
				this.setMessage("Identification incorrecte");
				this.setTitre("Erreur");
				this.afficheMessage();
			}
		}
		else if(page==2){
			
			try {
				setUtilisateur(svc.identifier(email, motDePasse));
				setPrenom(utilisateur.getPrenom());
				setNom(utilisateur.getNom());
				setTelephone(utilisateur.getTelephone());
				//setMotDePasse(utilisateur.getMotDePasse());
				setEmail(utilisateur.getEmail());
				adresseBean.getAdressesByUtilisateur(utilisateur);
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("livraison.jsf");
				} catch (IOException e) {
					System.out.println("Redirection impossible vers la livraison");
				}
				
			} catch (ServiceException e) {
				this.setMessage("Identification incorrecte");
				this.setTitre("Erreur");
				this.afficheMessage();
			}
		}
	}
	
	public String deconnecter() {
		setUtilisateur(null);
		setPrenom(null);
		setNom(null);
		setTelephone(0);
		setEmail(null);
		setMotDePasse(null);
		adresseBean.setUtilisateur(null);
		commandeBean.setListCommandes(null);
		/*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect(context.getRequestContextPath() + "recherche.jsf");*/
		return "recherche?faces-redirect=true";
	}
	
	// Méthodes d'identification
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
