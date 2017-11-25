package util;

import dominio.Empresa;
import dominio.Indicador;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;

public class JSONLoader 
{

  private String FilePath = null;  
  private FileToStringReader Reader = null;
  
  public JSONLoader(String filePath, FileToStringReader reader)
  {
	  this.FilePath = filePath;	
	  this.Reader = reader;
  }

  public ArrayList<Empresa> GetEmpresasFromJSONArray()
  {
    String contenidoJSON = this.Reader.ReadFile(this.FilePath);
	
	Gson gson = new Gson();	
	Empresa [] empresas = gson.fromJson(contenidoJSON, Empresa[].class);	
    ArrayList<Empresa> empresasArray = new ArrayList<Empresa>(Arrays.asList(empresas));
	
	return empresasArray;
  }
  
  public boolean IndicadoresToJSONFile(Indicador [] indicadores)
  {	
    Gson gson = new Gson();
	String content = gson.toJson(indicadores);	
	boolean saved = this.Reader.SaveFile(this.FilePath, content);
	
    return saved;
  }
  
  public ArrayList<Indicador> IndicadoresFromJSONFile()
  {	
    String contenido = this.Reader.ReadFile(this.FilePath);

    Gson gson = new Gson();
	Indicador[] arrayIndicadores = gson.fromJson(contenido, Indicador[].class);	
	ArrayList<Indicador> indicadores = new ArrayList<Indicador>(Arrays.asList(arrayIndicadores));
	
    return indicadores;
  }

}