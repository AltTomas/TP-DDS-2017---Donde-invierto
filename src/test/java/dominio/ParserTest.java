package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import dominio.ParserTest.AnalizadorLexico;

// Helpers
import helpers.JSONLoader;
import helpers.FileToStringReader;
import helpers.FileToStringReader;

import java.util.ArrayList;

public class ParserTest 
{
    ArrayList<Indicador> indicadores = null;

    @Before
    public void cargaIndicadores() 
    {	
	   String filePath = "/home/dds/dds-utn/2017-mn-group-04/src/test/resources/indicadores.json";
	   FileToStringReader reader = new FileToStringReader();
	   JSONLoader loader = new JSONLoader(filePath, reader);
	   indicadores = loader.IndicadoresFromJSONFile();
    }
	
    @Test
	public void testPeriodoEstaComprendido()
    {
	   AnalizadorLexico Parser = new AnalizadorLexico();
	   boolean result = false;
	   
	   for(Indicador indicador : indicadores)
	   {
		 result = Parser.main(indicador.formula);
		 
		 if(!result)
			break;
	   }	
	   
	   assertTrue(result);
    }
}