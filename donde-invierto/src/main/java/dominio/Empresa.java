package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
	
	private List<ICalculable> calculos; //interfaz para cuentas e indicadores
	
	public Empresa(){
		calculos = new ArrayList<ICalculable>();
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
	
	public void initialize() {
		this.calculos = null;
	}
	
}