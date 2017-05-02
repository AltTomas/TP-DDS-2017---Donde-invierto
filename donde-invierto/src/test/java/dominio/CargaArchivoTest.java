package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

import helpers.JSONLoader;
import java.util.ArrayList;
import java.util.List;

public class CargaArchivoTest {

  private ArrayList<Empresa> empresas = null;
  
  @Before
  public void cargaArchivo() 
  {		
	String filePath = "src/test/resources/cuentas.json";		
	JSONLoader loader = new JSONLoader(filePath);	  	
	
	empresas = loader.GetEmpresasFromJSONArray();
  }
  
  @Test
  public void testHayEmpresas()
  {
	  assertTrue(empresas.size() > 0);
  }
  

  @Test
  public void testEmpresaConCuentas() 
  {
	  List<ICalculable> cuentas = null;
	  
	  String empresaBuscada = "empresa1";
	  
	  for (Empresa empresa : empresas)
	  {
		  String nombreEmpresa = empresa.getNombre();
		  if (nombreEmpresa.equals(empresaBuscada))
		  {
			  cuentas = empresa.getCuentas();
			  
		  }
	  }
	  
	  assertTrue(cuentas.size() > 0);
  }
  
}