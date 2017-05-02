package dominio;

// JUnit
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import helpers.JSONLoader;
import java.util.ArrayList;

public class CargaArchivoTest {

  @Test
  public void testCargaArchivo() {
	
	String filePath = "src/test/resources/cuentas.json";
	JSONLoader loader = new JSONLoader(filePath);	  
	
	ArrayList<Empresa> empresas = loader.GetEmpresasFromJSONArray();	
	
	// El array tiene más de una empresa.
	assertTrue(empresas.size() > 0);	
  }
}