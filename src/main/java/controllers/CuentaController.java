package controllers;


import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.VelocityContext;
import dominio.Cuenta;
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
				
				  return new VelocityTemplateEngine().render(
					        new ModelAndView(model, layout));
				
			});


			post("/cuenta/ingresar", (req,res) -> {
				
			 String empresa = req.queryParams("empresa");
			 String nombre = req.queryParams("nombre");
			 int valor = new Integer(req.queryParams("valor"));
			 String cuentaFechaInicio = req.queryParams("fechaInicio");
			 String cuentaFechaFin = req.queryParams("fechaFin");
				 				 
			 Cuenta cuenta = new Cuenta(nombre, cuentaFechaInicio, cuentaFechaFin, valor);
			 
			cuentaServices.addCuenta(empresa, cuenta);
			
				 res.status(201);
				 res.redirect("/ingresar/cuenta");
				 
				 return cuenta;
				
			});

	}
}
	