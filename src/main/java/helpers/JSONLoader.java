package helpers;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import dominio.Empresa;
import dominio.Periodo;
import dominio.Cuenta;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.util.Arrays;

public class JSONLoader 
{
  
  private String FilePath = null;  
  
  public JSONLoader(String filePath)
  {
	  this.FilePath = filePath;	
  }

  public ArrayList<Empresa> GetEmpresasFromJSONArray()
  {
    String contenidoJSON = this.ReadFile(this.FilePath);
	
	// GSON deserialization library.
	Gson gson = new Gson();	
	Empresa [] empresas = gson.fromJson(contenidoJSON, Empresa[].class);	
    ArrayList<Empresa> empresasArray = new ArrayList<Empresa>(Arrays.asList(empresas));
	
	return empresasArray;
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