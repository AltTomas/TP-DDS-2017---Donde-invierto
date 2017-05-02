package dominio;

import org.junit.Test;
import helpers.JSONLoader;

public class CargaArchivoTest {

  @Test
  public void testReadJsonFile() {
	
	String filePath = "src/test/resources/cuentas.json";
	JSONLoader loader = new JSONLoader(filePath);	  
	
	loader.GetCuentasAsJSONArray();	
	
	// TODO: agregar algun assert que justifique tener este test.
  }
}