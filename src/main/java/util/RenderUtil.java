package util;

import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class RenderUtil {
	
	public String getTempRes(String temp, VelocityContext context) {
		
		StringWriter sw = new StringWriter();
		
		VelocityEngine ve = new VelocityEngine();
		
		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		 ve.init();
		 
		 Template template = ve.getTemplate(temp);
		 
		 template.merge(context, sw);
		  
		  String result = sw.toString();
		 
	    return result;
	}
}
