package mainApp;

import controllers.EmpresaController;
import controllers.indexController;
import services.EmpresaServices;


public class App {

  public static void main(String[] args) {
			
	 EmpresaServices emserv = new EmpresaServices();		
     emserv.createEmpresa("test");
	 emserv.createEmpresa("TEST2");
		 
	 new EmpresaController(emserv);		
	 new indexController();					
  }
	
}
