package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

@Entity(name="Empresa")
@Table(name="empresa")
public class Empresa {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String nombre;
	    
	private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>(); 
	
	public Empresa(String paramNombre)
	{
		this.nombre = paramNombre;
	}
	
	public Empresa(){}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String paramNombre){
		this.nombre = paramNombre;
	}
	
	public void agregarCuenta(Cuenta cuenta){
		this.cuentas.add(cuenta);
	}
	
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cuenta_id", referencedColumnName = "cuenta_id")
	public List<Cuenta> getCuentas(){
		return this.cuentas;
	}
	
	public void eliminarCuenta(Cuenta calculo){
		this.cuentas.remove(calculo);
	}
	
	public BigDecimal getValorCalculo(Cuenta calculo, Periodo periodo){
		if (this.cuentas.contains(calculo)){
			return calculo.calcular(periodo);
		}else{
			return BigDecimal.ZERO;
		}
	}
}
