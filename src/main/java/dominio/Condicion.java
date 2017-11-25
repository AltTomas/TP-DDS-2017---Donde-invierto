package dominio;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Condicion")
public class Condicion {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private Cuenta calculo;
	private Empresa otraEmpresa;
	private BigDecimal valor;
	
	public Condicion(Cuenta paramCalculo, Empresa paramEmpresa, BigDecimal paramValor) 
	{
		this.calculo = paramCalculo;
		this.otraEmpresa = paramEmpresa;
		this.valor = paramValor;
	}
	
	public boolean aplicarCondicion(Empresa empresa,Periodo periodo)
	{
		if (this.otraEmpresa != null)
		{
			//Se compara un indicador o cuenta de una empresa sea mayor que el de otra empresa 
		    // (por el momento suponemos para condiciones mayores)
			return empresa.getValorCalculo(calculo, periodo)
			       .compareTo(this.otraEmpresa.getValorCalculo(calculo, periodo)) > 0;
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
