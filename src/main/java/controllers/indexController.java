package controllers;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.RenderUtil;


public class indexController {

  public indexController() {
		
	String layout = "templates/layout.vtl";	
	String layoutLogged = "templates/layoutLogged.vtl";
	
	
	
	  get("/", (req, res) -> {
		
		  Map<String, String> model = new HashMap<String, String>();
		  
		  VelocityContext context = new VelocityContext();
		  
		  String cookie = req.cookie("lgwapp.adb");
		  
		  if(cookie == null){
			  
			  String result = new RenderUtil().getTempRes("templates/index.vtl", context);
				 
			  model.put("template", result);
		      
		      return new VelocityTemplateEngine().render(
		        new ModelAndView(model, layout));
			  
		  }
		  
		   res.redirect("/welcome");
	        
	        return null;
	       
	  });
	  
	  post("/", (req,res)-> {
		  
		  
		  String usuario=req.queryParams("usuario");
		  String pass=req.queryParams("pass");
		  
		  res.cookie("lgwapp.adb", usuario);
		  
		  res.redirect("/welcome");
		  	 
		  
		  return null;
		  
	  });
	  
	  get("/welcome", (req,res) -> {
		  
		  Map<String, String> model = new HashMap<String, String>();
		  
		  VelocityContext context = new VelocityContext();
		  
		  String user = req.cookie("lgwapp.adb");
		  
		  if(user == null) {
			 
			res.redirect("/");
		  }
		  else {
			  
			  model.put("usuario", user);
			  
		  }
		 
		  String result = new RenderUtil().getTempRes("templates/index.vtl", context);
			 
		  model.put("template", result);
		  
	      
	      return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layoutLogged)
	       );
		  
	  });
	  
	  
  }
}
