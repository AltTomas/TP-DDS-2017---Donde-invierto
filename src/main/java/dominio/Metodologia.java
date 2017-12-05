package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Metodologia")
public class Metodologia {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	
	public Metodologia(Condicion paramCondicion) {
		agregarCondicion(paramCondicion);
	}
	
	public void agregarCondicion(Condicion paramCondicion) {
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
