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

import cl.csantam.model.dto.PacienteDto;
import cl.csantam.model.dto.UsuarioDto;
import cl.csantam.model.entity.Paciente;
import cl.csantam.model.entity.Usuario;
import cl.csantam.service.PacienteService;
import cl.csantam.service.UsuarioService;

@Controller
@RequestMapping("pacientes")
public class PacientesController {
	private static final Logger logger = LoggerFactory.getLogger(PacientesController.class);
	
	@Autowired
    private PacienteService servicio;
	
	
	@GetMapping
    public String index(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);
        
//        String nombre = servicio.buscarUsuarioPorCorreo(name);
//        modelo.addAttribute("nombre", nombre);
        return "pacientes/index";
    }
	
	
	@PostMapping
    public String registrarPaciente( @ModelAttribute Paciente paciente, ModelMap modelo) {
    	logger.info("Pass:" + paciente.getNombre());
        PacienteDto pacienteDto = servicio.registrarPaciente( paciente );
        if( pacienteDto.getPaciente()  == null)
            return "admin";

        //return "redirect:home"; 
        List<Paciente> pacientes = servicio.llenarPacientes().getPacientes();
    	
    	modelo.addAttribute("paciente", paciente );
    	modelo.addAttribute("pacientes", pacientes);
        return "pacientes/pacientesActualizar";
    }

}
