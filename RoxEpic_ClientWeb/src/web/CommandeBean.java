package web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import domain.achat.Commande;
import domain.achat.LigneCommande;
import domain.client.Utilisateur;
import service.ServiceException;
import service.ServiceLocal;

@SessionScoped
@ManagedBean
public class CommandeBean implements Serializable{
	
	@EJB
	private ServiceLocal svc=null;
	
	private static final long serialVersionUID = 1L;

	private double montantTotal;
	private List<LigneCommande> lignesPanier;
	private double livraison;
	private Calendar date;
	private Commande commande;
	private Utilisateur utilisateur;
	private LigneCommande ligneCommande;
	private Set<Commande> listCommandes;
	private TreeNode root;
	    
	public CommandeBean(){
    	
    }
	public ServiceLocal getSvc() {
		return svc;
	}
	public void setSvc(ServiceLocal svc) {
		this.svc = svc;
	}
	public List<LigneCommande> getLignesPanier() {
		return lignesPanier;
	}
	public void setLignesPanier(List<LigneCommande> lignesPanier) {
		this.lignesPanier = lignesPanier;
	}
	public double getMontantTotal() {
		this.montantTotal = livraison;
		for(LigneCommande lc : lignesPanier){
			this.montantTotal += lc.getPrixUnitaire()*lc.getQuantite();
		}
		return montantTotal;
	}
	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}
	public double getLivraison() {
		return livraison;
	}
	public void setLivraison(double livraison) {
		this.livraison = livraison;
	}	
	public String getDate() {
		String strdate = null;
		
		// Pattern de sortie
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (date != null)
			strdate = sdf.format(date.getTime());

		return strdate;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}
	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}
	public Set<Commande> getListCommandes() {
		return listCommandes;
	}
	public void setListCommandes(Set<Commande> listCommandes) {
		this.listCommandes = listCommandes;
	}	
	public TreeNode getRoot() {
		return root;
	}
	public void setRoot(TreeNode root) {
		this.root = root;
	}
		
	public void createTreeCommandes(){
		
		this.root = new DefaultTreeNode(commande, null);
		getCommandesByUtilisateur(utilisateur);
	
		for ( Commande c :listCommandes){
			TreeNode commandes = new DefaultTreeNode(c, this.root);
			//commandes.setParent(this.root);
			getLignesDeCoByUtilisateur(c);
			for ( LigneCommande l :lignesPanier){
	
				TreeNode commande = new DefaultTreeNode(l, commandes);
				//commande.setParent(commandes);
			}
		}
	}
		
	public void getCommandesByUtilisateur(Utilisateur utilisateur) {
		
		try {
			listCommandes = svc.commandeGetByUtilisateur(utilisateur);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
//		Commande commande = new Commande();
//		commande = listCommandes.iterator().next();
//		this.commande = commande;
	}
	
	public void getLignesDeCoByUtilisateur(Commande commande) {
		this.commande = commande;
		try {
			lignesPanier = svc.getByCom(commande);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}