package ejemplo.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ejemplo.api.rest.model.Usuario;

@Repository
public interface TelefonoRepository  extends CrudRepository<Usuario, Long>{
	
	
}
	
	

