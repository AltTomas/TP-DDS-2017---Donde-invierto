package dominio;

import java.time.LocalDate;
import java.lang.UnsupportedOperationException;

public class Periodo {
	
	LocalDate fechaInicio;
	LocalDate fechaFin;
	
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
		return this.fechaInicio.isAfter(otroPeriodo.fechaInicio) && 			
			   this.fechaFin.isBefore(otroPeriodo.fechaFin);
	}
	
	
}
