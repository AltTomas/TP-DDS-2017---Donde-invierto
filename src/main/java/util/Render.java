package util;

import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Render {
	
	public static String render(Map<String, Object> model, String templatePath) {
	    return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
