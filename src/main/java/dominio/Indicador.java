package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.DDSParser;

@Entity(name="Indicador")
@Table(name="indicador")
public class Indicador {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nombre;
    private String formula;
    
    @Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    public Long getId()  
    {  
      return id;  
    }  
	  
    public void setId(Long id)  
	{  
      this.id = id;  
	}  
    
	public Indicador(String paramNombre, String formula) {
		this.nombre = paramNombre;	
		this.formula = formula;
	}
	
	public Indicador () {}
	
	/* Formula */

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public String getFormula() {
		return formula;
	}


	/* Nombre */
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getValor(Empresa empresa) {
		
		DDSParser parser = new DDSParser();
		
		return parser.calcular(this.formula, empresa);
	}
	
}

