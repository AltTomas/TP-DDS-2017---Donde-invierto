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

public class JSONLoader 
{
  
  private String FilePath = null;  
  
  public JSONLoader(String filePath)
  {
	  this.FilePath = filePath;	
  }
  
  
  public String GetCuentasAsJSONArray()
  {
	  
	String contenidoJSON = this.ReadFile(this.FilePath);		
	JSONArray arr = new JSONArray(contenidoJSON);

	for (int i = 0; i < arr.length(); i++) 
	{
		
	  JSONObject cuentaJSON = arr.getJSONObject(i);
	  String nombre = cuentaJSON.getString("nombre");	
	  
	  Empresa empresa = new Empresa(nombre);
	  
	  
	  // Inicializar cuenta.
						
	  if (cuentaJSON.has("cuentas")) 
	  {
		 JSONArray cuentas = cuentaJSON.getJSONArray("cuentas");
		 
		 for (int p = 0; p < cuentas.length(); p++) 
		 {						
			 
			JSONObject cuenta = cuentas.getJSONObject(p);					
			String cuentaNombre = cuenta.getString("nombre");
			Double cuentaValor = cuenta.getDouble("valor");
			String fechaInicio = cuenta.getString("fechaInicio");
			String fechaFin = cuenta.getString("fechaFin");
			
			try
			{
			  // Parsear fechas.
			  LocalDate cuentaFechaInicio = LocalDate.parse(fechaInicio);
			  LocalDate cuentaFechaFin = LocalDate.parse(fechaFin);
			  
			  Periodo periodo = new Periodo(cuentaFechaInicio, cuentaFechaFin);
			  BigDecimal cuentaValorBigD = new BigDecimal(cuentaValor);
			  Cuenta cuenta1 = new Cuenta(cuentaNombre, periodo, cuentaValorBigD);
			}
		    catch(DateTimeParseException ex)
			{
			   // TODO: código para manejar la excepción.
			   System.out.println("Ocurrio una excepción");
			}
								
		 }
	  }		
	}

	return "";	  
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