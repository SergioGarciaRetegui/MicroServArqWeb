package tudai.Arqweb.SpringBoot;

import java.sql.Date;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tudai.Arqweb.SpringBoot.dao.AcopioDAO;
import tudai.Arqweb.SpringBoot.model.Acopio;

@RestController
@RequestMapping("/microServ/Usuario")
public class UserCrontroller {

	/**
	 * Retorna un listado de acopios realizados por un usuario 
	 * 
	 * @param id  Identificador de un Usuario en la Base de datos
	 * 
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<Acopio> AcopiosPorUsuario(@PathVariable String id){
		int idUser = Integer.valueOf(id);
		List<Acopio> result= AcopioDAO.getInstance().findByIdUsuario(idUser);
		if(result!=null)
			return result;
		else
			throw null;
	}

	/**
	 * Retorna un listado de acopios realizados por un usuario dentro de un rango de fechas. 
	 * 
	 * @param id  Identificador de un Usuario en la Base de datos
	 * @param FeI Fecha desde donde comienza la busqueda de acopios realizados
	 * @param FeF Fecha hasta donde termina la busqueda de acopios realizados
	 * 
	 */
	@RequestMapping(value = "/{id}/{FeI}/{FeF}", method = RequestMethod.GET, produces = "application/json")
	public List<Acopio> AcopiosPorUsuarioAndFechas(@PathVariable String id,@PathVariable String FeI,@PathVariable String FeF) {
		int idUser = Integer.valueOf(id);
		Date FechaIni= Date.valueOf(FeI);
		Date FechaFin= Date.valueOf(FeF);
		List<Acopio> result= AcopioDAO.getInstance().findByIdUsuarioAndfechas(idUser, FechaIni, FechaFin);
		if(result!=null)
			return result;
		else
			throw null;
	}
	
	
	
}
