package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Indicador;

public class IndicadorServices {
	
   private EntityManager em;
	   
   public IndicadorServices() {	   		 
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");			 	 
	  this.em = emf.createEntityManager();		
	}
		
	public Indicador createIndicador(String nombre, String formula){
					
       // TODO: completar período y fórmula.
	   Indicador indicador = new Indicador(nombre.toUpperCase(), formula);	 			 
	   
	   em.getTransaction().begin();
	   em.persist(indicador);		  
	   em.flush();
	   em.getTransaction().commit();
		
	   return indicador;
	}
	
	public List<Indicador> getAllIndicadores() {		
		String obtenerIndicadoresGrabados = "FROM Indicador";		
		List<Indicador> indicadores = em.createQuery(obtenerIndicadoresGrabados, Indicador.class).getResultList();			
		return indicadores;
	}
		
	public List<Indicador> getIndicador(String nombre) {
		
		String obtenerIndicador = "FROM Indicador WHERE nombre = " + "'" + nombre + "'";
	  	List<Indicador> indicadores = em.createQuery(obtenerIndicador, Indicador.class).getResultList();	
			
	  	if(indicadores.isEmpty()) {
	  		return null;
	  	}
		return indicadores;		
	}
	
}
