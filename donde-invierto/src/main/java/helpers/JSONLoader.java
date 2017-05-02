package helpers;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
import dominio.Empresa;
import dominio.Periodo;
import dominio.Cuenta;
import java.util.ArrayList;

public class JSONLoader 
{
  
  private String FilePath = null;  
  
  public JSONLoader(String filePath)
  {
	  this.FilePath = filePath;	
  }
  
  
  public ArrayList<Empresa> GetEmpresasFromJSONArray()
  {
	
	ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	
	String contenidoJSON = this.ReadFile(this.FilePath);		
	JSONArray arr = new JSONArray(contenidoJSON);

	for (int i = 0; i < arr.length(); i++) 
	{
		
	  JSONObject empresaJSON = arr.getJSONObject(i);
	  String nombre = empresaJSON.getString("nombre");	
	  
	  Empresa empresa = new Empresa(nombre);
	  	  
	  // Inicializar cuenta.
						
	  if (empresaJSON.has("cuentas")) 
	  {
		 JSONArray cuentas = empresaJSON.getJSONArray("cuentas");
		 
		 for (int p = 0; p < cuentas.length(); p++) 
		 {						
			 
			// Obtener cuenta.
			JSONObject cuentaJSON = cuentas.getJSONObject(p);			
			String cuentaNombre = cuentaJSON.getString("nombre");
			Double cuentaValor = cuentaJSON.getDouble("valor");
			String fechaInicio = cuentaJSON.getString("fechaInicio");
			String fechaFin = cuentaJSON.getString("fechaFin");
			
			try
			{
			  // Parsear fechas.
			  LocalDate cuentaFechaInicio = LocalDate.parse(fechaInicio);
			  LocalDate cuentaFechaFin = LocalDate.parse(fechaFin);
			  
			  Periodo periodo = new Periodo(cuentaFechaInicio, cuentaFechaFin);
			  BigDecimal cuentaValorBigD = new BigDecimal(cuentaValor);
			  Cuenta cuenta = new Cuenta(cuentaNombre, periodo, cuentaValorBigD);
			  
			  empresa.agregarCuenta(cuenta);
			}
		    catch(DateTimeParseException ex)
			{
			   // TODO: código para manejar la excepción.
			   System.out.println("Ocurrio una excepción");
			}						
								
		 }
	  }		
	  
	  empresas.add(empresa);
	  
	}

	return empresas;	  
  }
  
  private String ReadFile(String filePath)
  {	
	try
	{
       File file = new File(filePath);

	   if(!file.exists())
		   return "";
	   
	   String content = new Scanner(file).useDelimiter("\\Z").next();
	   return content;
	}
	catch(FileNotFoundException ex)
	{
	   return "";
	}
  }
  
}