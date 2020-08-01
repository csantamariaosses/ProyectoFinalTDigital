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

import cl.csantam.model.dto.PacienteDto;
import cl.csantam.model.entity.Paciente;
import cl.csantam.service.PacienteService;

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
        
        List<Paciente> pacientes = servicio.llenarPacientes().getPacientes();
       // logger.info( pacientes.toString() );
        modelo.addAttribute("pacientes", pacientes);
        modelo.addAttribute("username", name);
        
//        String nombre = servicio.buscarUsuarioPorCorreo(name);
//        modelo.addAttribute("nombre", nombre);
        return "pacientes/index";
    }
	
	
	    @PostMapping
	    public String registraPacientes( @ModelAttribute Paciente paciente, ModelMap modelo) {
	    	
	        PacienteDto pacienteDto = servicio.registrarPaciente( paciente );
	        if( pacienteDto.getPaciente() == null)
	            return "admin";

	        //return "redirect:home";
	        List<Paciente> pacientes = servicio.llenarPacientes().getPacientes();
	    	
	    	modelo.addAttribute("paciente", paciente );
	    	modelo.addAttribute("pacientes", pacientes );
	        return "pacientes/pacientesActualizar";
	  }
	    
	    @GetMapping("/actualizar")
	    public String actualizar(
	        ModelMap modelo,
	        @RequestParam(name = "id") Integer  id
	    ) {
	    	Paciente  paciente = servicio.buscarPacientePorId(id).getPaciente();
	    	
	    	logger.info("FechaNac:" + paciente.getFechaNacimiento());
	    	logger.info("Paciente:" + paciente.getNombre());
	    	List<Paciente> pacientes = servicio.llenarPacientes().getPacientes();
	    	
	    	modelo.addAttribute("paciente", paciente );
	    	modelo.addAttribute("pacientes", pacientes);
	        //modelo.put("usuarioVo", servicio..obtenerPorId(id));
	        //modelo.put("usuarioDto", usuarioDto);
	        return "pacientes/pacientesActualizar";
	    }
	    
	    
	    @PostMapping("/actualizar")
	    public String pacienteActualizar( @ModelAttribute Paciente  paciente, ModelMap modelo) {
	     	logger.info("ACTUALIZAR::" + paciente.getNombre() + " id "+ paciente.getId());
	     	
	        PacienteDto pacienteDto = servicio.actualizarPaciente( paciente );
	        if( pacienteDto.getPaciente() == null)
	        	return "admin/usuarios";

	        //return "redirect:home";
	        List<Paciente> pacientes = servicio.llenarPacientes().getPacientes();
	    	
	    	modelo.addAttribute("paciente", paciente );
	    	modelo.addAttribute("pacientes", pacientes );
	        return "pacientes/pacientesActualizar";
	    }

}
