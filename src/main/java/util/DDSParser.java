package util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import dominio.Cuenta;
import dominio.Empresa;
import dominio.Indicador;
import services.IndicadorServices;


public class DDSParser {
	
	private static final String SUMA = "+";
	private static final String RESTA = "-";
	private static final String DIV = "/";
	private static final String MULT = "*";
	
public double sumar(String num1, String num2) {
		
		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);
		
		return op1 + op2;
	}
	
public double restar(String num1, String num2) {
		
		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);
		
		return op1 - op2;
	}
	
public double dividir(String num1, String num2) {
		
		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);
		
		return op1 / op2;
	}
	
public double multiplicar(String num1, String num2) {
		
		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);
		
		return op1 * op2;
	}
	
public double potencia(String num1, String num2) {
		
		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);
		
		return Math.pow(op1, op2);
	}
	
public double calcular(String formula, Empresa empresa) {
						
		String parenRedux = reduxParen(getValues(formula, empresa));
		
		String[] result = parenRedux.split("(?<=[-+])|(?=[-+])");
						
		List<String> reducido = new LinkedList<>(Arrays.asList(redux(reduxPot(result))));
		
		return calcularSR(reducido);
				
				
	}

public double calcularSR(List<String> reducido) {
	
	double resfinal = 0;
	
	List<String> finalList = reducido;
	
	if(reducido.size()<2){
		
		resfinal = Double.parseDouble(reducido.get(0));
	}
	
	for (int i = 0; i < reducido.size(); i++) {
		
		if(reducido.size() > 3) {
			
		
		switch (reducido.get(i)) {
		case SUMA:
			
				reducido.set(i, String.valueOf(sumar(reducido.get(i-1), reducido.get(i+1))));
			
				finalList.remove(i-1);
				finalList.remove(i);
				
				reducido=finalList;
				
				i=0;
				
			break;
			
		case RESTA:
			
			reducido.set(i, String.valueOf(restar(reducido.get(i-1), reducido.get(i+1))));
		
			reducido.remove(i-1);
			reducido.remove(i);
		
			i=0;
			
		break;

		default:
			break;
			}
		
		}
		
		else {
			
			switch (reducido.get(i)) {
			case SUMA:
				
				resfinal = sumar(reducido.get(i-1), reducido.get(i+1));
										
				break;
				
			case RESTA:
				
				resfinal = restar(reducido.get(i-1), reducido.get(i+1));
												
			break;

			default:
				break;
				}		
		}
	}
	
	return resfinal;
}

		
public String[] redux(String[] formula) {
						
		List<String> reducido = new LinkedList<>(Arrays.asList(formula));
		
		double resfinal = 0;
		
		for (int i = 0; i < reducido.size(); i++) {
			
			String[] parcial = reducido.get(i).split("(?<=[*/])|(?=[*/])");
			
			LinkedList<String> parcialList = new LinkedList<>(Arrays.asList(parcial));
			LinkedList<String> parcialListDup = parcialList;
			
		for (int j = 0; j < parcialList.size(); j++) {
					
			if(parcialList.size() > 3) {
							
			switch (parcialList.get(j)) {
			case MULT:
					double mult = multiplicar(parcialList.get(j-1), parcialList.get(j+1));
					resfinal = resfinal + mult;
					
					parcialListDup.set(j, String.valueOf(mult));
					parcialListDup.remove(j-1);
					parcialListDup.remove(j);
					
					parcialList=parcialListDup;
					
					j=0;
					
				break;
				
			case DIV:
				
				double div = dividir(parcialList.get(j-1), parcialList.get(j+1));
				resfinal = resfinal + div;
				
				parcialListDup.set(j, String.valueOf(div));
				parcialListDup.remove(j-1);
				parcialListDup.remove(j);
				
				parcialList=parcialListDup;
				
				j=0;
				
			break;

			default:
				break;
				}
				
			}
			
			else {
				
				switch (parcialList.get(j)) {
				case MULT:
					
					resfinal = multiplicar(parcialList.get(j-1), parcialList.get(j+1));
					reducido.set(i, String.valueOf(resfinal));
											
					break;
					
				case DIV:
					
					resfinal = dividir(parcialList.get(j-1), parcialList.get(j+1));
					reducido.set(i, String.valueOf(resfinal));
													
				break;

				default:
					break;
					}
			}
		}
		
		
	}

		String[] enviar = reducido.toArray(new String[0]);
		return enviar;
}
	
	
public String reduxParen(String formula) {
		
		String[] parenSplit = formula.split("(?<=[()])|(?=[()])");
		
		List<String> parenSplitL = new LinkedList<>(Arrays.asList(parenSplit));
		
		List<String> finalList = parenSplitL;
		
		for (int i = 0; i < parenSplitL.size(); i++) {
			
			if(parenSplitL.get(i).equals("(")) {
				
				double resultado = calcular(parenSplitL.get(i+1), null);
				
				parenSplitL.set(i+1, Double.toString(resultado));
				
				finalList.remove(i);				
				finalList.remove(i+1);
				
				parenSplitL = finalList;
				
				i = 0;
				
			}
		}
		
		String[] finalForm = parenSplitL.toArray(new String[0]);
		String enviar = arrayToString(finalForm);
		return enviar;
		
	}

	
public String[] reduxPot(String[] formula) {
		
			
		List<String> reducido = new LinkedList<>(Arrays.asList(formula));
		
		
		double resfinal = 0;
		
		for (int i = 0; i < reducido.size(); i++) {
			
			String[] parcial = reducido.get(i).split("(?<=[*/])|(?=[*/])");
			
			LinkedList<String> parcialList = new LinkedList<>(Arrays.asList(parcial));
						
		for (int j = 0; j < parcialList.size(); j++) {
			
			List<String> redPot = new LinkedList<>(Arrays.asList(parcialList.get(j).split("(?<=\\^)|(?=\\^)")));
			List<String> redPotDup = redPot;
			
			for(int k = 0; k < redPot.size(); k++) {
					
			if(redPot.size() > 3) {
				
				switch (redPot.get(k)) {
				case "^":

					double pot = potencia(redPot.get(k-1), redPot.get(k+1));
					resfinal = resfinal + pot;
					
					redPotDup.set(k, String.valueOf(pot));
					redPotDup.remove(k-1);
					redPotDup.remove(k);
					
					redPot=redPotDup;
					
					k=0;
					
					break;

				default:
					break;
				}		
				
			}
										
			else {
				

				switch (redPot.get(k)) {
				case "^":
						
					resfinal = potencia(redPot.get(k-1), redPot.get(k+1));
					parcial[j]=String.valueOf(resfinal);
					reducido.set(i, arrayToString(parcial));
					
					break;

				default:
					break;
				}	
				
			}
		}
	}
		
		
	}

		String[] enviar = reducido.toArray(new String[0]);
		return enviar;
	}


public String arrayToString(String[] array) {
	
	String stringFinal = array[0];
	
for (int i = 1; i < array.length; i++) {
		
		stringFinal = stringFinal+array[i];
		
	}

	return stringFinal;
}

public String getValues(String formula, Empresa empresa) {
	
	String[] splittedArr = formula.split("(?<=[*/()+-])|(?=[*/()+-])|(?<=\\^|(?=\\^))|(?<=[|&<>=])|(?=[|&<>=])");
	
	for (int i = 0; i < splittedArr.length; i++) {
		
		if(!(splittedArr[i].matches("-?\\d+(\\.\\d+)?") || splittedArr[i].matches("[*/+-^()|&<>=]"))) {
			
			if(!(splittedArr[i].equals("true") || splittedArr[i].equals("false"))) {
			
			splittedArr[i] = searchValue(splittedArr[i], empresa);
			}
			
		}	
	}	
	
	return arrayToString(splittedArr);
	
	
	}

public String searchValue(String id, Empresa empresa) {
	
	List<Cuenta> listaCuentas = empresa.getCuentas();
	
	for (int i = 0; i < listaCuentas.size(); i++) {
		
		if(listaCuentas.get(i).getNombre().equals(id) & id.substring(id.length()-3).equals(listaCuentas.get(i).periodoToEval())){
			
			return Double.toString(listaCuentas.get(i).getValor());
			
		}
		
	}
	
	List<Indicador> listaIndicadores = new IndicadorServices().getAllIndicadores();
	
	for (int i = 0; i < listaIndicadores.size(); i++) {
		
		if(listaIndicadores.get(i).getNombre().equals(id)) {
			
			return Double.toString(this.calcular(id, empresa));
			
		}
		
	}
	
	return null;
}
}
	





	
	
	


