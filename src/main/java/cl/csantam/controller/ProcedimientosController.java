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

import cl.csantam.model.dto.ProcedimientoDto;
import cl.csantam.model.entity.Procedimiento;
import cl.csantam.service.ProcedimientoService;

@Controller
@RequestMapping("procedimientos")
public class ProcedimientosController {
	private static final Logger logger = LoggerFactory.getLogger(ProcedimientosController.class);
	
	boolean info  = false;
	boolean error = false;
	String    msg = "";
	
	@Autowired
    private ProcedimientoService servicio;
	
	
	@GetMapping
    public String index(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);
        List<Procedimiento> procedimientos = servicio.llenarProcedimientos().getProcedimientos();
        System.out.println( procedimientos.get(0));
        modelo.addAttribute("procedimientos", procedimientos);
//        String nombre = servicio.buscarUsuarioPorCorreo(name);
//        modelo.addAttribute("nombre", nombre);
        return "procedimientos/index";
    }
	
	
	 @PostMapping
	    public String registraProcedimiento( @ModelAttribute Procedimiento procedimiento, ModelMap modelo) {
	    	
	        ProcedimientoDto procedimientoDto = servicio.registrarProcedimiento( procedimiento );
	        if(procedimientoDto.getProcedimiento() == null)
	            return "admin";

	        //return "redirect:home";
	        List<Procedimiento> procedimientos = servicio.llenarProcedimientos().getProcedimientos();
	    	
	    	//modelo.addAttribute("usuario", usuario);
	    	modelo.addAttribute("procedimientos", procedimientos);
	        return "procedimientos/index";
	  }
	 
	 
	    @GetMapping("/actualizar")
	    public String actualizar(
	        ModelMap modelo,
	        @RequestParam(name = "id") Integer  id
	    ) {
	    	Procedimiento procedimiento = servicio.buscarProcedimientoPorId( id ).getProcedimiento();
	    	
	    	logger.info("Procedimiento:" + procedimiento.getDescripcion());
	    	List<Procedimiento> procedimientos = servicio.llenarProcedimientos().getProcedimientos();
	    	
	    	modelo.addAttribute("procedimiento", procedimiento);
	    	modelo.addAttribute("procedimientos", procedimientos);

	        return "procedimientos/procedimientosActualizar";
	    }
	    
	    
	    @PostMapping("/actualizar")
	    public String procedimientoActualizar( @ModelAttribute Procedimiento procedimiento, ModelMap modelo) {
	     	logger.info("ACTUALIZAR::" + procedimiento.getNombre() + " id "+ procedimiento.getId());
	     	
	        ProcedimientoDto procedimientoDto = servicio.actualizarProcedimiento( procedimiento );
	        if( procedimientoDto.getProcedimiento() == null) {
	        	error = true;
	        	msg = "Ocurrio un problema al intentar actualizar...";
	        } else {
	        	info = true;
	        	msg = "Procedimiento actualizado en forma correcta ...!";
	        }

	        //return "redirect:home";
	        List<Procedimiento> procedimientos = servicio.llenarProcedimientos().getProcedimientos();
	    	
	        modelo.put("info", info);
	        modelo.put("error", error);
	        modelo.put("msg", msg);
	        
	    	modelo.addAttribute("procedimiento", procedimiento);
	    	modelo.addAttribute("procedimientos", procedimientos );
	        return "procedimientos/procedimientosActualizar";
	    }

	    
	    
	    @GetMapping("/eliminar")
	    public String procedimientoEliminar(
	        ModelMap modelo,
	        @RequestParam(name = "id") Integer id
	    ) {

	    	logger.info("Controller Eliminar:" + id);
	    	boolean error = false;
	    	boolean info = false;
	    	String  msg = "";
	    	Procedimiento procedimiento = servicio.buscarProcedimientoPorId(id).getProcedimiento();
	    	
	    	if( servicio.eliminarProcedimiento( procedimiento ) )  {
	    		info = true;
	    		msg = "Procedimiento eliminado en forma exitosa..!!";
	    	} else {
	    		error = false;
	    		msg = "Ocurrio un problema al intentar eliminar";
	    	}
	    	
	    	List<Procedimiento> procedimientos = servicio.llenarProcedimientos().getProcedimientos();
	    	
	    	modelo.addAttribute("info", info);
	    	modelo.addAttribute("error", error);
	    	modelo.addAttribute("msg", msg);
	    	modelo.addAttribute("procedimiento", procedimiento );
	    	modelo.addAttribute("procedimientos", procedimientos);
	        //modelo.put("usuarioVo", servicio..obtenerPorId(id));
	        //modelo.put("usuarioDto", usuarioDto);
	        return "procedimientos/procedimientosActualizar";
	    }
}
