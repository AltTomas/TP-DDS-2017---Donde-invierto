package util;

import dominio.Empresa;

public class Posiciones {
	
	Empresa empresa;
	String condicion;
	String valor;
	
	public Posiciones(Empresa empresa2, String string, String string2) {
		this.empresa=empresa2;
		this.condicion=string;
		this.valor=string2;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

}
