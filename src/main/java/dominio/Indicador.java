package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

@Entity
@Table(name="indicador")
public class Indicador implements ICalculable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
		
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="periodo_id", referencedColumnName="id")
	private Periodo periodo;

	private String nombre;
	//private List<Indicador> indicadores = new ArrayList<Indicador>();
	//private List<Cuenta> cuentas = new ArrayList<Cuenta>();
    public String formula = "";
	
	public Indicador(String paramNombre, Periodo paramPeriodo, String formula) {
		this.nombre = paramNombre;
		this.periodo = paramPeriodo;	
		this.formula = formula;
	}
	
	public Indicador () {}
	
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

	/*
	public void agregarIndicador(Indicador indicador){
		this.indicadores.add(indicador);
	}
	
	public void eliminarIndicador(Indicador indicador){
		this.indicadores.remove(indicador);
	}
	
	public void agregarCuenta(Cuenta cuenta){
		this.cuentas.add(cuenta);
	}
	
	public void eliminarCuenta(Cuenta cuenta){
		this.cuentas.remove(cuenta);
	}*/
	
	public BigDecimal calcular(Periodo periodo){
		if (periodo.estaComprendidoEntre(this.periodo)){
			return this.calcularValorIndicadores().add(this.calcularValorCuentas());
		}else{
			return BigDecimal.ZERO;
		}
	}

	public BigDecimal calcularValorCuentas() {
		return BigDecimal.ZERO;
	}

	public BigDecimal calcularValorIndicadores() {
		return BigDecimal.ZERO;
	}
		
    public boolean estaEnPeriodo(Periodo periodo)
	{
	   if(this.periodo.estaComprendidoEntre(periodo))
		   return true;
	   else
		   return false;
	}		
}

