package controllers;


import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
		   
		   context.put("empresaList", cuentaServices.getAllEmpresas());

           String result = new RenderUtil().getTempRes("templates/ingresarCuenta.vtl", context);
           model.put("template", result);
				
		   return new VelocityTemplateEngine().render(new ModelAndView(model, layout));				
       });


	   post("/cuenta/ingresar", (req,res) -> {
		
		   VelocityContext context = new VelocityContext();	
		 String empresa = req.queryParams("empresa");
		 String nombre = req.queryParams("nombre");
		 int valor = new Integer(req.queryParams("valor"));
		 String periodo = req.queryParams("periodo");				 				 
		 
		 Cuenta cuenta = new Cuenta(nombre.toUpperCase(), periodo, valor);

		 Empresa emp = new EmpresaServices().getEmpresa(empresa).get(0);
			 
		 
			 for (int i = 0; i < emp.getCuentas().size(); i++) {
				
				 if(emp.getCuentas().get(i).getNombre().equals(nombre.toUpperCase()) & emp.getCuentas().get(i).getPeriodo().equals(periodo)) {
					  context.put("empresa", StringUtils.capitalize(empresa.toLowerCase()));
					   context.put("cuenta", StringUtils.capitalize(nombre.toLowerCase()));
					   context.put("periodo", periodo);
					   context.put("existe", true);
					   
			       String result = new RenderUtil().getTempRes("templates/ingresarCuentaOk.vtl", context);
			       model.put("template", result);
							
					   return new VelocityTemplateEngine().render(new ModelAndView(model, layout));	
				 }
				 
			}
		 cuentaServices.addCuenta(emp, cuenta);
		 
		 context.put("empresa", StringUtils.capitalize(empresa.toLowerCase()));
		   context.put("cuenta", StringUtils.capitalize(nombre.toLowerCase()));
		   context.put("periodo", periodo);
		   context.put("creado", true);
			
		 res.status(201);
				   
		   context.put("empresaList", cuentaServices.getAllEmpresas());

		   String result = new RenderUtil().getTempRes("templates/ingresarCuentaOk.vtl", context);
		   model.put("template", result);
				
		   return new VelocityTemplateEngine().render(new ModelAndView(model, layout));	
		
		 
		
  	  });

	}
}
	