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
import services.MetodologiaServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.RenderUtil;




public class MetodologiaController {
	
	
	String layout = "templates/layoutLogged.vtl";
	Map<String, String> model = new HashMap<String, String>();
	
	public MetodologiaController(final MetodologiaServices metodologiaServices) {
	
	
		
		
	before("/metodologias/*", (req, res) ->{
		
		if(req.cookie("lgwapp.adb") == null) {
			res.redirect("/");
		}
		
		else {
						 
			 String user = req.cookie("lgwapp.adb");
			 
			 model.put("usuario", user);
			 
						 			 
		}
		
		
	});
	
get("/metodologias/buscar", (req, res)-> {
		
		 VelocityContext context = new VelocityContext();
		 
		 String result = new RenderUtil().getTempRes("templates/buscarMetodologia.vtl", context);
		  
		  
		  model.put("template", result);
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
	});
	
	get("/metodologias", (req, res) -> {
		
		
		 VelocityContext context = new VelocityContext();
		 		
		 String metodologia =  req.queryParams("metodologia");
		  String all = req.queryParams("all");
		  
		  if(all != null) {
			  if(all.equals("yes")) {
			 			  
				  context.put("empresaList", metodologiaServices.getAllMetodologia());
			  }
		  }
		  		else {
		  	if(metodologia != null) {
				
			  String emp = "asd";
			  
			   if(emp == null) {
				   context.put("metodologiasList", "error");
			   }
			   
			   else {
				   context.put("metodologiasList", emp);
			   }
			  
		  } 
		}
		 
		 String result = new RenderUtil().getTempRes("templates/metodologia.vtl", context);
		 
		  model.put("template", result);
		
		  return new VelocityTemplateEngine().render(
			        new ModelAndView(model, layout));
		});
	
	get("/metodologias/ingresar", (req,res) -> {
		
		
		
		 VelocityContext context = new VelocityContext();
		 
		 String result = new RenderUtil().getTempRes("templates/ingresarMetodologia.vtl", context);
		 
		  model.put("template", result);
		
		  return new VelocityTemplateEngine().render(
			        new ModelAndView(model, layout));
		
	});
	
	
	post("/metodologias/ingresar", (req,res) -> {
		
	 String metodologia = req.queryParams("metodologia");
		 
	 metodologiaServices.createMetodologia(metodologia);
		 
		 res.status(201);
		 res.redirect("/metodologias/ingresar");
		 
		 return metodologia;
		
	});
		
	get("/metodologias/:metodologia", (req, res) -> {
		
						
		 VelocityContext context = new VelocityContext();
			 
		 String metodologia = req.queryParams("metodologia");
		 
		  String emp = "asd";
			  
		  if(emp == null) {
			   context.put("metodologiasList", "error");
		   }
		   
		   else {
			   context.put("metodologiasList", emp);
		   }  
		  
		  
		 String result = new RenderUtil().getTempRes("templates/metodologias.vtl", context);
		  
		 		  
		  model.put("template", result);
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
		});
	
	
	
	
	}
}
