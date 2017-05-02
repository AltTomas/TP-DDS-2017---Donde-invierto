package helpers;

import java.util.Date;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.joda.time.DateTime;
import dominio.Empresa;
import dominio.Periodo;
import dominio.Cuenta;



public class JSONLoader 
{
  
  private String FilePath = "";  
  
  public JSONLoader(String filePath)
  {
	  this.FilePath = filePath;	
  }
  
  
  public String GetCuentasAsJSONArray()
  {
	  
	String contenidoJSON = this.ReadFile(this.FilePath);	
	System.out.println(this.FilePath);
	
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
			String fechaInicio = cuenta.get("fecha inicio").toString();
			String fechaFin = cuenta.getString("fecha fin");
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
			Date cuentaFechaInicio = null;
			Date cuentaFechaFin = null;
			
			try
			{
				cuentaFechaInicio = formatoDelTexto.parse(fechaInicio);
				cuentaFechaFin = formatoDelTexto.parse(fechaFin);
			} 
			catch (ParseException ex) 
			{
				ex.printStackTrace();
			}
			
			Periodo periodo = new Periodo(cuentaFechaInicio, cuentaFechaFin);
			
			BigDecimal cuentaValorBigD = new BigDecimal (cuentaValor);
			
			Cuenta cuenta1 = new Cuenta(cuentaNombre, periodo, cuentaValorBigD);
			
			
			// TODO: Inicializar indicador.
			
			System.out.println(cuentaNombre);
			System.out.println(cuentaValor);
		 }
	  }		
	}

	return "";	  
  }
  
  private String ReadFile(String filePath)
  {	
	try
	{
	   String content = new Scanner(new File(filePath)).useDelimiter("\\Z").next();	
	   return content;
	}
	catch(FileNotFoundException ex)
	{
	   return "";
	}
  }
  
}