package dominio;

import org.joda.time.DateTime;

public class Periodo {
	
	DateTime fechaInicio;
	DateTime fechaFin;
	
	public Periodo(DateTime paramFechaInicio, DateTime paramFechaFin){
		this.fechaInicio = paramFechaInicio;
		this.fechaFin = paramFechaFin;
	}
	
	public DateTime getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(DateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public DateTime getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(DateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	//Falta comparar entre que fechas se encuentra el nuevo periodo
	public boolean estaComprendidoEntre(Periodo otroPeriodo){
		return this.fechaInicio.isAfter(otroPeriodo.fechaInicio) && this.fechaFin.isBefore(otroPeriodo.fechaFin);
	}
	
	
}
