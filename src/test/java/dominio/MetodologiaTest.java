package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;

// Funciones de DateTime.
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Big Decimal
import java.math.BigDecimal;

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
    Condicion condicion = new Condicion(cuenta, empresa, valor);
	
	metodologia = new Metodologia(condicion);	
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