package services;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Metodologia;

public class MetodologiaServices {
	
    private EntityManager em;
	   
	public MetodologiaServices() {	   		 
	   EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");			 	 
	   this.em = emf.createEntityManager();		
	}			
	
	public Metodologia createMetodologia(String nombre){
			
  	    Metodologia metodologia = new Metodologia(nombre.toUpperCase());
		
     	em.getTransaction().begin();
		em.persist(metodologia);
		em.flush();
		em.getTransaction().commit();
  	    
		return metodologia;
	}
	
	public List<Metodologia> getAllMetodologia() {
		
		String obtenerEmpresasGrabadas = "FROM Metodologia";		
		List<Metodologia> metodologias=	em.createQuery(obtenerEmpresasGrabadas, Metodologia.class).getResultList();
		
		return metodologias;
	}
	
	public void getMetodologia(String nombre) {		
		return;		
	}
	
}
