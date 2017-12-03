package dominio;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name="periodo")
public class Periodo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="fechaInicio")
	LocalDate fechaInicio;
    
    @Column(name="fechaFin")
	LocalDate fechaFin;
	
    public Periodo() {}
    
	public Periodo(LocalDate paramFechaInicio, LocalDate paramFechaFin){
		this.fechaInicio = paramFechaInicio;
		this.fechaFin = paramFechaFin;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	//Falta comparar entre que fechas se encuentra el nuevo periodo
	public boolean estaComprendidoEntre(Periodo otroPeriodo)
	{		
		return (this.fechaInicio.equals(otroPeriodo.fechaInicio) && 
			     this.fechaFin.equals(otroPeriodo.fechaFin)) ||
		       (this.fechaInicio.isAfter(otroPeriodo.fechaInicio) && 			
			   this.fechaFin.isBefore(otroPeriodo.fechaFin));
	}
}
