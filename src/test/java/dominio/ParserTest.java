package dominio;

public class ParserTest {
	
	String filePath = "/home/dds/dds-utn/2017-mn-group-04/src/test/resources/indicadores.json";
	FileToStringReader reader = new FileToStringReader();
	JSONLoader loader = new JSONLoader(filePath, reader);
	ArrayList<Indicador> indicadores = loader.IndicadoresFromJSONFile();
	Empresa empresa =  new Empresa();
	empresa.Nombre = "empresa2";
	AnalizadorLexico Parser = new AnalizadorLexico();
			
	For (Indicador indicador : indicadores)
	{
		Parser.main(indicador.formula);
		
	}	
}