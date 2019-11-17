package tudai.Arqweb.SpringBoot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tudai.Arqweb.SpringBoot.model.PuntoLimpio;

/**
 * Esta clase gestiona el acceso a la base de datos de los Punto Limpio, 
 * es el intermediario de las consultas relacionadas a los Punto Limpio.
 * 
 */
public class PuntoLimpioDAO {

	private static PuntoLimpioDAO daoPuntoLimpio;
	private EntityManagerFactory EMF;

	private PuntoLimpioDAO() {
		EMF = Persistence.createEntityManagerFactory("Practico-Especial");	
	}

	/** 
	* devuelve una unica instancia de la clase PuntoLimpioDAO, si no existe la crea, si ya esta creada devuelve la instancia
	* 
	*/
	public static PuntoLimpioDAO getInstance() {
		if(daoPuntoLimpio==null)
			daoPuntoLimpio=new PuntoLimpioDAO();
		return daoPuntoLimpio;
	}

	/**
	 * Devuelve un PuntoLimpio persistido en la base de datos segun un id
	 * 
	 * @param id Identificador unico de un PuntoLimpio.
	 */
	public PuntoLimpio findById(Integer id) {
		
		EntityManager entityManager=EMF.createEntityManager();
		PuntoLimpio Pl=entityManager.find(PuntoLimpio.class, id);
		entityManager.close();
		return Pl;
	
	}

	/**
	 * Persiste en la base de datos un objeto PuntoLimpio.
	 * 
	 * @param camion Es una Instancia de la clase PuntoLimpio la cual se quiere persistir en la base
	 * 
	 */
	public PuntoLimpio persist(PuntoLimpio pl) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(pl);
		entityManager.getTransaction().commit();
		entityManager.close();
		return pl;
	}

	/**
	 * Retorna un listado de todos los PuntoLimpio persistidos en la base de datos
	 * 
	 */
	public List<PuntoLimpio> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		List<PuntoLimpio> PuntoLimpios=entityManager.createQuery("SELECT p FROM PuntoLimpio p").getResultList();
		entityManager.close();
		return PuntoLimpios;
	}

	/**
	 * Verifica si un PuntoLimpio llego al limite de su capacidad de almacenamiento
	 * 
	 * @return Retorna True si el limite fue alcanzado o False en caso contario.
	 */
	public boolean capacidadAlcanzada(int id) {
		EntityManager entityManager=EMF.createEntityManager();
		PuntoLimpio pl=entityManager.find(PuntoLimpio.class, id);
		entityManager.close();
		return pl.getKgAcumulados()>= pl.getKgTope();
	}

	/**
	 * Borra todos los PuntoLImpio persistidos en la base de datos 
	 * 
	 */	
	public int deleteAll() {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		int result=entityManager.createQuery("DELETE FROM PuntoLimpio").executeUpdate();
        entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	/**
	 * Borra un PuntoLimpio especifico de la base de datos
	 * 
	 * @param id Identificador de un PuntoLimpio.
	 */
	public boolean delete(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		PuntoLimpio pl=entityManager.find(PuntoLimpio.class, id);
		entityManager.getTransaction().begin();
        entityManager.remove(pl);
        entityManager.clear();
        entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	/**
	 * Actualiza un PuntoLimpio en la base de datos
	 * 
	 * @param id identificador de un PuntoLimpio.
	 * @param entity Instancia de la clase PuntoLimpio la cual contiene los valores a actualizar 
	 */
	public PuntoLimpio update(Integer id, PuntoLimpio entity) {
		throw new UnsupportedOperationException();
	}


}
