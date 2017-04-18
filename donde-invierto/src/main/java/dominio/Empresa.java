package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
	
	private String nombre;
	private List<ICalculable> calculos = new ArrayList<ICalculable>(); //interfaz para cuentas e indicadores
	
	public Empresa(String paramNombre, ICalculable paramCalculo){
		this.nombre = paramNombre;
		agregarCuenta(paramCalculo);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String paramNombre){
		this.nombre = paramNombre;
	}
	
	public void agregarCuenta(ICalculable calculo){
		this.calculos.add(calculo);
	}
	
	public void eliminarCuenta(ICalculable calculo){
		this.calculos.remove(calculo);
	}
	
	public BigDecimal getValorCalculo(ICalculable calculo, Periodo periodo){
		if (this.calculos.contains(calculo)){
			return calculo.calcular(periodo);
		}else{
			return BigDecimal.ZERO;
		}
	}
	
	
	
}