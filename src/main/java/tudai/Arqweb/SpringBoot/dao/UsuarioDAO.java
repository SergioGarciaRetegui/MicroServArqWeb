package tudai.Arqweb.SpringBoot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import tudai.Arqweb.SpringBoot.model.Usuario;

/**
 * Esta clase gestiona el acceso a la base de datos de los Usuarios, 
 * es el intermediario de las consultas relacionadas a los Usuarios.
 * 
 * 
 */
public class UsuarioDAO{

	private static UsuarioDAO daoUsuario;
	private EntityManagerFactory EMF;

	private UsuarioDAO() {
		EMF = Persistence.createEntityManagerFactory("Practico-Especial");
	}

	/** 
	* Devuelve una unica instancia de la clase UsuarioDAO, si no existe la crea, si ya esta creada devuelve la instancia
	* 
	*/
	public static UsuarioDAO getInstance() {
		if(daoUsuario==null)
			daoUsuario=new UsuarioDAO();
		return daoUsuario;
	}
	/**
	 * Persiste en la base de datos un objeto Usuario.
	 * 
	 * @param user Es una Instancia de la clase Usuario la cual se quiere persistir en la base
	 * 
	 */
	public Usuario persist(Usuario user) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		return user;
	}

	/**
	 * Devuelve un usuario persistido en la base de datos segun un id
	 * 
	 * @param id Identificador unico de un Usuario.
	 */
	public Usuario findById(Integer id) {
		
		EntityManager entityManager=EMF.createEntityManager();
		Usuario user=entityManager.find(Usuario.class, id);
		entityManager.close();
		return user;
	}

	/**
	 * Retorna un listado de todos los Usuarios persistidos en la base de datos
	 * 
	 */
	public List<Usuario> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		List<Usuario> usuarios=entityManager.createQuery("SELECT u FROM Usuario u").getResultList();
		entityManager.close();
		return usuarios;
	}
	
	
	
	/**
	 * Borra todos los Usuarios persistidos en la base de datos 
	 * 
	 */	
	public int deleteAll() {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		int result=entityManager.createQuery("DELETE FROM Usuario").executeUpdate();
        entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	/**
	 * Borra un Usuario especifico de la base de datos
	 * 
	 * @param id Identificador de un Usuario.
	 */
	public boolean delete(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		int result=entityManager.createQuery("DELETE FROM Usuario u WHERE u.id= :id").setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if (result ==0)
   		    return false;
		else 
			return true;
	}

	
	/**
	 * Actualiza un Usuario en la base de datos
	 * 
	 * @param id identificador de un Usuario.
	 * @param entity Instancia de la clase Usuario la cual contiene los valores a actualizar 
	 */
	public Usuario update(Integer id, Usuario entity) {
		throw new UnsupportedOperationException();
	}


}
