package cl.csantam.controller;





import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.csantam.config.EncoderUtils;
import cl.csantam.model.dto.UsuarioDto;
import cl.csantam.model.entity.Usuario;
import cl.csantam.service.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
    private UsuarioService servicio;
    
    @GetMapping
    public String admin(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);
        
        logger.info("USER:"+ name);
        String nombre = servicio.buscarUsuarioPorCorreo(name);
        UsuarioDto  usuarioDto =  servicio.buscarUsuarioDtoPorCorreo( name );
        String passLogin = usuarioDto.getUsuario().getContrasenia();
        logger.info("PWD:"+ passLogin);
        
        modelo.addAttribute("nombre", nombre);
        String passInicio = EncoderUtils.passwordEncoder().encode("1234");
        logger.info("PWD_inicio:"+ passInicio);
        logger.info("nombre:" + nombre);
        if(  passLogin.equals( passInicio )) {
        	
        	return "admin/cambioContrasenia";
        } else  {
        	return "usuario/index";
        }
       
        
    }
    
    @PostMapping
    public String usuario( @ModelAttribute Usuario usuario) {
        UsuarioDto usuarioDto = servicio.registrarUsuario(usuario);
        if(usuarioDto.getUsuario() == null)
            return "usuario";

        return "redirect:home";
    }

    
    @GetMapping("/cambioContrasenia")
    public String usuarioCambioPasswordInicio(HttpSession session ,  ModelMap modelo ) {
    	Integer id = (Integer) session.getAttribute("id");
    	modelo.put("id", id);
    	return "usuario/cambioContrasenia";
    }
    
    
    @PostMapping("/cambioContrasenia")
    public String usuarioCambioContrasenia( 
    	 HttpSession session,
         ModelMap modelo, 
         @RequestParam("id") Integer id,
         @RequestParam("password") String pass,
         @RequestParam("password_") String pass_) {
    	
         String correo = session.getAttribute( "correo").toString();
         
         logger.info("Id:" + id);
         logger.info("Nombre:" + session.getAttribute("nombre"));
    	 logger.info("CAMBIO-CONTRASENIA::" + pass + " pass_ "+  pass_);
     	 logger.info("Sesion:::" + correo );
     	
     	 if( !pass.equals( pass_)) {
     		logger.info("PASSS: password no son iguales");
     		modelo.put("error", true);
     		modelo.put("msg", "Las contraseñas no son iguales, favor reintentar.");
     		return "usuario/cambioContrasenia";
     	 } 
     	 String new_pass = EncoderUtils.passwordEncoder().encode( pass );
     	 Usuario  usuario = servicio.buscarUsuarioPorId(id).getUsuario();
     	 
     	 usuario.setContrasenia( new_pass);
     	 UsuarioDto  usuarioDto = servicio.actualizarUsuario(usuario);
     	 if( usuarioDto != null ) {
     		modelo.put("info", true);
     		modelo.put("msg", "Su contraseña fue actualizada en forma correcta..!!");
     	 } else {
     		modelo.put("error", true);
     		modelo.put("msg", "Ocurrio un problema al intentar cambiar su contraseña.");
     	 }
     	 
     	 return "usuario/cambioContrasenia";
    }

    

}
