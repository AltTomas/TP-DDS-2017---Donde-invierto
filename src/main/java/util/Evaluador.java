package util;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dominio.Empresa;
import util.DDSParserM;

public class Evaluador {
	
	private DDSParserM parserM;
	
	public Evaluador() {
		System.out.println("DAAAAAAWG");
		this.parserM = new DDSParserM();		
	}
	
	public List<Values> evaluador(List<Empresa> empresas, String metodologia) {
				
		ArrayList<ArrayList<Values>> listaValores = new ArrayList<ArrayList<Values>>();		
		String[] condiciones = splitMetodologia(metodologia);
				
		for (int i = 0; i < condiciones.length; i++) {
											
			if(!(condiciones[i]==null | condiciones[i].equals("") | condiciones[i].equals("&"))){
				
				List<Posiciones> posiciones = new ArrayList<Posiciones>();
				
				for (int j = 0; j < empresas.size(); j++) {					
					Boolean res = this.parserM.evaluar(condiciones[i], empresas.get(j), "2017");					
					Posiciones posicion = new Posiciones(empresas.get(j), condiciones[i], Boolean.toString(res));
					posiciones.add(posicion);	
				}
								
				ArrayList<Values> valores = evalPosiciones(posiciones);				
				listaValores.add(valores);								
			}
			
		}
				
		return finalSort(listaValores);
		
	}
	
	public String[] splitMetodologia(String metodologia) {				  
	  	   String[] split1 = metodologia.split("([()])");	  	  	  	   	  	   
	  	   return split1;
	}
	
	public List<Values> finalSort(ArrayList<ArrayList<Values>> listaValores){
		
		ArrayList<Values> finalVal = listaValores.get(0);
		
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
		
		for (int i = 0; i < finalVal.size(); i++) {
			
			double posicion = Math.floor(finalVal.get(i).getValor() / 4);
			
			finalVal.get(i).setValor(posicion);
			
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
		        				
		        				String value1 = new DDSParser().searchValue(StringUtils.substringAfter(arr[j].getCondicion(), "_"), arr[j].getEmpresa(),"2017");
		        				String value2 = new DDSParser().searchValue(StringUtils.substringAfter(arr[j].getCondicion(), "_"), arr[j+1].getEmpresa(),"2017");
		        				
		        				int value1i = Integer.parseInt(value1);
		        				int value2i = Integer.parseInt(value2);
		        				
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
			        				
			        				String value1 = new DDSParser().searchValue(StringUtils.substringAfter(arr[j].getCondicion(), "_"), arr[j].getEmpresa(),"2017");
			        				String value2 = new DDSParser().searchValue(StringUtils.substringAfter(arr[j].getCondicion(), "_"), arr[j+1].getEmpresa(),"2017");
			        				
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
}



