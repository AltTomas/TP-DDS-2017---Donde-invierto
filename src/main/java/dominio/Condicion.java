package dominio;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="condicion")
public class Condicion {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cuenta_id", referencedColumnName="id")
	private Cuenta calculo;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empresa_id", referencedColumnName="id")
	private Empresa empresa;
	
	private BigDecimal valor;
	
	public Condicion(Cuenta paramCalculo, Empresa paramEmpresa, BigDecimal paramValor) 
	{
		this.calculo = paramCalculo;
		this.empresa = paramEmpresa;
		this.valor = paramValor;
	}
	
	public Condicion() {}
	
	public boolean aplicarCondicion(Empresa empresa,Periodo periodo)
	{
		if (this.empresa != null)
		{
			//Se compara un indicador o cuenta de una empresa sea mayor que el de otra empresa 
		    // (por el momento suponemos para condiciones mayores)
			return empresa.getValorCalculo(calculo, periodo)
			       .compareTo(this.empresa.getValorCalculo(calculo, periodo)) > 0;
		}
		else 
		{
		   //Se compara un indicador o cuenta con un valor
		   //this.calculo > this.valor
		   return empresa.getValorCalculo(this.calculo, periodo)
		                 .compareTo(this.valor) > 0;
		}
	}
	
}
