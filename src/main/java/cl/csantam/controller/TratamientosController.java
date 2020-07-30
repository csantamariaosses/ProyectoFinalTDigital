package cl.csantam.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tratamientos")
public class TratamientosController {
	@GetMapping
    public String admin(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);
        
//        String nombre = servicio.buscarUsuarioPorCorreo(name);
//        modelo.addAttribute("nombre", nombre);
        return "tratamientos/index";
    }

}
