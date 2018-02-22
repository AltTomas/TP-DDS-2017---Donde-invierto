package controllers;


import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.velocity.VelocityContext;

import dominio.Empresa;
import dominio.Indicador;
import services.EmpresaServices;
import services.IndicadorServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.RenderUtil;
import util.Values;



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
	
get("/indicadores/buscar", (req, res)-> {
		
		 VelocityContext context = new VelocityContext();
		 
		 String result = new RenderUtil().getTempRes("templates/buscarIndicador.vtl", context);
		  
		  
		  model.put("template", result);
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
	});
	
	get("/indicadores", (req, res) -> {
		
		
		 VelocityContext context = new VelocityContext();
		 		
		 String indicador =  req.queryParams("indicador");
		  String all = req.queryParams("all");
		  
		  if(all != null) {
			  if(all.equals("yes")) {
			 			  
				  context.put("indicadorList", indicadorServices.getAllIndicadores());
			  }
		  }
		  		else {
		  	if(indicador != null) {
				
		  		List<Indicador> emp = indicadorServices.getIndicador(indicador);
		  					  
			   if(emp == null) {
				   context.put("indicadorList", "error");
			   }
			   
			   else {
				   context.put("indicadorList", emp);
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
	 String formula = req.queryParams("formula");
		 
		 indicadorServices.createIndicador(indicador, formula);
		 
		 res.status(201);
		 res.redirect("/indicadores/ingresar");
		 
		 return indicador;
		
	});
		
	get("/indicadores/calcular", (req, res) -> {
		
						
		 VelocityContext context = new VelocityContext();
		 
		 context.put("indicadorList", indicadorServices.getAllIndicadores());
		 context.put("empresaList", new EmpresaServices().getAllEmpresas());
			 
				  
		 String result = new RenderUtil().getTempRes("templates/calcularIndicador.vtl", context);
		  
		 		  
		  model.put("template", result);
		      
	    return new VelocityTemplateEngine().render(
	        new ModelAndView(model, layout));
		});
	
	get("/indicadores/valor", (req, res) -> {
		
		VelocityContext context = new VelocityContext();
		 
		 String indicador = req.queryParams("indicador");
		 String empresa = req.queryParams("empresa");
		 String periodo = req.queryParams("periodo");
		 String all = req.queryParams("all");
		 
		 Empresa empre = new EmpresaServices().getEmpresa(empresa).get(0);
		 
		 List<Indicador> emp = indicadorServices.getIndicador(indicador);
			  
		  if(emp == null) {
			   context.put("indicadorList", "error");
		   }
		   
		   else if (all != null ) {
			   
			   if (all.equals("yes")){
			   
			   List<Empresa> empresas = new EmpresaServices().getAllEmpresas();
			   
			   List<Values> listaValores = new ArrayList<Values>();
			   
			   for (int i = 0; i < empresas.size(); i++) {
					
				
			   	if(!Double.toString(emp.get(0).getValor(empresas.get(i), periodo)).equals("0.0")){	
			   		
			   		
			   					   	   		
			   		Values valor = new Values(empresas.get(i).getNombre(), emp.get(0).getValor(empresas.get(i), periodo) );
			   		listaValores.add(valor);
			   		   	}						
			   }
			   
			   if(listaValores.isEmpty()) {
				   context.put("indicadorList", "nocuentaall");
			   }
			   else {
			   context.put("valoresList", listaValores);
			   context.put("indicador", emp.get(0).getNombre());
			   }
		   }
		   }
			   
			else if(emp.get(0).getValor(empre, periodo) == 0) {
				  
				  context.put("indicadorList", "nocuenta");
				  
			  }
			  
			  else {
				  
				  List<Values> listaValores = new ArrayList<Values>();
				  Values valor = new Values(empre.getNombre(), emp.get(0).getValor(empre, periodo));
				  listaValores.add(valor);
			   context.put("indicador", emp.get(0).getNombre());
			   context.put("valoresList", listaValores);
			  }
		     
		  
		  
		 String result = new RenderUtil().getTempRes("templates/indicadorValor.vtl", context);
		  
		 model.put("template", result);
	      
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(model, layout));
		
		
	});
	
	
	
	
	}
}
