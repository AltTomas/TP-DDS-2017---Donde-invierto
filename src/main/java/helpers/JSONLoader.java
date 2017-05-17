package helpers;

import dominio.Empresa;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.util.Arrays;
import helpers.FileToStringReader;

public class JSONLoader 
{

  private String FilePath = null;  
  private FileToStringReader reader = null;
  
  public JSONLoader(String filePath, FileToStringReader reader)
  {
	  this.FilePath = filePath;	
	  this.FileReader = reader;
  }

  public ArrayList<Empresa> GetEmpresasFromJSONArray()
  {
    String contenidoJSON = this.FileReader.ReadFile(this.FilePath);
	
	// GSON deserialization library.
	Gson gson = new Gson();	
	Empresa [] empresas = gson.fromJson(contenidoJSON, Empresa[].class);	
    ArrayList<Empresa> empresasArray = new ArrayList<Empresa>(Arrays.asList(empresas));
	
	return empresasArray;
  }

}