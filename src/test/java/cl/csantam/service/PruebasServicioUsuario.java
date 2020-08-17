package cl.csantam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.javafaker.Faker;

import cl.csantam.model.dao.UsuarioDao;
import cl.csantam.model.dto.UsuarioDto;
import cl.csantam.model.entity.Usuario;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;



@SpringBootTest
public class PruebasServicioUsuario {
	
	 private static Usuario usuario;
	 private static UsuarioDto usuarioDto;
	 private static List<Usuario> usuarios;
	
	@MockBean
    UsuarioDao usuarioDao;
	
	@Autowired
	UsuarioService usuarioService;
	
	 private static final Faker faker = new Faker(new Locale("es-ES"));
	    
	@BeforeAll
	static void fixture() {
		usuario = new Usuario();
		usuario.setId(faker.number().randomDigit());
		usuario.setNombre(faker.name().name());
		usuario.setCorreo(faker.internet().emailAddress());
		usuario.setContrasenia(faker.internet().password());
		usuario.setRut(faker.number().toString());

		usuarioDto = new UsuarioDto();
		usuarioDto.setUsuario(usuario);
		usuarios = new ArrayList<>();
		usuarios.add(usuario);
	}
	


	  @Test
	  @DisplayName("Prueba de registro de un usuario en el sistema")
	  void pruebaDeRegistro() {
	        when(usuarioDao.save(usuarioDto.getUsuario())).thenReturn(usuario);
	        assertEquals(usuarioDto ,usuarioService.registrarUsuario(usuario));
	   }
	  
	  
	   @Test
	   @DisplayName("Prueba si se listan los usuarios del sistemas")
	   public void pruebaListarUsuarios() {
	        when(usuarioDao.findAll()).thenReturn(usuarios);
	        assertEquals(usuarios, usuarioService.llenarUsuarios().getUsuarios());
	   }
	   
	   @Test
	    @DisplayName("Prueba si podemos eliminar el usuario")
	    public void pruebaEliminarUsuarioPorId() {
			 usuarioService.eliminarUsuario(usuario);
	    }
	   
	   @Test
	   @DisplayName("Prueba si podemos actualizar el usuario")
	   public void pruebaActualizarUsuario() {
	        
            
	        when(usuarioDao.save( usuario )).thenReturn(usuario);
	        usuarioDto =  usuarioService.actualizarUsuario(usuario);
	        assertNotNull( usuarioDto );
	   }
    

}
