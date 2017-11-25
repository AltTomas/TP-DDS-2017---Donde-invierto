package util;

import java.util.ArrayList;
import dominio.Indicador;
import dominio.Periodo;

public final class IndicadorHelper
{
	private IndicadorHelper() {}
	
	public static ArrayList<Indicador> IndicadoresPorPeriodo(ArrayList<Indicador> lista, Periodo periodo)
    {
       ArrayList<Indicador> result = new ArrayList<Indicador>();
   
       for (Indicador indicador : lista)
       { 
	      if(indicador.estaEnPeriodo(periodo))
	      {
		    result.add(indicador);   
	      }
       }
   
       return result;
    }	
}