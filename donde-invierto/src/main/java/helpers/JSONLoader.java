package helpers;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONLoader {
  
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
	  
	  System.out.println(nombre);
						
	  if (cuentaJSON.has("indicadores")) 
	  {
		 JSONArray indicadores = cuentaJSON.getJSONArray("indicadores");
		 
		 for (int p = 0; p < indicadores.length(); p++) 
		 {					
			JSONObject indicador = indicadores.getJSONObject(p);					
			String indicadorNombre = indicador.getString("nombre");
			Double indicadorValor = indicador.getDouble("valor");												
			System.out.println(indicadorNombre);
			System.out.println(indicadorValor);
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