package controllers;


import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.velocity.VelocityContext;

import dominio.Empresa;
import dominio.Metodologia;
import services.EmpresaServices;
import services.MetodologiaServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.RenderUtil;
import util.Values;




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
		
	get("/metodologias/calcular", (req, res) -> {
		
		
		 VelocityContext context = new VelocityContext();
		 
		 context.put("metodologiaList", metodologiaServices.getAllMetodologia());
		 context.put("empresaList", new EmpresaServices().getAllEmpresas());
			 
				  
		 String result = new RenderUtil().getTempRes("templates/calcularMetodologia.vtl", context);
		  
		 		  
		  model.put("template", result);
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
		});
	
	get("/metodologias/valor", (req, res) -> {
		
		VelocityContext context = new VelocityContext();
		 
		 String metodologia = req.queryParams("metodologia");
		 String empresa = req.queryParams("empresa");
		 String periodo = req.queryParams("periodo");
		 String all = req.queryParams("all");
		 
		 Empresa empre = new EmpresaServices().getEmpresa(empresa).get(0);
		 
		 List<Metodologia> emp = metodologiaServices.getMetodologia(metodologia);
			  
		  if(emp == null) {
			   context.put("indicadorList", "error");
		   }
		   
		   else if (all != null ) {
			   
			   if (all.equals("yes")){
			   
			   List<Empresa> empresas = new EmpresaServices().getAllEmpresas();
			   
			   List<Values> listaValores = new ArrayList<Values>();
			   
			   for (int i = 0; i < empresas.size(); i++) {
					
				  		
			   		Values valor = new Values(empresas.get(i).getNombre(), emp.get(0).getValor(empresas.get(i), periodo) );
			   		listaValores.add(valor);
			   		   					
			   }
			   
			   if(listaValores.isEmpty()) {
				   context.put("indicadorList", "nocuentaall");
			   }
			   else {
			   context.put("valoresList", listaValores);
			   context.put("metodologia", emp.get(0).getNombre());
			   
			   	}
			  }
		   }
			   						  
			  else {
				  
				  List<Values> listaValores = new ArrayList<Values>();
				  Values valor = new Values(empre.getNombre(), emp.get(0).getValor(empre, periodo));
				  listaValores.add(valor);
			   context.put("metodologia", emp.get(0).getNombre());
			   context.put("valoresList", listaValores);
			  }
		     
		  
		  
		 String result = new RenderUtil().getTempRes("templates/metodologiaValor.vtl", context);
		  
		 model.put("template", result);
	      
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(model, layout));
		
		
		});
	
	
	}
}
