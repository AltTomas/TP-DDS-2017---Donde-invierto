package services;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Cuenta;
import dominio.Empresa;


public class IndicadorServices {
	
	 public EntityManager em() 
	 {	 	 
		 EntityManager em = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");
		em = emf.createEntityManager();
		
		return em;
	 }
	
	public Empresa createIndicador(String nombre){
			
		Empresa empresa = new Empresa(nombre.toUpperCase());
		
		em().getTransaction().begin();
		em().persist(empresa);
		
		return empresa;
	}
	
	public List<Empresa> getAllIndicadores() {
		
		String obtenerEmpresasGrabadas = "FROM Empresa";
		
		List<Empresa> empresas=	em().createQuery(obtenerEmpresasGrabadas, Empresa.class).getResultList();
			
		return empresas;
	}
	
	public void getIndicador(String nombre) {
		
		return;
		
	}
	

}
