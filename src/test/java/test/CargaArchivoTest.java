package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
// JUnit
import org.junit.Test;

import dominio.Cuenta;
import dominio.Empresa;
import util.FileToStringReader;
import util.JSONLoader;

public class CargaArchivoTest {

  private ArrayList<Empresa> empresas = null;
  
  @Before
  public void cargaArchivo() 
  {		
	String filePath = "src/test/resources/cuentas.json";		
	FileToStringReader reader = new FileToStringReader();	
	JSONLoader loader = new JSONLoader(filePath, reader);	  	
	
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
	  List<Cuenta> cuentas = null;
	  
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