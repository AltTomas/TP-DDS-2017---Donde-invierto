package dominio;

import java.util.ArrayList;
import java.util.List;

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
    
	//private List<Condicion> condiciones = new ArrayList<Condicion>();
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre; 
	}
	
	public Metodologia(String nombre, Condicion paramCondicion) {
		
		this.nombre = nombre;
		
		if(paramCondicion != null)
			System.out.println("-----");
			//agregarCondicion(paramCondicion);
	}
	
	public Metodologia() {}
			
	public void agregarCondicion(Condicion paramCondicion) {
		/*this.condiciones.add(paramCondicion);*/
	}
	
	public void eliminarCondicion(Condicion paramCondicion) {
		/*this.condiciones.remove(paramCondicion);*/
	}
	
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
