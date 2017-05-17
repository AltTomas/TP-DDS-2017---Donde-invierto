package helpers;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileToStringReader
{
  
  public FileToStringReader()
  {
	
  }
  
  public String ReadFile(String filePath)
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