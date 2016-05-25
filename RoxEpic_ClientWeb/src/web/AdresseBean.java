package web;

import java.io.Serializable;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import domain.client.Adresse;
import domain.client.Utilisateur;
import service.ServiceException;
import service.ServiceLocal;

@SessionScoped
@ManagedBean
public class AdresseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Adresse adresse;
	private int codePostal;
	private String complement;
	private String libelle;
	private String pays;
	private String rue;
	private String ville;
	private Utilisateur utilisateur;
	private int idAdresse;
	private Set<Adresse> listAdresses;	

	@EJB
    private ServiceLocal svc = null;
	
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	public Set<Adresse> getListAdresses() {
		return listAdresses;
	}
	public void setListAdresses(Set<Adresse> listAdresses) {
		this.listAdresses = listAdresses;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public ServiceLocal getSvc() {
		return svc;
	}
	public void setSvc(ServiceLocal svc) {
		this.svc = svc;
	}
	
	public void modifier(){
		
		adresse.setId(idAdresse);
		adresse.setLibelle(getLibelle());
		adresse.setRue(getRue());
		adresse.setComplement(getComplement());
		adresse.setCodePostal(getCodePostal());
		adresse.setVille(getVille());
		adresse.setPays(getPays());
		try {
			svc.modifierAdresse(adresse);
		} catch (ServiceException e) {
			e.printStackTrace();
		}		
		getAdressesByUtilisateur(utilisateur);
	}
	
	public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Vos modifications ont été enregistrées"));
    }
		
	public void supprimer(){
		try {
			svc.deleteAdresse(adresse);
			System.out.println("ploup");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		codePostal = 0;
		complement = null;
		libelle = null;
		pays = null;
		rue = null;
		ville = null;
		getAdressesByUtilisateur(utilisateur);
	}
	
	public void ajouterAdresse(){
		codePostal = 0;
		complement = null;
		libelle = null;
		pays = null;
		rue = null;
		ville = null;
	}
	
	public void ajouterByUtilisateur(){
		Adresse adresse = new Adresse();
		adresse.setCodePostal(getCodePostal());
		adresse.setComplement(getComplement());
		adresse.setLibelle(getLibelle());
		adresse.setPays(getPays());
		adresse.setRue(getRue());
		adresse.setVille(getVille());
		adresse.setUtilisateur(utilisateur);
		try {
			svc.insertAdresse(adresse);
		} catch (ServiceException e) {
			e.printStackTrace();
		}		
		codePostal = 0;
		complement = null;
		libelle = null;
		pays = null;
		rue = null;
		ville = null;
		getAdressesByUtilisateur(utilisateur);
	}
	
	public void afficherAdresse(){
		Adresse adresse= new Adresse();		
		try {
			adresse = svc.getAdresseById(idAdresse);
			this.adresse=adresse;
		} catch (ServiceException e) {
			e.printStackTrace();
		}			
		libelle = adresse.getLibelle();
		rue = adresse.getRue();
		complement = adresse.getComplement();
		ville = adresse.getVille();
		codePostal = adresse.getCodePostal();
		pays = adresse.getPays();
		
	}
	
	public void getAdressesByUtilisateur(Utilisateur utilisateur) {
		
		try {
			listAdresses = svc.adresseGetByUtilisateur(utilisateur);	
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		Adresse adresse= new Adresse();
		adresse = listAdresses.iterator().next();
		this.adresse = adresse;
		libelle = adresse.getLibelle();
		rue = adresse.getRue();
		complement = adresse.getComplement();
		ville = adresse.getVille();
		codePostal = adresse.getCodePostal();
		pays = adresse.getPays();			
	}
	
}
