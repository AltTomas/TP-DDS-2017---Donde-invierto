package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.Gson;

import dominio.Empresa;
import dominio.Indicador;
import dominio.Metodologia;
import redis.clients.jedis.Jedis;

public class MetodologiaServices {
	
    private EntityManager em;
    private Gson gson;

	public MetodologiaServices() {	   		 
	   EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");			 	 
	   this.em = emf.createEntityManager();	
	   this.gson = new Gson();
	}			
	
	public Metodologia createMetodologia(String nombre, String formula){
			
  	    Metodologia metodologia = new Metodologia(nombre.toUpperCase(), formula);
		
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
	
	public List<Metodologia> getMetodologia(String nombre) {
		
		// Lista de empresas y empresa individual.
		List<Metodologia> metodologias;
		Metodologia metodologiaEncontrada; 
						
		//
		Jedis jedis = new Jedis("localhost");		
		String value = jedis.get(nombre);
		
	    if(value != null)
		{
			//
	    	metodologias = new ArrayList<Metodologia>();
	    	metodologiaEncontrada = this.gson.fromJson(value, Metodologia.class);
			metodologias.add(metodologiaEncontrada);
			
			return metodologias;
		}
			    		
		String obtenerMetodologia = "FROM Metodologia WHERE nombre = " + "'" + nombre + "'";
	  	metodologias = em.createQuery(obtenerMetodologia, Metodologia.class).getResultList();	
			  	    	   	 
	  	if(metodologias.isEmpty()) {
	  		return null;
	  	}
	  	
	  	 // Indicador encontrada. Ponerlo en la cache de redis.
	  	metodologiaEncontrada = metodologias.get(0);
 	    String valorAGuardar = this.gson.toJson(metodologiaEncontrada);
 	    jedis.set(metodologiaEncontrada.getNombre(), valorAGuardar);
	  	
		return metodologias;				
	}
	
}
