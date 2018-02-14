package controllers;


import static spark.Spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import dominio.Empresa;
import services.EmpresaServices;
import services.IndicadorServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.RenderUtil;




public class IndicadorController {
	
	
	String layout = "templates/layoutLogged.vtl";
	Map<String, String> model = new HashMap<String, String>();
	
	public IndicadorController(final IndicadorServices indicadorServices) {
	
	
		
		
	before("/indicadores/*", (req, res) ->{
		
		if(req.cookie("lgwapp.adb") == null) {
			res.redirect("/");
		}
		
		else {
						 
			 String user = req.cookie("lgwapp.adb");
			 
			 model.put("usuario", user);
			 
						 			 
		}
		
		
	});
	
get("/indicador/buscar", (req, res)-> {
		
		 VelocityContext context = new VelocityContext();
		 
		 String result = new RenderUtil().getTempRes("templates/buscarMetodologia.vtl", context);
		  
		  
		  model.put("template", result);
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
	});
	
	get("/indocadores", (req, res) -> {
		
		
		 VelocityContext context = new VelocityContext();
		 		
		 String indicador =  req.queryParams("indicador");
		  String all = req.queryParams("all");
		  
		  if(all != null) {
			  if(all.equals("yes")) {
			 			  
				  context.put("empresaList", indicadorServices.getAllIndicadores());
			  }
		  }
		  		else {
		  	if(indicador != null) {
				
			  String emp = "asd";
			  
			   if(emp == null) {
				   context.put("indicadoresList", "error");
			   }
			   
			   else {
				   context.put("indicadoresList", emp);
			   }
			  
		  } 
		}
		 
		 String result = new RenderUtil().getTempRes("templates/indicador.vtl", context);
		 
		  model.put("template", result);
		
		  return new VelocityTemplateEngine().render(
			        new ModelAndView(model, layout));
		});
	
	get("/indicadores/ingresar", (req,res) -> {
		
		
		
		 VelocityContext context = new VelocityContext();
		 
		 String result = new RenderUtil().getTempRes("templates/ingresarIndicador.vtl", context);
		 
		  model.put("template", result);
		
		  return new VelocityTemplateEngine().render(
			        new ModelAndView(model, layout));
		
	});
	
	
	post("/indicadores/ingresar", (req,res) -> {
		
	 String indicador = req.queryParams("indicador");
		 
		 indicadorServices.createIndicador(indicador);
		 
		 res.status(201);
		 res.redirect("/empresas/ingresar");
		 
		 return indicador;
		
	});
		
	get("/indicadores/:indicador", (req, res) -> {
		
						
		 VelocityContext context = new VelocityContext();
			 
		 String indicador = req.queryParams("indicador");
		 
		  String emp = "asd";
			  
		  if(emp == null) {
			   context.put("indicadoresList", "error");
		   }
		   
		   else {
			   context.put("indicadoresList", emp);
		   }  
		  
		  
		 String result = new RenderUtil().getTempRes("templates/indicador.vtl", context);
		  
		 		  
		  model.put("template", result);
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
		});
	
	
	
	
	}
}
