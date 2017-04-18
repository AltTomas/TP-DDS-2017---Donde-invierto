package helpers;

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
	///ArrayList<PuntoDeInteres> ListaPOI = new ArrayList<>();

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
		    //Int valor = indicador.getJSONString("valor");														
			System.out.println(indicadorNombre);
		 }
	  }		
	}

	return "";	  
  }
  
  private String ReadFile(String filePath)
  {
	return "[{\"nombre\": \"empresa1\",\"indicadores\": [{\"nombre\": \"indicador1\",\"valor\": 150}]},{\"nombre\": \"empresa2\",\"indicadores\": [{\"nombre\": \"indicador40\",\"valor\": 2000}]}]";
  }
}