package util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dominio.Empresa;
import services.EmpresaServices;
import services.IndicadorServices;
import services.MetodologiaServices;
import util.DDSParserM;

public class Evaluador {
	

	public DDSParser DDSParser;

	
	private DDSParserM parserM;
	
	public Evaluador() {
		System.out.println("DAAAAAAWG");
		this.parserM = new DDSParserM();	
		this.DDSParser = new DDSParser();
	}
	
	public List<Values> evaluador(List<Empresa> empresas, String metodologia) {
				
		int promedio = 0;
		ArrayList<ArrayList<Values>> listaValores = new ArrayList<ArrayList<Values>>();		
		String[] condiciones = splitMetodologia(metodologia);
				
		for (int i = 0; i < condiciones.length; i++) {
											
			if(!(condiciones[i]==null | condiciones[i].equals("") | condiciones[i].equals("&"))){
				
				List<Posiciones> posiciones = new ArrayList<Posiciones>();
				
				for (int j = 0; j < empresas.size(); j++) {		
					
					if(condiciones[i].startsWith("AUM") | condiciones[i].startsWith("DIS")) {
						condiciones[i] = generateCons(condiciones[i]);
					}
					
					
					Boolean res = this.parserM.evaluar(condiciones[i], empresas.get(j), "2017");					
					Posiciones posicion = new Posiciones(empresas.get(j), condiciones[i], Boolean.toString(res));
					posiciones.add(posicion);	
					
				
				}
								
				ArrayList<Values> valores = evalPosiciones(posiciones);				
				listaValores.add(valores);	
				promedio++;
			}
			
		}
				
		return finalSort(listaValores, promedio);
		
	}
	
	public String[] splitMetodologia(String metodologia) {				  
	  	   String[] split1 = metodologia.split("([()])");	  	  	  	   	  	   
	  	   return split1;
	}
	
	public List<Values> finalSort(ArrayList<ArrayList<Values>> listaValores, int promedio){
		
	
		
		List<Values> finalVal = listaValores.get(0);
		
		for (int i = 1; i < listaValores.size(); i++) {
			
			ArrayList<Values> valuesLis = listaValores.get(i);
			
				for (int j = 0; j < valuesLis.size(); j++) {
					
					String empresa = valuesLis.get(j).getEmpresa().getNombre();
					
						for (int k = 0; k < finalVal.size(); k++) {
							
							if(finalVal.get(k).getEmpresa().getNombre().equals(empresa)) {
								
								double posicion = finalVal.get(k).getValor() + valuesLis.get(j).getValor();
								
								finalVal.get(k).setValor(posicion);								
							}
							
					}
			}
			
		}
		
			
		Values[] arr = new Values[finalVal.size()];
		
		arr = finalVal.toArray(arr);
		
		int n = finalVal.size();
		
		 for (int i = 0; i < n-1; i++) {
	            for (int j = 0; j < n-i-1; j++) {
	            	
	                if (arr[j].getValor() > arr[j+1].getValor())
	                {
	                     Values temp = arr[j];
	                    arr[j] = arr[j+1];
	                    arr[j+1] = temp;
	                }
	           }
		 }
		 
		 finalVal = Arrays.asList(arr);
		 
			for (int i = 0; i < finalVal.size(); i++) {
				
			
				
				finalVal.get(i).setValor(i+1);
				
			}
		
		
		
		return finalVal;
		
		
		
		
	}
	
	public ArrayList<Values> evalPosiciones(List<Posiciones> posiciones){
		
			Posiciones[] arr = new Posiciones[posiciones.size()];
			
			arr = posiciones.toArray(arr);
			
			  int n = arr.length;
			  
		        for (int i = 0; i < n-1; i++) {
		            for (int j = 0; j < n-i-1; j++) {
		            	
		                if (arr[j].getValor().equals("false"))
		                {
		                    // swap temp and arr[i]
		                    Posiciones temp = arr[j];
		                    arr[j] = arr[j+1];
		                    arr[j+1] = temp;
		                }
		        
		        		if(arr[j].getCondicion().startsWith("MAY")){		        			
		        			
		        			if(StringUtils.substringAfter(arr[j].getCondicion(), "_").equals("ANTIG")){
		        			
		        				if(arr[j].getEmpresa().getAntiguedad() < arr[j+1].getEmpresa().getAntiguedad()) {
		        			Posiciones temp = arr[j];
		                    arr[j] = arr[j+1];
		                    arr[j+1] = temp;
		                    
		        				}
		        			}
		        			else {
		        				
		        				String periodo = StringUtils.substringAfterLast(arr[j].getCondicion(), "_");
		        				
		        				if(periodo.equals("LAST")) {
		        					periodo = "2017";
		        				}
		        				
		        				String value1 = DDSParser.searchValue(StringUtils.substringBetween(arr[j].getCondicion(), "_"), arr[j].getEmpresa(),periodo);
		        				String value2 = DDSParser.searchValue(StringUtils.substringBetween(arr[j].getCondicion(), "_"), arr[j+1].getEmpresa(),periodo);
		        				
		        				double value1i = Double.parseDouble(value1);
		        				double value2i = Double.parseDouble(value2);
		        				
		        				if(value1i < value2i) {
				        			Posiciones temp = arr[j];
				                    arr[j] = arr[j+1];
				                    arr[j+1] = temp;
				                    
				        				}
		        				
		        			}
		        			
		        	
		        		}
		        		
		        		if(arr[j].getCondicion().startsWith("MEN")){
		        			
		        			if(StringUtils.substringAfter(arr[j].getCondicion(), "_").equals("ANTIG")){
			        			
			        			if(arr[j].getEmpresa().getAntiguedad() > arr[j+1].getEmpresa().getAntiguedad()) {
			        			Posiciones temp = arr[j];
			                    arr[j] = arr[j+1];
			                    arr[j+1] = temp;
			                    
			        				}
		        			}
			        			
			        			else {
			        				
			        				String periodo = StringUtils.substringAfterLast(arr[j].getCondicion(), "_");
			        				
			        				if(periodo.equals("LAST")) {
			        					periodo = "2017";
			        				}
			        				
			        				String value1 = DDSParser.searchValue(StringUtils.substringBetween(arr[j].getCondicion(), "_"), arr[j].getEmpresa(),periodo);
			        				String value2 = DDSParser.searchValue(StringUtils.substringBetween(arr[j].getCondicion(), "_"), arr[j+1].getEmpresa(),periodo);
			        				
			        				double value1i = Double.parseDouble(value1);
			        				double value2i = Double.parseDouble(value2);
			        				
			        				if(value1i > value2i) {
					        			Posiciones temp = arr[j];
					                    arr[j] = arr[j+1];
					                    arr[j+1] = temp;
					                    
					        				}
			        				
			        			}
		        			
		        		}
		            
		           }
		        }
		        
		        ArrayList<Values> returnLis = new ArrayList<Values>();
		        
		        for (int i = 0; i < arr.length; i++) {
					
		        	Values value = new Values(arr[i].getEmpresa(), i+1);
		        	returnLis.add(value);
		        	
				}
				
		
		return returnLis;
		
	}
	
	public String generateCons(String cond) {
		
		if(StringUtils.startsWith(cond, "AUM")) {
			
			String id = StringUtils.substringBetween(cond, "-", "_");
			
			Integer periodoInit = Integer.parseInt(StringUtils.substringBetween(cond, "_", "_"));
					
		
			
			Integer until = Integer.parseInt(StringUtils.substringAfterLast(cond, "_"));
			
			String condicion = null;
			
			for (int i = 0; i < until; i++) {
				
				String periodo1 = Integer.toString(periodoInit+i);
				String periodo2 = Integer.toString(periodoInit+i+1);
				
				String condparc = id+"_"+periodo1+"<"+id+"_"+periodo2;
				
				if(i==0) {
				condicion = condparc;
				}
				
				else {
					condicion = condicion+"&"+condparc;
				}
				
								
			}
			
			if(condicion == null) {
				return cond;
			}
			
			return condicion;
			
			
			
		}
		
		else if(StringUtils.startsWith(cond, "DIS")) {
			
			String id = StringUtils.substringBetween(cond, "-", "_");
			
			Integer periodoInit = Integer.parseInt(StringUtils.substringBetween(cond, "_", "_"));
			
			Integer until = Integer.parseInt(StringUtils.substringAfterLast(cond, "_"));
			
			String condicion = null;
			
			for (int i = 0; i < until; i++) {
				
				String periodo1 = Integer.toString(periodoInit+i);
				String periodo2 = Integer.toString(periodoInit+i+1);
				
				String condparc = id+"_"+periodo1+">"+id+"_"+periodo2;
				
				if(i==0) {
				condicion = condparc;
				}
				
				else {
					condicion = condicion+"&"+condparc;
				}
				
								
			}
			
			if(condicion == null) {
				return cond;
			}
			
			return condicion;
			
			
		}
		
		return cond;
		
	}
}



