package dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;

@Entity
@Table(name="Cuenta")
public class Cuenta {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	private String nombre;	
	private BigDecimal valor;
	
	@OneToOne
	private Periodo periodo;
	
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
	
	public BigDecimal calcular(Periodo periodo){
		if (periodo.estaComprendidoEntre(this.periodo)){
			return this.valor;
		}else{
			return BigDecimal.ZERO;
		}		
	}
	

}
