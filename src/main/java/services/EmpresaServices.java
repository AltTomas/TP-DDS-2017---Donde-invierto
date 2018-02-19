package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Cuenta;
import dominio.Empresa;


public class EmpresaServices {
	
   private EntityManager em;
   
   public EmpresaServices() {	   
	  
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");			 	 
	  this.em = emf.createEntityManager();		
   }
	
   public Empresa createEmpresa(String nombre){
			
	   Empresa empresa = new Empresa(nombre.toUpperCase());				
				
	   em.getTransaction().begin();
	   em.persist(empresa);
	   em.flush();
	   em.getTransaction().commit();
		
	   return empresa;
	}
	
	public void addCuenta(String nombreEmpresa, Cuenta cuenta) {
		
	return;
	
	}
	
	public List<Empresa> getAllEmpresas() {
		
		String obtenerEmpresasGrabadas = "FROM Empresa";
		
		List<Empresa> empresas=	em.createQuery(obtenerEmpresasGrabadas, Empresa.class).getResultList();
			
		return empresas;
	}
	
	public Empresa getEmpresa(String nombreEmpresa) {
				
  	  String obtenerEmpresa = "FROM Empresa WHERE nombre = " + nombreEmpresa;
  	  List<Empresa> empresas= em.createQuery(obtenerEmpresa, Empresa.class).getResultList();	
		
  	  if(empresas.size() == 0)
  		  return null;
  	    	   	 
	  return empresas.get(0);				
	}
	
	// TODO: ???
	public void getCuentas() {
		return;
	}

}
