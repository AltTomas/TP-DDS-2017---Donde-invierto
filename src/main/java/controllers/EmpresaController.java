package controllers;


import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.ErrorManager;

import dominio.Empresa;
import services.EmpresaServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;



public class EmpresaController {
	
	public EmpresaController(final EmpresaServices empresaService) {
	
	String layout = "templates/layout.vtl";
		
	get("/empresas/empresa", (req, res) -> {
		 Map<String, String> model = new HashMap<String, String>();
		 model.put("template", "templates/empresa.vtl" );
		 
		  String empresa =  req.queryParams("empresa").toUpperCase();
		  
		  Empresa empresaResponse = empresaService.getEmpresa(empresa);
		  
		  model.put("empresa", empresaResponse.getNombre());
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
		});
	
	get("/empresas/buscarEmpresa", (req,res) -> {
		
		 Map<String, String> model = new HashMap<String, String>();
	      model.put("template", "templates/buscarEmpresa.vtl" );
	      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
		
	});

	}
}
