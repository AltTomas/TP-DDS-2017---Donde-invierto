package test;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;

// Funciones de DateTime.
import java.time.LocalDate;

// Big Decimal
import java.math.BigDecimal;

import dominio.Periodo;
import dominio.Cuenta;
import dominio.Empresa;
import dominio.Metodologia;

public class MetodologiaTest 
{
  
  Metodologia metodologia = null;   
  
  @Before
  public void cargaMetodologia() 
  {	 	
    // Set cuenta. 
    Cuenta cuenta = new Cuenta("Ahorros", "2007-01-01", "2008-01-01", 4500);	
  	BigDecimal valor = new BigDecimal(4500);
	
    Empresa empresa = new Empresa("Empresa Test");	
	
	metodologia = new Metodologia("Metodologia 1");	
  }

  
  @Test
  public void testConvieneInvertir()
  {		    
	Empresa empresa = new Empresa("Empresa Test");

    LocalDate fechaInicio = LocalDate.parse("2007-01-01");
	LocalDate fechaFin = LocalDate.parse("2008-01-01");
	Periodo periodo = new Periodo(fechaInicio, fechaFin); 	 
  
	boolean conviene = metodologia.convieneInvertir(empresa, periodo);
	
	assertFalse(conviene);
  }
  
}