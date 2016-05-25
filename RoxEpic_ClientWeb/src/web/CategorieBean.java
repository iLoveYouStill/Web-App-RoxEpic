package web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import domain.catalogue.Categorie;
import service.ServiceException;
import service.ServiceLocal;

@ApplicationScoped
@ManagedBean
public class CategorieBean implements Serializable{
	
	@EJB
	private ServiceLocal svc=null;
	
	private static final long serialVersionUID = 1L;
	List<Categorie> categories;

    public CategorieBean(){
    	
    }
    
    @PostConstruct
    public void init() {
    	try {
			setCategories(svc.getAllCategories());
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

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
}
