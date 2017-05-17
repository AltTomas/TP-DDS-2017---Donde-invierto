package helpers;

import dominio.Empresa;
import dominio.Indicador;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.util.Arrays;

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

}