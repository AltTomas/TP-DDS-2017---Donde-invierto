package dominio;

import org.junit.Test;
import helpers.JSONLoader;

public class CargaArchivoTest {

  @Test
  public void testAssertTrue() {
	
	String filePath = "";
	JSONLoader loader = new JSONLoader(filePath);	  
	
	loader.GetCuentasAsJSONArray();	
  }
}