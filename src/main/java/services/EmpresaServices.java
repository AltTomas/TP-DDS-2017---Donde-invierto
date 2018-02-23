package services;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Cuenta;
import dominio.Empresa;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

public class EmpresaServices {
	
   private EntityManager em;   
   private Gson gson;
   
   public EmpresaServices() {	   	  
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");			 	 
	  this.em = emf.createEntityManager();	
	  this.gson = new Gson();
	  
   }
	
   public Empresa createEmpresa(String nombre)
   {			
	   Empresa empresa = new Empresa(nombre.toUpperCase());				
		   
	   em.getTransaction().begin();
	   em.persist(empresa);
	   em.flush();
	   em.getTransaction().commit();
	   		   
	   return empresa;
	}
	

    public Cuenta addCuenta(Empresa empresa, Cuenta cuenta) 

    {	    	                      	 		
    	// Persistir cuenta.
    	em.getTransaction().begin();
		em.persist(cuenta);

        		
		// Agregar cuenta a empresa.
        empresa.agregarCuenta(cuenta);
               
    	em.flush();
		em.getTransaction().commit();
		
		return cuenta;
	}
	
	public List<Empresa> getAllEmpresas() 
	{		
		String obtenerEmpresasGrabadas = "FROM Empresa";
		List<Empresa> empresas=	em.createQuery(obtenerEmpresasGrabadas, Empresa.class).getResultList();
		
		return empresas;
	}
	

	public List<Empresa> getEmpresa(String nombre) 
	{		
		// Lista de empresas y empresa individual.
		List<Empresa> empresas;
		Empresa empresaEncontrada; 
				
		//
		Jedis jedis = new Jedis("localhost");		
		String value = jedis.get(nombre);	    
	    
		if(value != null)
		{
			//
			empresas = new ArrayList<Empresa>();
			empresaEncontrada = this.gson.fromJson(value, Empresa.class);
			empresas.add(empresaEncontrada);
			
			return empresas;
		}
				
  	    String obtenerEmpresa = "FROM Empresa WHERE nombre = " +  "'" + nombre + "'";
  	    empresas = em.createQuery(obtenerEmpresa, Empresa.class).getResultList();	
		
  	    if(empresas.isEmpty())
  		   return null;
  	  
  	    // Empresa encontrada. Ponerla en la cache de redis.
  	   // empresaEncontrada = empresas.get(0);
  	   // String valorAGuardar = gson.toJson(empresaEncontrada);
  	   // jedis.set(empresaEncontrada.getNombre(), valorAGuardar);
  	    
	  return empresas;
	}
	
}
