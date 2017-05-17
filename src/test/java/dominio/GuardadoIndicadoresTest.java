package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

// Funciones de DateTime.
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Helpers
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


  @Test
  public void testGuardarEnArchivo()
  {		
    String filePath = "src/test/resources/indicadores.json";
	FileToStringReader reader = new FileToStringReader();
	JSONLoader loader = new JSONLoader(filePath, reader);
	
	boolean saved = loader.IndicadoresToJSONFile(this.indicadores);
	
	assertTrue(saved);
  }
  
  
  @Test
  public void testRecuperarIndicadores()
  {		
    String filePath = "src/test/resources/indicadores2.json";
	FileToStringReader reader = new FileToStringReader();
	JSONLoader loader = new JSONLoader(filePath, reader);	
	boolean saved = loader.IndicadoresToJSONFile(this.indicadores);

    ArrayList<Indicador> indicadores = loader.IndicadoresFromJSONFile();
  }
  
}