package dominio;

import java.util.ArrayList;
import java.util.List;

public class Metodologia {
	
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	
	public Metodologia(Condicion paramCondicion){
		agregarCondicion(paramCondicion);
	}
	
	public void agregarCondicion(Condicion paramCondicion){
		this.condiciones.add(paramCondicion);
	}
	
	public void eliminarCondicion(Condicion paramCondicion) {
		this.condiciones.remove(paramCondicion);
	}
	
	public boolean convieneInvertir(Empresa empresa,Periodo periodo){
		boolean valor = true;
		for (Condicion condicion : condiciones) {
			valor = valor && condicion.aplicarCondicion(empresa, periodo);
		}
		return valor;
	}
	
}