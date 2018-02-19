package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="metodologia")
public class Metodologia {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;	
    private String nombre;
    	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre; 
	}
	
	public Metodologia(String nombre) {		
		this.nombre = nombre;		
	}
	
	public Metodologia() {}
					
	public boolean convieneInvertir(Empresa empresa,Periodo periodo) {
		boolean valor = true;
		valor = false;
		/*
		for (Condicion condicion : condiciones) {
			valor = valor && condicion.aplicarCondicion(empresa, periodo);
		}*/
		
		return valor;
	}
	
}
