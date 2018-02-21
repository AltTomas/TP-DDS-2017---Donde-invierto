package dominio;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import dominio.Empresa;

@Entity(name="Cuenta")
@Table(name="cuenta")
public class Cuenta {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;	
	private double valor;
	private String periodo;			
	private Empresa empresa;
	
	
	public Cuenta(String nombre, String periodo, double valor)
	{
		
		
		this.nombre = nombre;
		this.periodo = periodo;
		this.valor = valor;
	}
	
	public Cuenta(){}
	
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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/* Empresa */
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresa() {
		return empresa;
	}		

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	/* Valor */
	
	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getPeriodo () {
		return this.periodo;
	}
	
	public String periodoToEval() {
		
		switch (this.periodo.toUpperCase()) {
		case "ENERO":
			
			return "ENE";
		case "FEBRERO":
			
			return "FEB";
		case "MARZO":
			
			return "MAR";
			
		case "ABRIL":
			
			return "ABR";
			
		case "MAYO":
			
			return "MAY";
			
		case "JUNIO":
				
			return "JUN";
		case "JULIO":
			
			return "JUL";
		case "AGOSTO":
			
			return "AGO";
		case "SEPTIEMBRE":
			
			return "SEP";
		case "OCTUBRE":
			
			return "OCT";
		case "NOVIEMBRE":
			
			return "NOV";
		case "DICIEMBRE":
		
			return "DIC";
		default:
			
			return null;
		}
		
	}
	
}
