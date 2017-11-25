package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dominio.Cuenta;
import dominio.Empresa;

public class EmpresaServices {
	
HashMap<String, Empresa> empresas = new HashMap<>();
	
	public Empresa createEmpresa(String nombre){
			
		Empresa empresa = new Empresa(nombre.toUpperCase());
		
		this.empresas.put(empresa.getNombre(), empresa);
		
		return empresa;
	}
	
	public void addCuenta(String nombreEmpresa, Cuenta cuenta) {
		
		Empresa empresa = empresas.get(nombreEmpresa);
		
		empresa.agregarCuenta(cuenta);
	}
	
	public List<Empresa> getAllEmpresas() {
		
		return new ArrayList<>(empresas.values());
	}
	
	public Empresa getEmpresa(String nombre) {
		return empresas.get(nombre);
	}

}
