package cl.csantam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import cl.csantam.model.entity.Procedimiento;
import cl.csantam.model.entity.Rol;
import cl.csantam.model.entity.Usuario;
import cl.csantam.service.ProcedimientoService;
import cl.csantam.service.UsuarioService;

@SpringBootApplication
public class ExClassSeguridadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExClassSeguridadApplication.class, args);
	}

}

@Component
class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private UsuarioService servicioUsuario;
    
    @Autowired
    private ProcedimientoService servicioProcedimiento;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("administrador");
        usuario.setCorreo("admin@mail.cl");
        usuario.setContrasenia("1234");
        //usuario.setContrasenia( EncoderUtils.passwordEncoder().encode("1234"));
        usuario.setRut("123456789-0"); 
        usuario.setRol(Rol.ROLE_ADMIN);
        
        servicioUsuario.registrarUsuario(usuario);
        
        usuario = new Usuario();
        usuario.setRut("1234567-0");
        usuario.setNombre("usuario");
        usuario.setCorreo("user@mail.cl");
        
        usuario.setContrasenia("1234");
        //usuario.setContrasenia( EncoderUtils.passwordEncoder().encode("1234"));
        usuario.setRol(Rol.ROLE_USER);
        servicioUsuario.registrarUsuario(usuario);
        
        Procedimiento procedimiento = new Procedimiento();
        procedimiento.setCodProcedimiento("PRO-001");
        procedimiento.setNombre("Nombre Procedimiento");
        procedimiento.setDescripcion("Descripcion....");
        procedimiento.setPrecio( 20000);
        procedimiento.setEstado(1);
        
        servicioProcedimiento.registrarProcedimiento( procedimiento );
        
        
        procedimiento = new Procedimiento();
        procedimiento.setCodProcedimiento("PRO-002");
        procedimiento.setNombre("Nombre Procedimiento 2");
        procedimiento.setDescripcion("Descripcion....2 ");
        procedimiento.setPrecio( 50000);
        procedimiento.setEstado(1);
        
        servicioProcedimiento.registrarProcedimiento( procedimiento );
        
        
    }
    
}
