package dominio;

import java.util.ArrayList;
import java.util.List;

public class Metodologia {
	
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	
	public boolean convieneInvertir(Empresa empresa,Periodo periodo){
		boolean valor = true;
		for (Condicion condicion : condiciones) {
			valor = valor && condicion.aplicarCondicion(empresa, periodo);
		}
		return valor;
	}
	
	public void initialize() {
		this.condiciones = null;
	}
}
