package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Cuenta;
import dominio.Empresa;


public class EmpresaServices {
	
	 public EntityManager em() 
	 {	 	 
		 EntityManager em = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");
		em = emf.createEntityManager();
		
		return em;
	 }
	
	public Empresa createEmpresa(String nombre){
			
		Empresa empresa = new Empresa(nombre.toUpperCase());
		
		//em().getTransaction().begin();
		//em().persist(empresa);
		
		return empresa;
	}
	
	public void addCuenta(String nombreEmpresa, Cuenta cuenta) {
		
	return;
	
	}
	
	public List<Empresa> getAllEmpresas() {
		
		String obtenerEmpresasGrabadas = "FROM Empresa";
		
		List<Empresa> empresas=	em().createQuery(obtenerEmpresasGrabadas, Empresa.class).getResultList();
			
		return empresas;
	}
	
	public void getEmpresa(String nombre) {
		
		return;
		
	}
	public void getCuentas(String nombreEmpresa){
				
		return;
		
		
	}

}
