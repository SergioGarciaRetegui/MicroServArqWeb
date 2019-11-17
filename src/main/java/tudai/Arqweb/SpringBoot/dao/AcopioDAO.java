package tudai.Arqweb.SpringBoot.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tudai.Arqweb.SpringBoot.model.Acopio;
import tudai.Arqweb.SpringBoot.model.Residuo;

/**
 * Esta clase gestiona el acceso a la base de datos de los acopios, 
 * es el intermediario de las consultas relacionadas a los acopios.
 * 
 * 
 */
public class AcopioDAO {

	private static AcopioDAO daoAcopio;
	private EntityManagerFactory EMF;
	
	private AcopioDAO() {
		EMF = Persistence.createEntityManagerFactory("Practico-Especial");	
	}
	
	/** devuelve una unica instancia de la clase Acopio DAO, si no existe la crea, si ya esta creada devuelve la instancia
	 * 
	 */
	public static AcopioDAO getInstance() {
		if(daoAcopio==null)
			daoAcopio=new AcopioDAO();
		return daoAcopio;
	}
	
	/**
	 * Devuelve un acopio persistido en la base de datos segun un id
	 * 
	 * @param id Identificador unico de un Acopio.
	 */
	public Acopio findById(Integer id) {
		
		EntityManager entityManager=EMF.createEntityManager();
		Acopio acopio=entityManager.find(Acopio.class, id);
		entityManager.close();
		return acopio;
	
	}
	
	/**
	 *Devuelve todos los acopios realizados por un Usuario segun su id 
	 * 
	 * @param id: Corresponde al id del usuario al cual se quieren consultar los acopios realizados
	 * 
	 * @return Retorna una lista de Acopios.
	 */
	public List<Acopio> findByIdUsuario(Integer id) {
        List<Acopio> result=new ArrayList<Acopio>();
 		EntityManager entityManager=EMF.createEntityManager();
		result=entityManager.createQuery("SELECT a FROM Acopio a INNER JOIN a.user u WHERE u.id = :idUser ").setParameter("idUser", id).getResultList();
		entityManager.close();
		return result;
	}
	
	/**
	 * Devuelve una lista de los tipos de residuos depositados por un usuario
	 * 
	 * @param id Corresponde al identificador de un usuario
	 * 
	 * @return List<Residuo> listado de residuos depositados por un usuario
	 * 
	 */
	public List<Residuo>FindTipoDepositadosByID(int id){
		EntityManager entityManager=EMF.createEntityManager();
		List<Residuo> residuos=entityManager.createQuery("SELECT DISTINCT a.reciclable FROM Acopio a INNER JOIN a.user u WHERE u.id= :idUser").setParameter("idUser", id).getResultList();
		entityManager.close();
		return residuos;
		
	}
	
	/**
	 * Devuelve la cantidad de kg depositados de un tipo de residuo de un usuario
	 * 
	 * @param idUser Identificador de un Usuario.
	 * @param idRes Identificador de un residuo.
	 * 
	 * @return retorna un entero que corresponde a la cantidad en kg de un residuo depositado por un usuario.
	 */
	public int cantResidByIdUserAndIdResiduo(int idUser, int idRes){
		EntityManager entityManager=EMF.createEntityManager();
		Long result=(Long) entityManager.createQuery("SELECT SUM(a.cant) FROM Acopio a INNER JOIN a.user u ON (u.id= :idUser) INNER JOIN a.reciclable r ON(r.id=:idRes)").setParameter("idUser", idUser).setParameter("idRes",idRes).getSingleResult();
		entityManager.close();
		return result.intValue();
		
	}

	/**
	 * Devuelve una lista de los tipos de residuos depositados por un usuario entre un rango de fechas dado
	 * 
	 * @param id Corresponde al identificador de un usuario
	 * @param FechaI Fecha desde inicial de la busqueda formato YYYY/MM/DD.
	 * @param FechaF Fecha hasta donde realizar la busqueda formato YYYY/MM/DD.
	 * 
	 * @return List<Residuo> listado de residuos depositados por un usuario
	 * 
	 */
	public List<Residuo>FindTipoDepositadosByIDandFechas(int id,Date FechaI, Date FechaF){
		EntityManager entityManager=EMF.createEntityManager();
		List<Residuo> residuos=entityManager.createQuery("SELECT DISTINCT a.reciclable FROM Acopio a INNER JOIN a.user u WHERE u.id= :idUser AND a.fechaAcopio BETWEEN :f1 AND :f2").setParameter("idUser", id).setParameter("f1",FechaI).setParameter("f2",FechaF).getResultList();
		entityManager.close();
		return residuos;		
	}
	
	/**
	 * Retorna todos los acopios realis�zados por un usuario dentro de un rango de fechas
	 * 
	 * @param id Corresponde al identificador de un usuario
	 * @param FechaI Fecha desde inicial de la busqueda formato YYYY/MM/DD.
	 * @param FechaF Fecha hasta donde realizar la busqueda formato YYYY/MM/DD.
	 */
	public List<Acopio> findByIdUsuarioAndfechas(Integer id,Date FechaI, Date FechaF){
		EntityManager entityManager=EMF.createEntityManager();
		List<Acopio> Acopios=entityManager.createQuery("SELECT a FROM Acopio a INNER JOIN a.user u WHERE u.id= :idUser AND a.fechaAcopio BETWEEN :f1 AND :f2").setParameter("idUser", id).setParameter("f1",FechaI).setParameter("f2",FechaF).getResultList();
		entityManager.close();
		return Acopios;
	}	

	/**
	 * Retorna un listado de todos los residuos depositados en un punto limpio dentro de un rango de fechas.
	 * 
	 * @param id Corresponde al identificador de un Punto Limpio.
	 * @param FechaI Fecha desde inicial de la busqueda formato YYYY/MM/DD.
	 * @param FechaF Fecha hasta donde realizar la busqueda formato YYYY/MM/DD.
	 */
	public List<Residuo> findByIdPuntoLimpioAndfechas(Integer id,Date FechaI, Date FechaF){
		EntityManager entityManager=EMF.createEntityManager();
		List<Residuo> resid=entityManager.createQuery("SELECT a.reciclable FROM Acopio a INNER JOIN a.puntlimpio p WHERE p.id= :idPL AND a.fechaAcopio BETWEEN :f1 AND :f2").setParameter("idPL", id).setParameter("f1",FechaI).setParameter("f2",FechaF).getResultList();
		entityManager.close();
		return resid;
	}	
	
	/**
	 * 	Retorna un listado de todos los acopios realizado en un punto Limpio. 
	 * 
	 * @param id Corresponde al identificador de un Punto Limpio.
	 */

	public List<Acopio> historialByIdPuntoLimpio(Integer id){
		EntityManager entityManager=EMF.createEntityManager();
		List<Acopio> acopios=entityManager.createQuery("SELECT a FROM Acopio a INNER JOIN a.puntlimpio p WHERE p.id= :idPL").setParameter("idPL", id).getResultList();
		entityManager.close();
		return acopios;
	}	

	/**
	 * Persiste en la base de datos un objeto Acopio.
	 * 
	 * @param acopio Es una Instancia de la clase Acopio la cual se quiere persistir en la base
	 * 
	 */
	public Acopio persist(Acopio acopio) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(acopio);
		entityManager.getTransaction().commit();
		entityManager.close();
		return acopio;
	}

	/**
	 * Retorna un listado de todos los acopios persistidos en la base de datos
	 * 
	 */
	public List<Acopio> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		List<Acopio> Acopios=entityManager.createQuery("SELECT a FROM Acopio a").getResultList();
		entityManager.close();
		return Acopios;
	}
	
	/**
	 * Borra todos los acopios persistidos en la base de datos 
	 * 
	 */	
	public int deleteAll() {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		int result=entityManager.createQuery("DELETE FROM Acopio").executeUpdate();
        entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	/**
	 * Borra un Acopio especifico de la base de datos
	 * 
	 * @param id Identificador de un Acopio.
	 */
	public boolean delete(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		int result=entityManager.createQuery("DELETE FROM Acopio a WHERE a.id= :id").setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if (result ==0)
   		    return false;
		else 
			return true;
	}

	/**
	 * Actualiza un acopio en la base de datos
	 * 
	 * @param id identificador de un Acopio.
	 * @param entity Instancia de la clase Acopio la cual contiene los valores a actualizar 
	 */
	public Acopio update(Integer id, Acopio entity) {
		throw new UnsupportedOperationException();
	}



}