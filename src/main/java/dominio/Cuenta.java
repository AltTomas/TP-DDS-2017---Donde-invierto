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
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name="cuenta")
public class Cuenta {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	private String nombre;	
	private BigDecimal valor;
		
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="periodo_id", referencedColumnName="id")
	private Periodo periodo;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="empresa_id", referencedColumnName="id")
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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
			
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
		
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setPeriodo(Empresa empresa) {
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
