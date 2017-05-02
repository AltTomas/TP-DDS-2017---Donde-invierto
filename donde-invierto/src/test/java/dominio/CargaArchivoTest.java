package dominio;

import org.junit.Test;
import helpers.JSONLoader;
import java.util.ArrayList;

public class CargaArchivoTest {

  @Test
  public void testCargaArchivo() {
	
	String filePath = "src/test/resources/cuentas.json";
	JSONLoader loader = new JSONLoader(filePath);	  
	
	ArrayList<Empresa> empresas = loader.GetEmpresasFromJSONArray();	
	
	
	// TODO: agregar algun assert que justifique tener este test.
  }
}