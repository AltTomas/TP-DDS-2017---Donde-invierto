package test;

import dominio.Periodo;
import dominio.Empresa;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

public class PersistenciaTest
{	
     EntityManager em = null;		 
	 	 
	 @Before
	 public void cargaDatos() 
	 {	 	 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ddstp");
		em = emf.createEntityManager();
	 }
	  
	  @Test
	  public void testPersistePeriodo()
	  {	  
		  // Persistir período.	  
		  LocalDate fechaInicio = LocalDate.parse("2007-01-01");
		  LocalDate fechaFin = LocalDate.parse("2008-01-01");
		  Periodo periodo = new Periodo(fechaInicio, fechaFin);		  		 		  	  		  		  
		  String obtenerPeriodoGrabado = "FROM Periodo WHERE fechaInicio='2007-01-01' AND fechaFin='2008-01-01'";		  
		  
		  em.getTransaction().begin();
		  em.persist(periodo);
		  em.flush();
		  em.getTransaction().commit();

		  List<Periodo> periodos = em.createQuery(obtenerPeriodoGrabado, Periodo.class).getResultList();		  
		  Assert.assertTrue(periodos.size() > 0);
	  }
	  
	  @Test
	  public void testPersisteEmpresa() 
	  {
		  Empresa empresa = new Empresa("Umbrella Corporation");
		  
		  String obtenerEmpresasGrabadas = "FROM Empresa";
  		  
		  em.getTransaction().begin();
		  em.persist(empresa);
		  em.flush();
		  em.getTransaction().commit();

		  List<Empresa> empresas = em.createQuery(obtenerEmpresasGrabadas, Empresa.class).getResultList();		  
		  Assert.assertTrue(empresas.size() > 0);
		  
	  }
	  
	  @After
	  public void limpiar() 
	  {		  
		  String obtenerPeriodoGrabado = "FROM Periodo WHERE fechaInicio='2007-01-01' AND fechaFin='2008-01-01'";		  
		  List<Periodo> periodoList = em.createQuery(obtenerPeriodoGrabado, Periodo.class).getResultList();
		  
		  if(periodoList.size() > 0)
		  {			  						  			  			  
			  Periodo periodoElem = (Periodo) periodoList.get(0);
			  em.getTransaction().begin();
			  em.remove(periodoElem);
			  em.getTransaction().commit();  			  
		  }
			  
	  }
}