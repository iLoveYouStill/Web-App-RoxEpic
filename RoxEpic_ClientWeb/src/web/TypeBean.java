package web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import domain.catalogue.Type;
import service.ServiceException;
import service.ServiceLocal;

@ApplicationScoped
@ManagedBean
public class TypeBean implements Serializable{
	
	@EJB
	private ServiceLocal svc=null;
	
	private static final long serialVersionUID = 1L;
	List<Type> types;

    public TypeBean(){
    	
    }
    
    @PostConstruct
    public void init() {
    	try {
    		setTypes(svc.getAllTypes());
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

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}
}
