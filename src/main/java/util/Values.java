package util;

public class Values {
	
	String nombreEmpresa;
	double valor;
	Boolean valorM;
	
	
	public Values(String nombreEmpresa, double valor) {		
		this.nombreEmpresa = nombreEmpresa;
		this.valor = valor;		
	}
	
	public Values(String nombreEmpresa, Boolean valorm) {		
		this.nombreEmpresa = nombreEmpresa;
		this.valorM = valorm;
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
