package util;

import dominio.Empresa;

public class Values {
	
	String nombreEmpresa;
	double valor;
	Boolean valorM;
	Empresa empresa;
	
	
	public Values(String nombreEmpresa, double valor) {		
		this.nombreEmpresa = nombreEmpresa;
		this.valor = valor;		
	}
	
	public Values(String nombreEmpresa, Boolean valorm) {		
		this.nombreEmpresa = nombreEmpresa;
		this.valorM = valorm;
	}
	
	public Values(Empresa empresa, double valor) {		
		this.empresa = empresa;
		this.valor = valor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getValorM() {
		return valorM;
	}
	
	public void setValorM(Boolean valorM) {
		this.valorM = valorM;
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
