package dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import dominio.Empresa;

@Entity(name="Cuenta")
@Table(name="cuenta")
public class Cuenta {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String nombre;	
	private BigDecimal valor;

	private Periodo periodo;			
	private Empresa empresa;
	
	public Cuenta(String nombre, String fechaInicio, String fechaFin, int valor)
	{
		LocalDate cuentaFechaInicio = LocalDate.parse(fechaInicio);
		LocalDate cuentaFechaFin = LocalDate.parse(fechaFin);			  
        Periodo periodo = new Periodo(cuentaFechaInicio, cuentaFechaFin);
        BigDecimal valorBigDecimal = new BigDecimal(valor);
		
		this.nombre = nombre;
		this.periodo = periodo;
		this.valor = valorBigDecimal;
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
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periodo_id")
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
			
	public BigDecimal calcular(Periodo periodo) {
		
		if (periodo.estaComprendidoEntre(this.periodo)) {
		  return this.valor;
		}
		else {
		  return BigDecimal.ZERO;
		}		
	}
	
}
