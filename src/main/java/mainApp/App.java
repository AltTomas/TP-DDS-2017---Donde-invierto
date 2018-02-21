package mainApp;

import controllers.CuentaController;
import controllers.EmpresaController;
import controllers.IndicadorController;
import controllers.MetodologiaController;
import controllers.indexController;
import services.EmpresaServices;
import services.IndicadorServices;
import services.MetodologiaServices;
import spark.Spark;


public class App {

  public static void main(String[] args) {
			
	 	 
		 Spark.staticFiles.location("/public"); 
	
		 EmpresaServices emserv = new EmpresaServices();		
		 IndicadorServices inserv = new IndicadorServices();
		 MetodologiaServices mtserv = new MetodologiaServices();
		 
		 new EmpresaController(emserv);			
		 new indexController();
		 new CuentaController(emserv);
		 new IndicadorController(inserv);
		 new MetodologiaController(mtserv);
	 
  }
	
}
