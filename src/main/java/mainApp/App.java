package mainApp;

import spark.Spark;

import java.util.ArrayList;
import java.util.Arrays;

// Controllers.
import controllers.CuentaController;
import controllers.EmpresaController;
import controllers.IndicadorController;
import controllers.MetodologiaController;
import controllers.UploadController;
import controllers.indexController;

//Servicios.
import services.EmpresaServices;
import services.IndicadorServices;
import services.MetodologiaServices;

public class App 
{
   public static void main(String[] args) 
   {				 	 
  	   Spark.staticFiles.location("/public"); 
  	  
	   EmpresaServices emserv = new EmpresaServices();		
	   IndicadorServices inserv = new IndicadorServices();
	   MetodologiaServices mtserv = new MetodologiaServices();	  
  	   	    
	   new EmpresaController(emserv);			
	   new indexController();
	   new CuentaController(emserv);
	   new IndicadorController(inserv);
	   new MetodologiaController(mtserv);
	   new UploadController();
   }
}
