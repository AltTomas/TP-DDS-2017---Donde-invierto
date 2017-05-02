package dominio;

import java.math.BigDecimal;

public class Cuenta implements ICalculable {

	private String nombre;
	private Periodo periodo;
	private BigDecimal valor;
	
	public Cuenta(String paramNombre, Periodo paramPeriodo, BigDecimal paramValor) {
		this.nombre = paramNombre;
		this.periodo = paramPeriodo;
		this.valor = paramValor;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public BigDecimal calcular(Periodo periodo){
		if (periodo.estaComprendidoEntre(this.periodo)){
			return this.valor;
		}else{
			return BigDecimal.ZERO;
		}
	}
	
	
		
}
