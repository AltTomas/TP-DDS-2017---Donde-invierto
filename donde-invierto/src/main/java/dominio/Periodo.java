package dominio;

import java.util.Date;

import org.joda.time.DateTime;

public class Periodo {
	
	Date fechaInicio;
	Date fechaFin;
	
	public Periodo(Date paramFechaInicio, Date paramFechaFin){
		this.fechaInicio = paramFechaInicio;
		this.fechaFin = paramFechaFin;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	//Falta comparar entre que fechas se encuentra el nuevo periodo
	public boolean estaComprendidoEntre(Periodo otroPeriodo){
		return this.fechaInicio.after(otroPeriodo.fechaInicio) && this.fechaFin.before(otroPeriodo.fechaFin);
	}
	
	
}
