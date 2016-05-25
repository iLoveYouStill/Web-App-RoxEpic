package web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.catalogue.Marque;
import domain.catalogue.Modele;
import domain.catalogue.Type;
import service.ServiceException;
import service.ServiceLocal;

@SessionScoped
@ManagedBean
public class ModeleBean implements Serializable{
	
	@EJB
	private ServiceLocal svc=null;
	
	private static final long serialVersionUID = 1L;
	private int selectModele;
	private boolean exist = true;

	public ModeleBean(){
    	
    }
    
   public List<Modele> getAllModeles(int t, int m){
	    Marque ma=new Marque();
	    Type ty = new Type();
	    ma.setId(m);
	    ty.setId(t);
    	List<Modele> lm=null;
    	
    	try {
			lm=svc.getModeleByTypeMarque(ma, ty);
			if(lm.size() > 0)
				setExist(false);
			else {
				setExist(true);
				this.setSelectModele(0);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    		return lm;
    }
   
	public ServiceLocal getSvc() {
		return svc;
	}

	public void setSvc(ServiceLocal svc) {
		this.svc = svc;
	}

	public int getSelectModele() {
		return selectModele;
	}

	public void setSelectModele(int selectModele) {
		this.selectModele = selectModele;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
