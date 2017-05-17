package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

// Funciones de DateTime.
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import helpers.JSONLoader;
import helpers.FileToStringReader;

import helpers.FileToStringReader;

public class GuardadoIndicadoresTest 
{
     
  Indicador [] indicadores = new Indicador [2];
  
  @Before
  public void cargaIndicadores() 
  {	
     Periodo periodo, periodo2;
 
     // Set cuenta.
     LocalDate fechaInicio = LocalDate.parse("2007-01-01");
	 LocalDate fechaFin = LocalDate.parse("2008-01-01");
	 periodo = new Periodo(fechaInicio, fechaFin);  
	 
	 LocalDate fechaInicio2 = LocalDate.parse("2009-01-01");
	 LocalDate fechaFin2 = LocalDate.parse("2010-01-01");
	 periodo2 = new Periodo(fechaInicio2, fechaFin2);  
	 
	 indicadores[0] = new Indicador("Indicador Test", periodo);
	 indicadores[1] = new Indicador("Indicador Test2", periodo2);	 
  }

  // TODO: el calculo en el futuro deberia tener sentido y no ser cero.
  @Test
  public void testGuardarEnArchivo()
  {		
    String filePath = "src/test/resources/indicadores.json";
	FileToStringReader reader = new FileToStringReader();
	JSONLoader loader = new JSONLoader(filePath, reader);
	
	boolean saved = loader.IndicadoresToJSONFile(this.indicadores);
	
	assertTrue(saved);
  }
  
}