package dominio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.lang.*;

// JUnit
import org.junit.Test;
import org.junit.Rule;
import org.junit.Before;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import parser.AnalizadorLexico;
import parser.ParseException;
import helpers.FileToStringReader;

public class ParserTest 
{        

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testInstanciaAnalizadorLexicoArchivo() throws ParseException, FileNotFoundException
  {
    String file = "src/test/resources/pruebaParser.json";
    AnalizadorLexico parser = null;
    boolean leer;
	
    try
	{
       parser = new AnalizadorLexico(new FileInputStream(file));
    }
	catch(FileNotFoundException e)
	{
   	   System.out.println("El archivo " + file + " no se econtro.");
	   return ; 
    }
   
    while(leer=parser.Programa());	  
    assertFalse(parser.Programa());    
  }

  @Test
  public void testInstanciaAnalizadorLexicoCadena() 
		throws ParseException, FileNotFoundException
  {
    String cadena1 = "\""+"formula\""+": "+"\"Operacion Neta+Capital\"";
	Reader reader1 = new StringReader(cadena1);    
	AnalizadorLexico parser=null;
    System.out.println("Cadena Ingresada" + cadena1);

 	if(parser == null) 
	   parser = new AnalizadorLexico(reader1);
	else 
	   parser.ReInit(reader1);
	
    assertTrue(parser.Programa());	      
  }

  @Test(expected = ParseException.class)
  public void testInstanciaAnalizadorLexicoCadenaFalse() 
     throws ParseException, FileNotFoundException
  {
     String cadena2 = "formula = a+b";    
     Reader reader2 = new StringReader(cadena2);    
  	 AnalizadorLexico parser=null;

 	 System.out.println("Cadena Ingresada" + cadena2);
 	 
	 if( parser == null ) 
 			parser = new AnalizadorLexico(reader2);
 	 else 
 		parser.ReInit(reader2);
      
	 parser.Programa();
  }
}