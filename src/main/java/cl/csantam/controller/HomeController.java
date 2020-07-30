package cl.csantam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.csantam.service.UsuarioService;

@Controller
@RequestMapping("home")
public class HomeController {
    
    @Autowired
    private UsuarioService servicio;
    
    @GetMapping
    public String home(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
       
        String name = auth.getName();
        String nombre = servicio.buscarUsuarioPorCorreo( name );
        modelo.addAttribute("username", name);
        modelo.addAttribute("nombre", nombre);
        // capturo el nombre de usuario
        modelo.put("usuarioDto", servicio.llenarUsuarios());
         
      
        //return "home/index";
        return "usuario/index";
    }
}
