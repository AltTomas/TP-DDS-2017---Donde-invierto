package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Empresa;
import dominio.Indicador;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

public class IndicadorServices {
	
   private EntityManager em;
   private Gson gson;

   public IndicadorServices() {	   		 
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");			 	 
	  this.em = emf.createEntityManager();		
	  this.gson = new Gson();
	}
		
	public Indicador createIndicador(String nombre, String formula){
					
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
		
		// Lista de empresas y empresa individual.
		List<Indicador> indicadores;
		Indicador indicadorEncontrado; 
						
	    // Redis
        Jedis jedis = new Jedis("localhost");		
	    String value = jedis.get(nombre);	    
		
	    if(value != null)
		{
			//
	    	indicadores = new ArrayList<Indicador>();
			indicadorEncontrado = this.gson.fromJson(value, Indicador.class);
			indicadores.add(indicadorEncontrado);
			
			return indicadores;
		}
	    
		String obtenerIndicador = "FROM Indicador WHERE nombre = " + "'" + nombre + "'";
	  	indicadores = em.createQuery(obtenerIndicador, Indicador.class).getResultList();	
			
	  	if(indicadores.isEmpty()) {
	  		return null;
	  	}
	  	
	  	 // Indicador encontrada. Ponerlo en la cache de redis.
	  	indicadorEncontrado = indicadores.get(0);
  	    String valorAGuardar = this.gson.toJson(indicadorEncontrado);
  	    jedis.set(indicadorEncontrado.getNombre(), valorAGuardar);
	  	
		return indicadores;		
	}
	
}
