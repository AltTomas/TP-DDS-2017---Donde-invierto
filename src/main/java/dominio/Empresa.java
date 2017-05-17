package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
	
	private String nombre;
	private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>(); 
	
	public Empresa(String paramNombre)
	{
		this.nombre = paramNombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String paramNombre){
		this.nombre = paramNombre;
	}
	
	public void agregarCuenta(Cuenta cuenta){
		this.cuentas.add(cuenta);
	}
	
	public List<Cuenta> getCuentas(){
		return this.cuentas;
	}
	
	public void eliminarCuenta(Cuenta calculo){
		this.cuentas.remove(calculo);
	}
	
	public BigDecimal getValorCalculo(Cuenta calculo, Periodo periodo){
		if (this.cuentas.contains(calculo)){
			return calculo.calcular(periodo);
		}else{
			return BigDecimal.ZERO;
		}
	}
	
	
	
}