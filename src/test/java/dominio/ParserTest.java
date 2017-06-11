package dominio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import dominio.AnalizadorLexico;
import helpers.FileToStringReader;

public class ParserTest 
{        
  @Test
  public void testInstanciaAnalizadorLexico() throws ParseException, FileNotFoundException
  {
    String file = "C:/Projects/2017-mn-group-04/src/test/resources/indicador.txt";
	FileInputStream input = new FileInputStream(file);
	AnalizadorLexico analizador = new AnalizadorLexico();	
	//analizador.main(input);
  }  
}