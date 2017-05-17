package helpers;

// JAVA 8 IMPORTS
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileToStringReader
{
  
  public FileToStringReader(){}
  
  public String ReadFile(String filePath)
  {
     try
     {
		Path path = Paths.get(filePath);
		byte[] bytes = Files.readAllBytes(path);	
        String content = new String(bytes);
   		
	    return content;
	 }
	 catch(Exception ex)
	 {
	    return "";
	 }
  }
  
  public boolean SaveFile(String filePath, String content)
  {	 
	 try
	 {
  		Path path = Paths.get(filePath);
		Files.write(path, content.getBytes());
		
		return true;
	 }
	 catch(Exception ex)
	 {
		return false;
	 }
  }
}