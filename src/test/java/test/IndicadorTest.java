package test;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

// Funciones de DateTime.
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import util.IndicadorHelper;

import dominio.Periodo;
import dominio.Indicador;

public class IndicadorTest 
{
   
  Periodo periodo = null;   
  Indicador indicador,indicador2 = null;
  ArrayList<Indicador> indicadores = new ArrayList<Indicador>();
  
  @Before
  public void cargaIndicador() 
  {	
     LocalDate fechaInicio = LocalDate.parse("2007-01-01");
	 LocalDate fechaFin = LocalDate.parse("2008-01-01");
	 periodo = new Periodo(fechaInicio, fechaFin);  
	 
	 indicador = new Indicador("Indicador Test", periodo, "");	 
	 
	 LocalDate fechaInicio2 = LocalDate.parse("2010-01-01");
	 LocalDate fechaFin2 = LocalDate.parse("2010-01-01");
	 Periodo periodo2 = new Periodo(fechaInicio2, fechaFin2);  
	 Indicador indicador2 = new Indicador("Indicador Test", periodo2, "");
	 
	 indicadores.add(indicador);
	 indicadores.add(indicador2);
  }

  // TODO: el calculo en el futuro deberia tener sentido y no ser cero.
  @Test
  public void testCalcular()
  {	
	BigDecimal valor = indicador.calcular(periodo);
	assertEquals(BigDecimal.ZERO, valor);
  }
  
  @Test
  public void testIndicadoresPorPeriodo()
  {
    LocalDate fechaInicioTest = LocalDate.parse("2007-01-01");	
	LocalDate fechaFinTest = LocalDate.parse("2008-01-01");
	Periodo periodoTest = new Periodo(fechaInicioTest, fechaFinTest);  
	 
    ArrayList<Indicador> result = IndicadorHelper
	                    .IndicadoresPorPeriodo(indicadores, periodoTest);
	
	assertEquals(1, result.size());
  }
  
}