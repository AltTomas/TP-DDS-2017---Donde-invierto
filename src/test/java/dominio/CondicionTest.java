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

public class CondicionTest 
{
   
  Periodo periodo = null;   
  Indicador indicador = null;
  Condicion condicion = null;
  
  @Before
  public void cargaIndicador() 
  {	 	 
    BigDecimal valor = new BigDecimal(4500);
    Cuenta cuenta = new Cuenta("Ahorros", "2007-01-01", "2008-01-01", 4500);	
    Empresa empresa = new Empresa("Empresa Test");	
    condicion = new Condicion(cuenta, empresa, valor);
  }
  
  @Test
  public void testAplicarCondicion()
  {	
    
	Empresa empresa2 = new Empresa("Empresa2 Test");
	
	// Set cuenta.
    LocalDate fechaInicio = LocalDate.parse("2007-01-01");
	LocalDate fechaFin = LocalDate.parse("2008-01-01");
	Periodo periodo2 = new Periodo(fechaInicio, fechaFin);
	
    boolean aplicarCondicion = condicion.aplicarCondicion(empresa2, periodo2);
	
	assertFalse(aplicarCondicion);
  }
  
}