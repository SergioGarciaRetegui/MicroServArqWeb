package tudai.Arqweb.SpringBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * Esta clase define objetos que representa los PuntoLimpio en donde depositar los residuos
 * de los cuales es importante registarar su geoposicion, capacidad de almacenamiento como atributos principales  
 */
@Entity
public class PuntoLimpio {

	@Id
	@GeneratedValue
	int id;

	
	String nombre;
	int kgTope;
	int KgAcumulados;
	String Calle;
	int numero;

	/**
     * Datos de Geoposicion de un PuntoLimpio
     */
	/**
	 * latGeoposicion representa la latitud en geoposicion
	 */

    Double latGeoposicion;
	/**
	 * longGeoposicion representa la longitud en geoposicion
	 */
    Double longGeoposicion;

	public PuntoLimpio() {
		
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
	public void setKgTope(int kgTope) {
		this.kgTope=kgTope;
	}

	public int getKgTope() {
		return this.kgTope;
	}
	public int getKgAcumulados() {
		return KgAcumulados;
	}
	public void setKgAcumulados(int kgAcumulados) {
		KgAcumulados += kgAcumulados;
	}
	public void setKgAcumuladosaCero() {
		this.KgAcumulados=0;
	}
	public String getCalle() {
		return Calle;
	}
	public void setCalle(String calle) {
		Calle = calle;
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
	public void setLatGeoposicion(Double latgeoposicion) {
		latGeoposicion = latgeoposicion;
	}
	public Double getLongGeoposicion() {
		return longGeoposicion;
	}
	public void setLongGeoposicion(Double longeoposicion) {
		longGeoposicion = longeoposicion;
	}
	public double geoDistancia(Double lat,Double longi) {
		return Math.sqrt(Math.pow(Math.abs(lat-this.getLatGeoposicion()), 2)+Math.pow(Math.abs(longi-this.getLongGeoposicion()), 2));
	}

	@Override
	public String toString() {
		return "PuntoLimpio [id=" + id + ", nombre=" + nombre + ", kgTope=" + kgTope + ", KgAcumulados=" + KgAcumulados
				+ ", Calle=" + Calle + ", numero=" + numero + ", Latgeoposicion=" + latGeoposicion + ", Longeoposicion="
				+ longGeoposicion + "]";
	}
}
