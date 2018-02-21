package controllers;


import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.velocity.VelocityContext;
import dominio.Metodologia;
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
			 			  
				  context.put("metodologiaList", metodologiaServices.getAllMetodologia());
			  }
		  }
		  		else {
		  	if(metodologia != null) {
				
			  List<Metodologia> emp = metodologiaServices.getMetodologia(metodologia);
			  
			   if(emp == null) {
				   context.put("metodologiaList", "error");
			   }
			   
			   else {
				   context.put("metodologiaList", emp);
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
	 String formula = req.queryParams("formula");
	 
	 metodologiaServices.createMetodologia(metodologia, formula);
		 
		 res.status(201);
		 res.redirect("/metodologias/ingresar");
		 
		 return metodologia;
		
	});
		
	get("/metodologias/:metodologia", (req, res) -> {
		
						
		 VelocityContext context = new VelocityContext();
			 
		 String metodologia = req.queryParams("metodologia");
		 
		 List<Metodologia> emp = metodologiaServices.getMetodologia(metodologia);
			  
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
