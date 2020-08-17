package cl.csantam.controller;

import java.util.List;

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

import cl.csantam.model.dto.UsuarioDto;
import cl.csantam.model.entity.Usuario;
import cl.csantam.service.UsuarioService;


@Controller
@RequestMapping("admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	boolean error = false;
	boolean exito = false;
	String  msg   = "";
	
	
    @Autowired
    private UsuarioService servicio;
    
    @GetMapping
    public String admin(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);
        
        String nombre = servicio.buscarUsuarioPorCorreo(name);
        modelo.addAttribute("nombre", nombre);
        return "admin/index";
    }
    
    @GetMapping("/usuarios")
    public String adminUsuarios(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);
        
        List<Usuario> usuarios = servicio.llenarUsuarios().getUsuarios();
        
        String nombre = servicio.buscarUsuarioPorCorreo(name);
        modelo.addAttribute("nombre", nombre);
        modelo.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }
    
    
   
    @GetMapping("/actualizar")
    public String actualizar(
        ModelMap modelo,
        @RequestParam(name = "id") Integer id
    ) {
    	Usuario usuario = servicio.buscarUsuarioPorId(id).getUsuario();
    	
    	logger.info("rut:" + usuario.getRut());
    	List<Usuario> usuarios = servicio.llenarUsuarios().getUsuarios();
    	
    	modelo.addAttribute("usuario", usuario);
    	modelo.addAttribute("usuarios", usuarios);
        return "admin/usuariosActualizar";
    }
    
    
    
    @PostMapping
    public String usuarioRegistrar( @ModelAttribute Usuario usuario, ModelMap modelo) {
    	logger.info("Pass:" + usuario.getContrasenia());
        UsuarioDto usuarioDto = servicio.registrarUsuario(usuario);
        if(usuarioDto.getUsuario() == null)
            return "admin";

        List<Usuario> usuarios = servicio.llenarUsuarios().getUsuarios();
    	
    	modelo.addAttribute("usuario", usuario);
    	modelo.addAttribute("usuarios", usuarios);
        return "admin/usuariosActualizar";
    }
    
    @PostMapping("/actualizar")
    public String usuarioActualizar( @ModelAttribute Usuario usuario, ModelMap modelo) {
     	logger.info("ACTUALIZAR::" + usuario.getNombre() + " id "+ usuario.getId());
     	
        UsuarioDto usuarioDto = servicio.actualizarUsuario( usuario );
		if (usuarioDto.getUsuario() == null) {
			exito = false;
			msg = "Ocurrio un problema al intentar actualizar";			
		} else {
			exito = true;
			msg = "Usuario actualizado en forma correcta..!!";
		}


        List<Usuario> usuarios = servicio.llenarUsuarios().getUsuarios();
        
    	modelo.addAttribute("exito", exito);
    	modelo.addAttribute("msg", msg);
    	modelo.addAttribute("usuario", usuario);
    	modelo.addAttribute("usuarios", usuarios);
        return "admin/usuariosActualizar";
    }
    
    @GetMapping("/cambioContrasenia")
    public String usuarioCambioPasswordInicio() {
    	return "usuario/cambioContrasenia";
    }
    
    @GetMapping("/eliminar")
    public String usuarioEliminar(
        ModelMap modelo,
        @RequestParam(name = "id") Integer id
    ) {

    	logger.info("Controller Eliminar:" + id);
    	Usuario usuario = servicio.buscarUsuarioPorId(id).getUsuario();
    	
    	if( servicio.eliminarUsuario( usuario ) )  {
    		exito = true;
    		msg = "Usuario eliminado en forma exitosa..!!";
    	} else {
    		exito = false;
    		msg = "Ocurrio un problema al intentar eliminar";
    	}
    	
    	List<Usuario> usuarios = servicio.llenarUsuarios().getUsuarios();
    	
    	
    	modelo.addAttribute("exito", exito);
    	modelo.addAttribute("msg", msg);
    	modelo.addAttribute("usuario", usuario);
    	modelo.addAttribute("usuarios", usuarios);
        //modelo.put("usuarioVo", servicio..obtenerPorId(id));
        //modelo.put("usuarioDto", usuarioDto);
        return "admin/usuariosActualizar";
    }
    

   
}
