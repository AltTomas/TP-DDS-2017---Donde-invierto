package test;

import dominio.Periodo;
import dominio.Empresa;
import dominio.Indicador;
import dominio.Cuenta;

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
     Periodo periodo;
     
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
		  periodo = new Periodo(fechaInicio, fechaFin);		  		 		  	  		  		  
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
	  
	  
	  @Test
	  public void testPersisteIndicador() 
	  {				  
		  Indicador indicador = new Indicador("Indicador Test", periodo, "");	 			 
		  
		  String obtenerIndicadoresGrabados = "FROM Indicador";
  		  
		  em.getTransaction().begin();
		  em.persist(indicador);
		  em.flush();
		  em.getTransaction().commit();

		  List<Indicador> indicadores = em.createQuery(obtenerIndicadoresGrabados, Indicador.class).getResultList();		  
		  Assert.assertTrue(indicadores.size() > 0);		  
	  }
	  
	  
	  @Test
	  public void testPersisteCuenta() 
	  {				  
		  Cuenta cuenta = new Cuenta("Cuenta 1", "2007-01-01", "2007-01-02", 1); 			 
		  
		  String obtenerCuentasGrabadas = "FROM Cuenta";
  		  
		  em.getTransaction().begin();
		  em.persist(cuenta);
		  em.flush();
		  em.getTransaction().commit();

		  List<Cuenta> cuentas = em.createQuery(obtenerCuentasGrabadas, Cuenta.class).getResultList();		  
		  Assert.assertTrue(cuentas.size() > 0);		  
	  }
	  

	  // TODO: el test elimina los periodos pero no considera que hacer cuando una empresa o un indicador ya tienen un periodo.
	  @After
	  public void limpiar() 
	  {
		  // Eliminar indicador
		  String obtenerIndicadoresGrabados = "FROM Indicador";
		  List<Indicador> indicadores = em.createQuery(obtenerIndicadoresGrabados, Indicador.class).getResultList();		  

		  if(indicadores.size() > 0)
		  {
			  Indicador indicadorElem = (Indicador) indicadores.get(0);
			  em.getTransaction().begin();
			  em.remove(indicadorElem);
			  em.getTransaction().commit();  
		  }
		  
		  // Eliminar empresa
		  String obtenerEmpresasGrabadas = "FROM Empresa";
		  List<Empresa> empresas = em.createQuery(obtenerEmpresasGrabadas, Empresa.class).getResultList();		  

		  if(empresas.size() > 0)
		  {
			  Empresa empresaElem = (Empresa) empresas.get(0);
			  em.getTransaction().begin();
			  em.remove(empresaElem);
			  em.getTransaction().commit();  
		  }
		  
		  // Eliminar periodo creado.
		  String obtenerPeriodoGrabado = "FROM Periodo WHERE fechaInicio='2007-01-01' AND fechaFin='2008-01-01'";		  
		  List<Periodo> periodoList = em.createQuery(obtenerPeriodoGrabado, Periodo.class).getResultList();
		  		  
		  if(periodoList.size() > 0)
		  {			
			  Periodo periodoElem = (Periodo) periodoList.get(0);
			  em.getTransaction().begin();
			  em.remove(periodoElem);
			  em.getTransaction().commit();  			  
		  }
		  
		  
		  // Eliminar cuenta creada.
		  String obtenerCuentaGrabada = "FROM Cuenta";		  
		  List<Cuenta> cuentaList = em.createQuery(obtenerCuentaGrabada, Cuenta.class).getResultList();	
  		  
		  if(cuentaList.size() > 0)
		  {			
			  Cuenta cuentaElem = (Cuenta) cuentaList.get(0);
			  em.getTransaction().begin();
			  em.remove(cuentaElem);
			  em.getTransaction().commit();  			  
		  }
			  
	  }
}