package util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import dominio.Empresa;
import redis.clients.jedis.Jedis;

public class DDSParserM extends DDSParser {

	// Variables de cálculo.
	private static final String OR = "|";
	private static final String AND = "&";
	private static final String MENOR = "<";
	private static final String MAYOR = ">";
	private static final String IGUAL = "=";

	private Jedis jedis;

	public DDSParserM() {
		this.jedis = new Jedis("localhost");
	}

	public Boolean or(String num1, String num2) {

		boolean op1 = Boolean.parseBoolean(num1);
		boolean op2 = Boolean.parseBoolean(num2);

		return op1 | op2;
	}

	public Boolean and(String num1, String num2) {

		boolean op1 = Boolean.parseBoolean(num1);
		boolean op2 = Boolean.parseBoolean(num2);

		return op1 & op2;
	}

	public Boolean mayor(String num1, String num2) {

		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);

		return op1 > op2;
	}

	public Boolean menor(String num1, String num2) {
		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);

		return op1 < op2;
	}

	public Boolean igual(String num1, String num2) {

		double op1 = Double.parseDouble(num1);
		double op2 = Double.parseDouble(num2);

		return op1 == op2;
	}

	public Boolean evaluar(String formula, Empresa empresa, String periodo) {

		Boolean evaluacion;

		String clave = formula + "_" + empresa + "_" + periodo; // Clave tiene la forma "formula-empresa-periodo".
		String value = jedis.get(clave); // Buscar si el resultado esta en redis.

		if (value != null) {
			// El resultado fue guardado en redis. Parsear el valor.
			evaluacion = Boolean.parseBoolean(value);
		} else {
			String redux1 = evalParen((getValues(formula, empresa, periodo)));
			String redux2 = reduxCalc(redux1.split("(?<=[|&<>=])|(?=[|&<>=])"));
			String[] redux3 = reduxMMI(redux2.split("(?<=[|&])|(?=[|&])"));
			evaluacion = reduxAO(redux3);

			this.jedis.set(clave, String.valueOf(evaluacion));
		}

		return evaluacion;

	}

	private Boolean reduxAO(String[] redux3) {

		Boolean resfinal = null;

		List<String> reducido = new LinkedList<>(Arrays.asList(redux3));

		List<String> finalList = reducido;

		if (reducido.size() < 2) {

			resfinal = Boolean.parseBoolean(reducido.get(0));
		}

		for (int i = 0; i < reducido.size(); i++) {

			if (reducido.size() > 3) {

				switch (reducido.get(i)) {
				case OR:

					reducido.set(i, String.valueOf(or(reducido.get(i - 1), reducido.get(i + 1))));

					finalList.remove(i - 1);
					finalList.remove(i);

					reducido = finalList;

					i = 0;

					break;

				case AND:

					reducido.set(i, String.valueOf(and(reducido.get(i - 1), reducido.get(i + 1))));

					reducido.remove(i - 1);
					reducido.remove(i);

					i = 0;

					break;

				default:
					break;
				}

			}

			else {

				switch (reducido.get(i)) {
				case OR:

					resfinal = or(reducido.get(i - 1), reducido.get(i + 1));

					break;

				case AND:

					resfinal = and(reducido.get(i - 1), reducido.get(i + 1));

					break;

				default:
					break;
				}
			}
		}

		return resfinal;
	}

	private String[] reduxMMI(String[] redux2) {

		List<String> reducido = new LinkedList<>(Arrays.asList(redux2));

		Boolean resfinal = null;

		for (int i = 0; i < reducido.size(); i++) {

			String[] parcial = reducido.get(i).split("(?<=[<>=])|(?=[<>=])");

			LinkedList<String> parcialList = new LinkedList<>(Arrays.asList(parcial));
			LinkedList<String> parcialListDup = parcialList;

			for (int j = 0; j < parcialList.size(); j++) {

				if (parcialList.size() > 3) {

					switch (parcialList.get(j)) {
					case IGUAL:
						Boolean igual = igual(parcialList.get(j - 1), parcialList.get(j + 1));
						resfinal = resfinal & igual;

						parcialListDup.set(j, String.valueOf(igual));
						parcialListDup.remove(j - 1);
						parcialListDup.remove(j);

						parcialList = parcialListDup;

						j = 0;

						break;

					case MENOR:

						Boolean menor = menor(parcialList.get(j - 1), parcialList.get(j + 1));
						resfinal = resfinal & menor;

						parcialListDup.set(j, String.valueOf(menor));
						parcialListDup.remove(j - 1);
						parcialListDup.remove(j);

						parcialList = parcialListDup;

						j = 0;

						break;

					case MAYOR:

						Boolean mayor = mayor(parcialList.get(j - 1), parcialList.get(j + 1));
						resfinal = resfinal & mayor;

						parcialListDup.set(j, String.valueOf(mayor));
						parcialListDup.remove(j - 1);
						parcialListDup.remove(j);

						parcialList = parcialListDup;

						j = 0;

						break;

					default:
						break;
					}

				}

				else {

					switch (parcialList.get(j)) {

					case IGUAL:
						resfinal = igual(parcialList.get(j - 1), parcialList.get(j + 1));
						reducido.set(i, String.valueOf(resfinal));

						break;

					case MENOR:

						resfinal = menor(parcialList.get(j - 1), parcialList.get(j + 1));
						reducido.set(i, String.valueOf(resfinal));

						break;

					case MAYOR:

						resfinal = mayor(parcialList.get(j - 1), parcialList.get(j + 1));
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

	public String evalParen(String formula) {

		String[] parenSplit = formula.split("(?<=[()])|(?=[()])");

		List<String> parenSplitL = new LinkedList<>(Arrays.asList(parenSplit));

		List<String> finalList = parenSplitL;

		for (int i = 0; i < parenSplitL.size(); i++) {

			if (parenSplitL.get(i).equals("(")) {

				Boolean resultado = evaluar(parenSplitL.get(i + 1), null, null);

				parenSplitL.set(i + 1, Boolean.toString(resultado));

				finalList.remove(i);
				finalList.remove(i + 1);

				parenSplitL = finalList;

				i = 0;

			}
		}

		String[] finalForm = parenSplitL.toArray(new String[0]);
		String enviar = arrayToString(finalForm);
		return enviar;

	}

	public String reduxCalc(String[] result) {

		for (int i = 0; i < result.length; i++) {

			if (result[i].length() > 1) {

				if (!(result[i].equals("true") || result[i].equals("false"))) {
					result[i] = String.valueOf(calcular(result[i], null, null));

				}

			}

		}

		return arrayToString(result);

	}
	
	
}
