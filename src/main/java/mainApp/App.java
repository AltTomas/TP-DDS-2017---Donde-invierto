package mainApp;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import controllers.CuentaController;
import controllers.EmpresaController;
import controllers.indexController;
import dominio.Cuenta;
import services.EmpresaServices;
import spark.Spark;


public class App {

  public static void main(String[] args) {
				
	 
	 Spark.staticFiles.location("/public"); 
	
	 EmpresaServices emserv = new EmpresaServices();		
     emserv.createEmpresa("test");
	 emserv.createEmpresa("TEST2");
	 Cuenta cuenta = new Cuenta();
	 cuenta.setNombre("asd");
		 
	 new EmpresaController(emserv);		
	 new indexController();
	 new CuentaController(emserv);
  }
	
}
