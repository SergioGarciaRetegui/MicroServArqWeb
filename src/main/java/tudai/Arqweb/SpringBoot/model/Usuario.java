package tudai.Arqweb.SpringBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * Define objetos que representan los usarios del sistema los cuales realizaran los acopios de residuos en los Puntolimpios de la ciudad. 
 * ademas de persistir sus datos principales se registra su geoposicion la cual da acceso a varios servicios.
 */
@Entity
public class Usuario {
	@Id
	@GeneratedValue
	int id;
	
	String nombre;	
	String apellido;
	int dni;
    String email;
    String calle;
    int numero;

    /**
     * Datos de Geoposicion de un Camion
     */
	/**
	 * latGeoposicion representa la latitud en geoposicion
	 */
    Double latGeoposicion;
	/**
	 * longGeoposicion representa la longitud en geoposicion
	 */
    Double longGeoposicion;

    
    public Usuario() {
    }
    public Usuario(int id, int dni,String nom, String apellido, String email, String calle, int num, Double  lat,Double Longi) {
      this.id=id;
      this.dni=dni;
      this.nombre=nom;
      this.apellido=apellido;
      this.email=email;
      this.calle=calle;
      this.numero=num;
      this.latGeoposicion=lat;
      this.longGeoposicion=Longi;
    }
    public int getId() {
    	return this.id;
    }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Double getLatGeoposicion() {
		return latGeoposicion;
	}
	public void setLatGeoposicion(Double latGeoposicion) {
		this.latGeoposicion = latGeoposicion;
	}
	public Double getLongGeoposicion() {
		return longGeoposicion;
	}
	public void setLongGeoposicion(Double longGeoposicion) {
		this.longGeoposicion = longGeoposicion;
	}
	public double geoDistancia(Double lat,Double longi) {
		return Math.sqrt(Math.pow(Math.abs(lat-this.getLatGeoposicion()), 2)+Math.pow(Math.abs(longi-this.getLongGeoposicion()), 2));
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email="
				+ email + ", calle=" + calle + ", numero=" + numero + ", latGeoposicion=" + latGeoposicion
				+ ", longGeoposicion=" + longGeoposicion + "]";
	}

}
