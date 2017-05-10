package dominio;

// JUnit
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

// Funciones de DateTime.
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Big Decimal
import java.math.BigDecimal;

public class EmpresaTest 
{
  
  Empresa empresa = null;
  Cuenta cuenta = null;
  Cuenta cuenta2 = null;  
  Periodo periodo = null;
  Periodo periodo2 = null;  
  BigDecimal valor = new BigDecimal(12000);	 
  
  @Before
  public void cargaEmpresa() 
  {	
 
     // Set cuenta.
     LocalDate fechaInicio = LocalDate.parse("2007-01-01");
	 LocalDate fechaFin = LocalDate.parse("2008-01-01");
	 periodo = new Periodo(fechaInicio, fechaFin); 	 	 
     cuenta = new Cuenta("Ahorros", periodo, valor);	
     
	 //	El periodo de la empresa esta comprendido entre este.
	 LocalDate fechaInicio2 = LocalDate.parse("2010-01-01");
	 LocalDate fechaFin2 = LocalDate.parse("2014-01-01");
	 periodo2 = new Periodo(fechaInicio2, fechaFin2); 
	 BigDecimal valor2 = new BigDecimal(12300);	 
	 cuenta2 = new Cuenta("Test", periodo2, valor2);	
	 
	 //Set empresa.
     empresa = new Empresa("Empresa Test");	 	
  }
  
  @Test
  public void testEmpresaAgregarCuentas()
  {	
  	int empresaCuentasInicio = empresa.getCuentas().size();
	empresa.agregarCuenta(cuenta);
    int empresaCuentasAgregadas = empresa.getCuentas().size();	
	empresa.eliminarCuenta(cuenta);
	int empresaCuentasFin = empresa.getCuentas().size();
	
    assertEquals(empresaCuentasInicio, 0);
	assertEquals(empresaCuentasAgregadas, 1);
    assertEquals(empresaCuentasFin, 0);	
  }  

  @Test
  public void testEmpresaValorCalculoCero() 
  {	     
  	empresa.agregarCuenta(cuenta);
    BigDecimal valorCalculo = empresa.getValorCalculo(cuenta2, periodo);
    empresa.eliminarCuenta(cuenta);
    assertEquals(valorCalculo, BigDecimal.ZERO);	
  }

  @Test
  public void testEmpresaValorCalculoPositivo() 
  {	     
  	empresa.agregarCuenta(cuenta);
    BigDecimal valorCalculo = empresa.getValorCalculo(cuenta, periodo);
	empresa.eliminarCuenta(cuenta);
    assertEquals(valorCalculo, valor);	
  }
  
}