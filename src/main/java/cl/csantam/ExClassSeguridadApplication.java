package cl.csantam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import cl.csantam.model.entity.Paciente;
import cl.csantam.model.entity.Prevision;
import cl.csantam.model.entity.Procedimiento;
import cl.csantam.model.entity.Rol;
import cl.csantam.model.entity.Sexo;
import cl.csantam.model.entity.Usuario;
import cl.csantam.service.ProcedimientoService;
import cl.csantam.service.UsuarioService;
import cl.csantam.service.PacienteService;

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
    
    @Autowired
    private PacienteService servicioPaciente;
    
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
        
        
        Paciente paciente = new Paciente();
        paciente.setRut("12345678");
        paciente.setNombre("Nombre Paciente");
        paciente.setCorreo("correo@mail.com");
        paciente.setCiudad("Santiago");
        paciente.setDireccion("Costanera 530");
        paciente.setFechaNacimiento("20-09-1990");
        paciente.setMovil("999888999");
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setPrevision( Prevision.FONASA);
        servicioPaciente.registrarPaciente( paciente );
        
        paciente = new Paciente();
        paciente.setRut("98765432");
        paciente.setNombre("Nombre Paciente 2");
        paciente.setCorreo("otro@mail.com");
        paciente.setCiudad("Santiago");
        paciente.setDireccion("Costanera 530");
        paciente.setFechaNacimiento("20-09-1990");
        paciente.setMovil("999888999");
        paciente.setSexo(Sexo.FEMENINO);
        paciente.setPrevision( Prevision.ISAPRE);
        servicioPaciente.registrarPaciente( paciente );
        
        
        
    }
    
}
