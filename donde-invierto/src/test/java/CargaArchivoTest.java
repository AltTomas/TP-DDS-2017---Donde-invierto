package dominio;

import org.junit.Test;
import helpers.JSONLoader;

public class CargaArchivoTest {

  @Test
  public void testAssertTrue() {
	
	String filePath = "C:/Users/Rulo/Desktop/2017-mn-group-04-develop/donde-invierto/src/test/resources/cuentas.json";
	JSONLoader loader = new JSONLoader(filePath);	  
	
	loader.GetCuentasAsJSONArray();	
  }
}