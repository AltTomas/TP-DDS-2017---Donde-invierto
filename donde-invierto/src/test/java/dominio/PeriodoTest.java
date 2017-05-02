package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

// Funciones de DateTime.
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PeriodoTest 
{
  
  Periodo periodo1 = null;
  Periodo periodo2 = null;
  Periodo periodo3 = null;  
  
  @Before
  public void cargaPeriodos() 
  {	
     LocalDate fechaInicio = LocalDate.parse("2007-01-01");
	 LocalDate fechaFin = LocalDate.parse("2008-01-01");
	 periodo1 = new Periodo(fechaInicio, fechaFin);
	 
     LocalDate fechaInicio2 = LocalDate.parse("2006-01-01");
	 LocalDate fechaFin2 = LocalDate.parse("2009-01-01");
	 periodo2 = new Periodo(fechaInicio2, fechaFin2);
	 	 
	 LocalDate fechaInicio3 = LocalDate.parse("2010-01-01");
	 LocalDate fechaFin3 = LocalDate.parse("2014-01-01");
	 periodo3 = new Periodo(fechaInicio3, fechaFin3);	 	
  }
  
  @Test
  public void testPeriodoEstaComprendido()
  {
	assertTrue(periodo1.estaComprendidoEntre(periodo2));
  }
  

  @Test
  public void testPeriodoNoEstaComprendido() 
  {	 
	assertFalse(periodo1.estaComprendidoEntre(periodo3));
  }
  
}