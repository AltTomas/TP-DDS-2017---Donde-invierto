package controllers;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class indexController {

  public indexController() {
		
	String layout = "templates/layout.vtl";	
	
	  get("/", (req, res) -> {
		
		  Map<String, String> model = new HashMap<String, String>();
	      model.put("template", "templates/index.vtl" );
	      
	      return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout)
	       );
	  });
  }
}
