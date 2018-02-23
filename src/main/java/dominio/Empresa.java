package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
 
@Entity
@Table(name="empresa")
public class Empresa {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nombre;
	    
	private List<Cuenta> cuentas = new ArrayList<Cuenta>(); 
	
	public Empresa(String paramNombre)
	{
		this.nombre = paramNombre;
	}
 
	public Empresa(){}
	
    @Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    public Long getId()  
    {  
      return id;  
    }  
	  
    public void setId(Long id)  
	{  
      this.id = id;  
	}  
	
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String paramNombre){
		this.nombre = paramNombre;
	}
	
	
	public void agregarCuenta(Cuenta cuenta){
		cuenta.setEmpresa(this);
		this.cuentas.add(cuenta);
	}
	
	@OneToMany(targetEntity=Cuenta.class, mappedBy="empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Cuenta> getCuentas(){
		return this.cuentas;
	}
    
   	public void setCuentas(List<Cuenta> cuentas) {
   		this.cuentas = cuentas;
   	}
	
	public void eliminarCuenta(Cuenta calculo){
		this.cuentas.remove(calculo);
	}
	
}
