package tudai.Arqweb.SpringBoot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tudai.Arqweb.SpringBoot.model.Residuo;

/**
 * Esta clase gestiona el acceso a la base de datos de los Residuos, 
 * es el intermediario de las consultas relacionadas a los Residuos.
 * 
 */
public class ResiduoDAO{

	private static ResiduoDAO daoRes;
	private EntityManagerFactory EMF;

	private ResiduoDAO() {
		EMF=Persistence.createEntityManagerFactory("Practico-Especial");
	}

	/** 
	* Devuelve una unica instancia de la clase ResiduoDAO, si no existe la crea, si ya esta creada devuelve la instancia
	* 
	*/
	public static ResiduoDAO getInstance() {
		if(daoRes==null)
			daoRes=new ResiduoDAO();
		return daoRes;
	}

	/**
	 * Persiste en la base de datos un objeto Residuo.
	 * 
	 * @param res Es una Instancia de la clase Residuo la cual se quiere persistir en la base
	 * 
	 * @return Residuo Retorna el residuo persistido.
	 */
	public Residuo persist(Residuo res) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(res);
		entityManager.getTransaction().commit();
		entityManager.close();
		return res;
	}

	/**
	 * Devuelve un residuo persistido en la base de datos segun un id
	 * 
	 * @param id Identificador unico de un Residuo.
	 */
	public Residuo findById(Integer id) {
		
		EntityManager entityManager=EMF.createEntityManager();
		Residuo res=entityManager.find(Residuo.class, id);
		entityManager.close();
		return res;
	
	}
	
	/**
	 * Retorna un listado de todos los Residuos persistidos en la base de datos
	 * 
	 */
	public List<Residuo> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		List<Residuo> residuos=entityManager.createQuery("SELECT r FROM Residuo r").getResultList();
		entityManager.close();
		return residuos;
	}
	
	/**
	 * Borra todos los Residuos persistidos en la base de datos 
	 * 
	 */	
	public int deleteAll() {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		int result=entityManager.createQuery("DELETE FROM Residuo").executeUpdate();
        entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	/**
	 * Borra un Residuo especifico de la base de datos
	 * 
	 * @param id Identificador de un Residuo.
	 */
	public boolean delete(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		int result=entityManager.createQuery("DELETE FROM Residuo r WHERE r.id= :id").setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if (result ==0)
   		    return false;
		else 
			return true;
	}

	/**
	 * Actualiza un Residuo en la base de datos
	 * 
	 * @param id identificador de un Residuo.
	 * @param entity Instancia de la clase Residuo la cual contiene los valores a actualizar 
	 */
	public Residuo update(Integer id, Residuo entity) {
		throw new UnsupportedOperationException();
	}


}
