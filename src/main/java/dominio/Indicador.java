package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Indicador implements ICalculable {
	
	private String nombre;
	private Periodo periodo;
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
    private String formula = "";
	
	public Indicador(String paramNombre, Periodo paramPeriodo, String formula) {
		this.nombre = paramNombre;
		this.periodo = paramPeriodo;	
		this.formula = formula;
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

	public void agregarIndicador(Indicador indicador){
		this.indicadores.add(indicador);
	}
	
	public void eliminarIndicador(Indicador indicador){
		this.indicadores.remove(indicador);
	}
	
	public void agregarCuenta(Cuenta cuenta){
		this.cuentas.add(cuenta);
	}
	
	public void eliminarCuenta(Cuenta cuenta){
		this.cuentas.remove(cuenta);
	}
	
	public BigDecimal calcular(Periodo periodo){
		if (periodo.estaComprendidoEntre(this.periodo)){
			return this.calcularValorIndicadores().add(this.calcularValorCuentas());
		}else{
			return BigDecimal.ZERO;
		}
	}

	public BigDecimal calcularValorCuentas() {
		return BigDecimal.ZERO;
	}

	public BigDecimal calcularValorIndicadores() {
		return BigDecimal.ZERO;
	}

}

