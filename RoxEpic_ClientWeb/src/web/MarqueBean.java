package web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import domain.catalogue.Marque;
import service.ServiceException;
import service.ServiceLocal;

@ApplicationScoped
@ManagedBean
public class MarqueBean implements Serializable{
	
	@EJB
	private ServiceLocal svc=null;
	
	private static final long serialVersionUID = 1L;
	List<Marque> marques;
 
    public MarqueBean(){
    	
    }
    
    @PostConstruct
    public void init() {
    	try {
			setMarques(svc.getAllMarques());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    }
    
	public ServiceLocal getSvc() {
		return svc;
	}

	public void setSvc(ServiceLocal svc) {
		this.svc = svc;
	}

	public List<Marque> getMarques() {
		return marques;
	}

	public void setMarques(List<Marque> marques) {
		this.marques = marques;
	}
}
