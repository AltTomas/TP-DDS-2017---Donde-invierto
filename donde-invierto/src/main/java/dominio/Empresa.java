package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
	
	private String nombre;
	
		
	// Interfaz para cuentas e indicadores
	private List<ICalculable> calculos = new ArrayList<ICalculable>(); 
	
	public Empresa(String paramNombre){
		this.nombre = paramNombre;
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
	
	public List<ICalculable> getCuentas(){
		return this.calculos;
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