package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Cuenta;
import dominio.Empresa;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class EmpresaServices {
	
   private EntityManager em;
   
   public EmpresaServices() {	   
	  
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");			 	 
	  this.em = emf.createEntityManager();		
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
		
		/*
		Config config = new Config();
		config.useSingleServer().setAddress("127.0.0.1:6379");		 		
	    RedissonClient client = Redisson.create(config);
		*/
		
  	    String obtenerEmpresa = "FROM Empresa WHERE nombre = " +  "'" + nombre + "'";
  	    List<Empresa> empresas= em.createQuery(obtenerEmpresa, Empresa.class).getResultList();	
		
  	    if(empresas.isEmpty())
  		   return null;
  	    	   	 
	  return empresas;
	}
	
}
