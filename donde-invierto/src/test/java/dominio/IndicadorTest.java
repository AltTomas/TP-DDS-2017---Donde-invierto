package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

// Funciones de DateTime.
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Big Decimal
import java.math.BigDecimal;

public class IndicadorTest 
{
   
  Periodo periodo = null;   
  Indicador indicador = null;
  
  @Before
  public void cargaIndicador() 
  {	
 
     // Set cuenta.
     LocalDate fechaInicio = LocalDate.parse("2007-01-01");
	 LocalDate fechaFin = LocalDate.parse("2008-01-01");
	 periodo = new Periodo(fechaInicio, fechaFin);  
	 
	 indicador = new Indicador("Indicador Test", periodo);
  }

  // TODO: el calculo en el futuro debería tener sentido y no ser cero.
  @Test
  public void testCalcular()
  {	
	BigDecimal valor = indicador.calcular(periodo);
	assertEquals(valor, BigDecimal.ZERO);
  }
  
}