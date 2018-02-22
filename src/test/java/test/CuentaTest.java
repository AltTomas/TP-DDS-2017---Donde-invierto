//package test;
//
//// JUnit
//import org.junit.Test;
//import org.junit.Before;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertEquals;
//
//import dominio.Cuenta;
//import dominio.Periodo;
//
//// Funciones de DateTime.
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//
//// Big Decimal
//import java.math.BigDecimal;
//
//public class CuentaTest 
//{
//  
//  Periodo periodo = null;
//  Periodo periodo2 = null;
//  Periodo periodo3 = null;
//  Cuenta cuenta = null;
//  
//  @Before
//  public void cargaCuentas() 
//  {	
//	  
//     cuenta = new Cuenta("Ahorros", "2007-01-01", "2008-01-01", 12000);	 
//	 
//	 //	El periodo de la empresa esta comprendido entre este.
//	 LocalDate fechaInicio2 = LocalDate.parse("2010-01-01");
//	 LocalDate fechaFin2 = LocalDate.parse("2014-01-01");
//	 periodo2 = new Periodo(fechaInicio2, fechaFin2); 
//	 
//	 //	El periodo de la empresa no esta comprendido entre este.
//	 LocalDate fechaInicio3 = LocalDate.parse("2007-01-02");
//	 LocalDate fechaFin3 = LocalDate.parse("2007-01-03");
//	 periodo3 = new Periodo(fechaInicio3, fechaFin3); 
//	 
//  }
//  
//  @Test
//  public void testCuentaCalcularCero()
//  {
//	BigDecimal valor = cuenta.calcular(periodo2);
//    assertEquals(valor, BigDecimal.ZERO);
//  }  
//
//  @Test
//  public void testCuentaCalcularValor() 
//  {	     
//    BigDecimal valorEsperado = new BigDecimal(12000);
//	BigDecimal valor = cuenta.calcular(periodo3);
//    assertEquals(valor, valorEsperado);	
//  }
//  
//}