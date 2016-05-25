package web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import domain.achat.Commande;
import domain.achat.LigneCommande;
import domain.achat.LigneCommandePK;
import domain.catalogue.Piece;
import domain.client.Adresse;
import domain.client.Utilisateur;
import service.ServiceException;
import service.ServiceLocal;

@SessionScoped
@ManagedBean
public class PanierBean extends MessageBean implements Serializable{
	
	@EJB
	private ServiceLocal svc=null;
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{commandeBean}")
	private CommandeBean commandeBean;
	
	private double montantTotal;
	private Set<LigneCommande> lignesPanier = new HashSet<LigneCommande>();
	private double livraison;
	private Calendar date;
	//private boolean validation;
		
    public PanierBean(){
    	
    }

	public ServiceLocal getSvc() {
		return svc;
	}

	public void setSvc(ServiceLocal svc) {
		this.svc = svc;
	}
	
	public Set<LigneCommande> getLignesPanier() {
		return lignesPanier;
	}

	public void setLignesPanier(Set<LigneCommande> lignesPanier) {
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
		//return date;
		String strdate = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (date != null)
			strdate = sdf.format(date.getTime());

		return strdate;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public CommandeBean getCommandeBean() {
		return commandeBean;
	}

	public void setCommandeBean(CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}
	
	public int getNbreLignes() {
		if (!lignesPanier.isEmpty())
			return lignesPanier.size();
		else
			return 0;
	}

	public void ajouterLignePanier(Piece p, int quantite){
		LigneCommande lc=new LigneCommande();
		lc.setPiece(p);
		lc.setQuantite(quantite);
		lc.setPrixUnitaire(p.getPrix());
		
		if(p.getStock() < 1) {
			this.setMessage("Désolé, cette pièce est hors stock");
			this.setTitre("Hors stock");
			this.afficheMessage();
		}
		else {
			if(lignesPanier.contains(lc)){
				this.setMessage("Cette pièce est déjà dans votre panier");
				this.setTitre("Doublon");
				this.afficheMessage();;
			}
			else{
				lignesPanier.add(lc);
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dialogConfirm').show();");
				/*this.setMessage("Pièce ajoutée au panier");
				this.setTitre("Ajout au panier");
				this.afficheMessage();*/
			}
		}
	}

	public String ajouterCommande(Adresse adresse, Utilisateur utilisateur){
		double montantTotal=livraison;	
		Commande co= new Commande();
		co.setAdresse(adresse);
		co.setUtilisateur(utilisateur);
		co.setDate(Calendar.getInstance());
		//setDate(Calendar.getInstance());
		for(LigneCommande lc : lignesPanier){
			montantTotal=montantTotal+(lc.getPrixUnitaire()*lc.getQuantite());
		}
		co.setMontantTotal(montantTotal);
		try {
			co=svc.insert(co);
		} catch (ServiceException e) {
			System.out.println("insertion de commande impossible");
		}
		
		for (LigneCommande lc : lignesPanier){
			LigneCommandePK lcpk=new LigneCommandePK(co.getId(), lc.getPiece().getIdPiece());
			lc.setCommande(co);
			lc.setLigneCommandePK(lcpk);
			try {
				svc.insert(lc);
				// Retire du stock
				lc.getPiece().setStock(lc.getPiece().getStock() - lc.getQuantite());
				svc.updatePiece(lc.getPiece());
			} catch (ServiceException e) {
				System.out.println(lc.getPiece().getLibelle() + " insertion ligne de commande impossible");
			}
			
		}
		commandeBean.getCommandesByUtilisateur(utilisateur);
		return "validation?faces-redirect=true";
	}
	
	public void supprimerLigne(LigneCommande lc) {
		lignesPanier.remove(lc);
	}
	
	public void nettoyage() {
		setMontantTotal(0.0);
		setLivraison(0.0);
		lignesPanier = new HashSet<LigneCommande>();
	}
	
	public void verifStock(LigneCommande lp, int stock) {
		if (lp.getQuantite() >= stock) {
			this.setMessage("Stock maximum atteint");
			this.setTitre("Alerte stock");
			this.afficheMessage();
			lp.setQuantite(stock);
		}
		else if (lp.getQuantite() < 1)
			lignesPanier.remove(lp);
	}
}
