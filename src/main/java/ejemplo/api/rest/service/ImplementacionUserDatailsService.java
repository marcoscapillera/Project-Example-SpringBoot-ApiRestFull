package ejemplo.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ejemplo.api.rest.model.Usuario;
import ejemplo.api.rest.repository.UsuarioRepository;


@Service
public class ImplementacionUserDatailsService  implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*Consultar en la BD el usuario*/
		
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
			
		}
				
		
		return new User(usuario.getLogin(),
						usuario.getPassword(),
						usuario.getAuthorities());
	}

}
