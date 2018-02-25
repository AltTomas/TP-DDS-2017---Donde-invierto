package util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dominio.Empresa;
import util.DDSParserM;

public class Evaluador {
	
	public List<Values> evaluador(List<Empresa> empresas, String metodologia) {
		
		ArrayList<ArrayList<Values>> listaValores = new ArrayList<ArrayList<Values>>();
		
		String[] condiciones = splitMetodologia(metodologia);
		
		for (int i = 0; i < condiciones.length; i++) {
			
			if(!(condiciones[i]==null | condiciones[i]=="")){
				
				List<Posiciones> posiciones = new ArrayList<Posiciones>();
				
				for (int j = 0; j < empresas.size(); j++) {
					
					Boolean res = new DDSParserM().evaluar(condiciones[i], empresas.get(j), "2017");
					
					
					
					posiciones.add(new Posiciones(empresas.get(j), condiciones[i], Boolean.toString(res)));	
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
		
		ArrayList<Values> finalVal = new ArrayList<Values>();
		
		for (int i = 0; i < listaValores.size(); i++) {
			
			ArrayList<Values> valuesLis = listaValores.get(i);
			
			for (int j = 0; j < valuesLis.size(); j++) {
				
				
				
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
		        			
		        			if(Integer.parseInt(arr[j].getValor()) < Integer.parseInt(arr[j+1].getValor())) {
		        			Posiciones temp = arr[j];
		                    arr[j] = arr[j+1];
		                    arr[j+1] = temp;
		                    
		        			}
		        			
		        		}
		        		
		        		if(arr[j].getCondicion().startsWith("MEN")){
		        			
		        			if(Integer.parseInt(arr[j].getValor()) > Integer.parseInt(arr[j+1].getValor())) {
		        			Posiciones temp = arr[j];
		                    arr[j] = arr[j+1];
		                    arr[j+1] = temp;
		                    
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



