package ejemplo.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.RepaintManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ejemplo.api.rest.model.Telefono;
import ejemplo.api.rest.model.Usuario;
import ejemplo.api.rest.repository.TelefonoRepository;
import ejemplo.api.rest.repository.UsuarioRepository;



@CrossOrigin(origins="https://ejemplospringrestapi.herokuapp.com/ejemplospringrestapi/")
@RestController /*Arquitectura REST*/
@RequestMapping(value = "/usuario2")
public class IndexController2 {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TelefonoRepository telefonoRepository;
	
	
	/*Restfull*/
	
	@GetMapping(value = "/{id}/codigoventa/{venta}", produces = "application/json")
	public ResponseEntity<Usuario> informex(@PathVariable (value="id") Long id
			                               ,@PathVariable (value="venta") Long venta) {
		

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		
		/*El return seria un informe*/
		return  new ResponseEntity<Usuario>(usuario.get(),HttpStatus.OK);
	}
	
	/*Restfull*/
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable (value="id") Long id) {
		

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return  new ResponseEntity<Usuario>(usuario.get(),HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/{id}/venta", produces = "application/json")
	public String  deletVenta(@PathVariable ("id") Long id) {
		

		usuarioRepository.deleteById(id); /*Borra todas las ventas del usuario*/
		
		return  "OK";
	}
	
	@DeleteMapping(value="/{id}", produces="application/text")
	public String delete (@PathVariable ("id") Long id) {
		
		
		usuarioRepository.deleteById(id);
		
		return "ok";
		
	}
	
	@DeleteMapping(value="/{id}/venta", produces="application/text")
	public String deleteVenta (@PathVariable ("id") Long id) {
		
		
		usuarioRepository.deleteById(id);
		
		return "ok";
		
	}
	
	
	@GetMapping(value="/", produces="application/json")
	public ResponseEntity<List<Usuario>> usuario(){
		
		
		List<Usuario> list = (List<Usuario>)usuarioRepository.findAll();
		
		return new  ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
		
	}
	
	
	@PutMapping (value="/", produces ="application/json")
	public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario){
		
		/*Otras rutinas antes de actualizar*/
		
		
		
		for(int pos=0; pos < usuario.getTelefonos().size(); pos++) {
			usuario.getTelefonos().get(pos).setUsuario(usuario);
		}
		
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@PostMapping (value="/", produces ="application/json")
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
		
		for(int pos=0; pos < usuario.getTelefonos().size(); pos++) {
			usuario.getTelefonos().get(pos).setUsuario(usuario);
		}
		
		
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	

	
	
	@PutMapping (value="/{iduser}/idventa/{idventa}", produces ="application/json")
	public ResponseEntity  updateVenta(@PathVariable Long iduser, 
										 @PathVariable Long idventa ){
		
		
		
		/*Algoritmo de venta*/
		//Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity("Venta actualizada", HttpStatus.OK);
	}
	
	@PostMapping (value="/{iduser/idventa/{idventa}", produces ="application/json")
	public ResponseEntity<Usuario> registrarVenta(@RequestBody Usuario usuario){
		
		for(int pos=0; pos < usuario.getTelefonos().size(); pos++) {
			usuario.getTelefonos().get(pos).setUsuario(usuario);
		}
		
		
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	

	
}
