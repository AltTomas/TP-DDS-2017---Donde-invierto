package dominio;

import java.math.BigDecimal;

public class Condicion {
	
	private ICalculable calculo;
	private Empresa otraEmpresa;
	private BigDecimal valor;
	
	public boolean aplicarCondicion(Empresa empresa,Periodo periodo){
		if (this.otraEmpresa != null){
			//Se compara un indicador o cuenta de una empresa sea mayor que el de otra empresa (por el momento suponemos para condiciones mayores)
			return empresa.getValorCalculo(calculo, periodo).compareTo(this.otraEmpresa.getValorCalculo(calculo, periodo)) > 0;
		} else {
			//Se compara un indicador o cuenta con un valor
			return empresa.getValorCalculo(this.calculo, periodo).compareTo(this.valor) > 0; //this.calculo > this.valor
		}
		
	}
	
	public void initialize() {
		
		this.otraEmpresa.initialize();
		this.valor = BigDecimal.ZERO;
	}

}
