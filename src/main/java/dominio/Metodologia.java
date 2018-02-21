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
    private String formula;
    	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre; 
	}
	
	public Metodologia(String nombre, String formula) {		
		this.nombre = nombre;	
		this.formula = formula;
	}
	
	public Metodologia() {}
	
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public String getFormula() {
		return this.formula;
	}
	
	
}
