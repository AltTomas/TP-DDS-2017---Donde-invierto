package controllers;


import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.VelocityContext;
import dominio.Cuenta;
import dominio.Empresa;
import services.EmpresaServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.RenderUtil;

public class CuentaController{
	
	Map<String, String> model = new HashMap<String, String>();
	
	String layout = "templates/layoutLogged.vtl";
	
	public CuentaController(final EmpresaServices cuentaServices) {
		
				
		before("/cuenta/*", (req, res) ->{
			
			if(req.cookie("lgwapp.adb") == null) {
				res.redirect("/");
			}
			
			else {
							 
				 String user = req.cookie("lgwapp.adb");
				 
				 model.put("usuario", user);
				 
						 			 
			}
			
			
		});
		
		
		get("/cuenta/ingresar", (req,res) -> {
				
				
		   VelocityContext context = new VelocityContext();				 
           String result = new RenderUtil().getTempRes("templates/ingresarCuenta.vtl", context);
           model.put("template", result);
				
		   return new VelocityTemplateEngine().render(new ModelAndView(model, layout));				
       });


	   post("/cuenta/ingresar", (req,res) -> {
						   		   
		 String empresa = req.queryParams("empresa");
		 String nombre = req.queryParams("nombre");
		 int valor = new Integer(req.queryParams("valor"));
		 String periodo = req.queryParams("periodo");				 				 

		 System.out.println("...................");
		 System.out.println(empresa);
		 System.out.println(nombre);
		 System.out.println(valor);
		 System.out.println(periodo);		 
		 System.out.println("...................");
		 
		 Cuenta cuenta = new Cuenta(nombre, periodo, valor);
		 Empresa emp = new EmpresaServices().getEmpresa(empresa).get(0);
			 
		 cuentaServices.addCuenta(emp, cuenta);
			
		 res.status(201);
		 res.redirect("/cuenta/ingresar");
				 
		 return cuenta;
		
  	  });

	}
}
	