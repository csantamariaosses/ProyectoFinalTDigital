package cl.csantam.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import cl.csantam.model.dto.PacienteDto;
import cl.csantam.model.dto.TratamientoDto;
import cl.csantam.model.entity.Paciente;
import cl.csantam.model.entity.Procedimiento;
import cl.csantam.model.entity.Tratamiento;
import cl.csantam.model.entity.Usuario;
import cl.csantam.service.PacienteService;
import cl.csantam.service.ProcedimientoService;
import cl.csantam.service.TratamientoService;
import cl.csantam.service.UsuarioService;

@Controller
@RequestMapping("tratamientos")
public class TratamientosController {
	private static final Logger logger = LoggerFactory.getLogger(TratamientosController.class);
	
	private boolean info = false;
	private boolean error = false;
	private String msg = "";
	
	@Autowired
    private PacienteService servicioPaciente;
	
	@Autowired
    private TratamientoService servicioTratamiento;
	
	@Autowired
    private ProcedimientoService servicioProcedimiento;
	
	@Autowired
    private UsuarioService servicioUsuario;
	
//	@Autowired
//	private TratamientoDao tratamientoDao;

	
	@GetMapping
    public String index(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);
        
        //List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();       
        List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
        List<Paciente> pacientes = servicioPaciente.llenarPacientes().getPacientes();

        modelo.addAttribute("proced", "");
       // modelo.addAttribute("procedimientos", procedimientos);

        modelo.addAttribute("usuarios", usuarios);
        modelo.put("pacientes", pacientes);
        return "tratamientos/tratamientosPaciente";
    }

	@PostMapping("/buscar") 
	public String pacienteBuscar(
			ModelMap modelo,
	        @RequestParam(name = "txtBuscar", required = false) String txtBuscar,
	        @RequestParam(name = "optradio", required = false) String optradio
			
			) {
	        
            logger.info("txtBuscar:" + txtBuscar);
            logger.info("optradio:" + optradio);
            
            List<Paciente> pacientes = servicioPaciente.llenarPacientes().getPacientes();
            if( optradio.equals("rut")) {
            	logger.info("Buscar por rut");
            	PacienteDto pacienteDto = servicioPaciente.buscarPacientePorRut( txtBuscar );
            	pacientes = Arrays.asList( servicioPaciente.buscarPacientePorRut( txtBuscar ).getPaciente());
            	logger.info("Nombre:" + pacienteDto.getPaciente().getNombre());
            }
            
            if( optradio.equals("nombre")) {
            	logger.info("Buscar por nombre:" + txtBuscar);
            	pacientes = servicioPaciente.buscarPacientesContieneNombre( txtBuscar ).getPacientes();
        
            }
            
            
            
            List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();       
            List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
            modelo.put("pacientes", pacientes);
            modelo.addAttribute("procedimientos", procedimientos);
            modelo.addAttribute("usuarios", usuarios);
		    return "tratamientos/tratamientosActualizar";
	}
	
	
	
	
	@GetMapping("pacienteSelecciona")
	public String pacienteSelecciona(
			ModelMap modelo,
	        @RequestParam(name = "id", required = true) Integer id
			) {
		    logger.info("id:" +id );
		    
		    List<Paciente> pacientes = Arrays.asList( servicioPaciente.buscarPacientePorId( id ).getPaciente());
		    Paciente  paciente = servicioPaciente.buscarPacientePorId(id).getPaciente();
		   // List<Tratamiento> tratamientos = 
		    String rutPaciente = paciente.getRut();
		    logger.info("TRTAMIENTOS********* rut" + rutPaciente);
	    

		    List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
		    List<Tratamiento> tratamientosPcte = new ArrayList<Tratamiento>();
		    tratamientosPcte = tratamientosTodos.stream().filter( x->x.getPaciente().equals(paciente)).collect(Collectors.toList());

            List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();       
            List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
            modelo.addAttribute("procedimientos", procedimientos);
            modelo.addAttribute("usuarios", usuarios);
            
            modelo.put("idPaciente", id);
            modelo.put("rutPaciente", rutPaciente );
		    modelo.put("pacientes", pacientes );
		    modelo.put("paciente", paciente );
		    modelo.put("tratamientos",  tratamientosPcte);
		    
		    return "tratamientos/tratamientosActualizar"; 
	}
	
	
	
	@GetMapping("pacienteSeleccionaPorId")
	public String pacienteSeleccionaPorId(
			ModelMap modelo,
	        @RequestParam(name = "id", required = true) Integer id
			) {
		    logger.info("id:" +id );
		    
		   
		    Paciente  paciente = servicioPaciente.buscarPacientePorId(id).getPaciente();
		   // List<Tratamiento> tratamientos = 
		    String rutPaciente = paciente.getRut();
		    logger.info("TRTAMIENTOS********* rut" + rutPaciente);
	    

		    List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
		    List<Tratamiento> tratamientosPcte = new ArrayList<Tratamiento>();
		    tratamientosPcte = tratamientosTodos.stream().filter( x->x.getPaciente().equals(paciente)).collect(Collectors.toList());

//            List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();       
//            List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
//            modelo.addAttribute("procedimientos", procedimientos);
//            modelo.addAttribute("usuarios", usuarios);
            
            modelo.put("idPaciente", id);
            modelo.put("rutPaciente", rutPaciente );
		    modelo.put("paciente", paciente );
		    modelo.put("tratamientos",  tratamientosPcte);
		    
		    return "tratamientos/tratamientosProcedimientos"; 
	}
	
	
	@PostMapping("agregarPideDatos")
	public String agregarPideDatos (
			ModelMap modelo,
			@RequestParam(name = "idPaciente", required = true) Integer idPaciente,
			@RequestParam(name = "rutPaciente", required = false) String rutPaciente	
			) {
		 List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();  
		 List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
		 modelo.put("idPaciente", idPaciente);
		 modelo.put("rutPaciente", rutPaciente);
		 modelo.put("procedimientos", procedimientos);
		 modelo.put("usuarios", usuarios);
		 return "tratamientos/tratamientosAgregarPideDatos";
	}
	
	
	@PostMapping("agregarProcedimiento")
	public String agregarProcedimiento(
			ModelMap modelo,
			@RequestParam(name = "idPaciente", required = false) Integer idPaciente,
	        @RequestParam(name = "rutPaciente", required = true) String rutPaciente,
	        @RequestParam(name = "codProcedimiento", required = true) String codProcedimiento,
	        @RequestParam(name = "rutUsuario", required = true) String rutUsuario,
	        @RequestParam(name = "descripcion", required = false) String descripcion
			) {
		  logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		  logger.info("idPaciente" + idPaciente);
		  logger.info("rutPaciente" + rutPaciente);
		  logger.info("codProcedimiento:" + codProcedimiento);
		  logger.info("rutUsuario:" + rutUsuario);
		  logger.info("descripcion:" + descripcion);
		  
		  
		  servicioTratamiento.agregaTratamiento(rutPaciente, rutUsuario, codProcedimiento, descripcion);
		  
		  // Listado pacientes
//		  List<Paciente> pacientes = Arrays.asList( servicioPaciente.buscarPacientePorRut(rutPaciente).getPaciente());
		  Paciente paciente = servicioPaciente.buscarPacientePorRut(rutPaciente).getPaciente();
		  logger.info("Nombre Pacientes:" + paciente.getNombre());
		  List<Paciente> pacientes = servicioPaciente.buscarPacientePorRut( rutPaciente ).getPacientes();
		  logger.info("Pacientes size:" + pacientes.size());
		  for(Paciente pacient:pacientes) {
			  logger.info(pacient.getNombre());
		  }
		  // Datos del paciente
//          Paciente  paciente = servicioPaciente.buscarPacientePorRut(rutPaciente).getPaciente();   
          
          // Disponibiliar procedimientos
//          List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();  
//          
//          //disponibiizar Usuarios
//          //List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
//          
          // Filtra tratamientos del paciente
          List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
		  List<Tratamiento> tratamientosPcte = new ArrayList<Tratamiento>();
		  tratamientosPcte = tratamientosTodos.stream().filter( x->x.getPaciente().equals(paciente)).collect(Collectors.toList());
		  logger.info("TRATAMIENTOS:******************************");
		  for(Tratamiento trat:tratamientosPcte) {
			  logger.info( trat.getPaciente().getNombre() );
			  logger.info("Poced Nombre:"+ trat.getProcedimiento().getNombre() );
			  logger.info("Poced: Precio"+ trat.getProcedimiento().getPrecio() );

		  }
//          
//		 
//          
//         // modelo.addAttribute("procedimientos", procedimientos);
//         // modelo.put("usuarios", usuarios);
//          modelo.put("idPaciente", idPaciente);
//          modelo.put("rutPaciente", paciente.getRut());
		  modelo.put("pacientes", pacientes );
		  modelo.put("paciente", paciente );
//		  modelo.put("tratamientos",  tratamientosPcte);
//		  return "tratamientos/index";
		return "tratamientos/auxiliar";
	}
	
	
//    @GetMapping("/eliminar")
//    public String tratamientoEliminar(
//        ModelMap modelo,
//        @RequestParam(name = "id") Integer id
//        
//    ) {
//
//    	logger.info("Controller Eliminar:" + id);
//    	boolean error = false;
//    	boolean info = false;
//    	String  msg = "";
//    	
//    	Tratamiento tratamiento = servicioTratamiento.buscarTratamientoPorId( id ).getTratamiento();
//    	
//    	if( servicioTratamiento.eliminarTratamiento( tratamiento ) )  {
//    		info = true;
//    		msg = "Tratamiento eliminado en forma exitosa..!!";
//    	} else {
//    		error = false;
//    		msg = "Ocurrio un problema al intentar eliminar";
//    	}
//    	
//    	
//		// Disponibiliar procedimientos
//		List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();
//
//		// Filtra tratamientos del paciente
//		List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
//		List<Tratamiento> tratamientosPcte = new ArrayList<Tratamiento>();
////		tratamientosPcte = tratamientosTodos.stream().filter(x -> x.getPaciente().equals(paciente))
////				.collect(Collectors.toList());
////
////		// disponibiizar Usuarios
////		List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
////
////		modelo.addAttribute("procedimientos", procedimientos);
////		modelo.addAttribute("usuarios", usuarios);
////
////		modelo.put("idPaciente", idPaciente);
////		modelo.put("rutPaciente", paciente.getRut());
////		modelo.put("pacientes", pacientes);
////		modelo.put("paciente", paciente);
//		modelo.put("tratamientos", tratamientosPcte);
//		return "tratamientos/tratamientosActualizar";
//    }
    
    
    @GetMapping("/actualizar")
    public String tratamientoActualizar(
    	     ModelMap modelo,
    	     @RequestParam(name = "id") Integer id
    		) {
    
    	
    	//disponibiizar Usuarios
        List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
        
    	// Disponibiliar procedimientos
    	List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();
        	
    	// Filtra tratamientos del paciente
        List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
        Tratamiento tratamiento = servicioTratamiento.buscarTratamientoPorId(id).getTratamiento();

//		Tratamiento tratamientoPcte =  (Tratamiento) tratamientosTodos.stream().filter( x->x.getId().equals(id)).distinct();
		
    	modelo.put("procedimientos", procedimientos);
    	modelo.put("tratamientos", tratamientosTodos);
    	modelo.put("tratamiento", tratamiento);
    	modelo.put("usuarios", usuarios);
    	return "tratamientos/tratamientoShowEditar";
    }
    
//    @PostMapping("/actualizar")
//    public String tratamientoActualizar(
//        ModelMap modelo,
//        @RequestParam(name = "id") Integer id,
//        @RequestParam(name = "codProcedimientoModif") String codProcedimiento,
//        @RequestParam(name = "codProfesionalModif")   String codProfesional,
//        @RequestParam(name = "descripcionModif")      String descripcion
//        
//        
//    ) {
//
//    	System.out.println("*****************************************************************");
//    	logger.info("Controller Actualiza:" + id);
//    	logger.info("Controller codProcedimiento:" + codProcedimiento);
//    	logger.info("Controller codProfesional:" + codProfesional);
//    	logger.info("Controller descripcion:" + descripcion);
//    	
//    	Tratamiento tratamiento = servicioTratamiento.buscarTratamientoPorId(id).getTratamiento();
//    	
//    	
//    	tratamiento.setProcedimiento(procedimiento); 	
//    	tratamiento.setId( id );
//    	tratamiento.setCodTratamiento(codTratamiento);
//    	tratamiento.setDescripcion(descripcion);
//    	tratamiento.setFechaIngreso(fechaIngreso);
//    	
//		return "tratamientos/tratamientosActualizar";
//    }
    
    
    
//    @PostMapping("/actualizar")
//    public String tratamientoActualizarN( 
//    		ModelMap modelo,
//
//			@RequestParam(name = "id", required = false) Integer id,
//			@RequestParam(name = "codTratamiento", required = false) String codTratamiento,
//	        @RequestParam(name = "rutPaciente", required = false) String rutPaciente,
//	        @RequestParam(name = "codProcedimiento", required = true) String codProcedimiento,
//	        @RequestParam(name = "rutUsuario", required = true) String rutUsuario,
//	        @RequestParam(name = "descripcion", required = false) String descripcion,
//	        @RequestParam(name = "fechaIngreso", required = false) String fechaIngreso
//	        
//    		) {
//    	
//    	
//     	logger.info("ACTUALIZAR::" + id );
//     	Paciente  paciente = servicioPaciente.buscarPacientePorRut(rutPaciente).getPaciente();
////     	Procedimiento procedimiento = servicioProcedimiento.buscarProcedimientoPorId(id);
//     	Tratamiento tratamiento = new Tratamiento();
//     	tratamiento.setId( id );
//     	tratamiento.setPaciente(paciente);
//     	tratamiento.setDescripcion(descripcion);
//     	tratamiento.setFechaIngreso(fechaIngreso);
//     	tratamiento.setCodTratamiento(codTratamiento);
////     	tratamiento.setProcedimiento(codProcedimiento);
//     	
//     	TratamientoDto tratamientoDto = servicioTratamiento.actualizarTratamiento(tratamiento);
//     	
//        if( tratamientoDto.getTratamiento() == null)
//        	return "admin/usuarios";
//
//      //disponibiizar Usuarios
////        List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
////        
////    	// Disponibiliar procedimientos
////    	List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();
////                
////    	// Filtra tratamientos del paciente
////        List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
////        Tratamiento tratamiento = servicioTratamiento.buscarTratamientoPorId(id).getTratamiento();
//        
//        String msg = "Registro Actualizado ...!!!";
//    	modelo.put("tratamiento", tratamiento);
////    	modelo.addAttribute("procedimientos", procedimientos);
////    	modelo.addAttribute("procedimientos", procedimientos );
////    	modelo.put("usuarios", usuarios);
//    	modelo.put("msg", msg);
//        return "tratamientos/tratamientosMsg";
//    }
    
    
    @GetMapping("tratamientosAgregarProcedimento")
	public String tratamientosAgregarProcedimento(
			ModelMap modelo,
			@RequestParam(name = "idPaciente", required = false) Integer idPaciente
	      
			) {
    	
    	    // Disponibiliar procedimientos
    	    List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();
    	    
    	    //disponibiizar Usuarios
            List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
            
    	    modelo.put("idPaciente", idPaciente);
    	    modelo.put("procedimientos", procedimientos);
    	    modelo.put("usuarios", usuarios);
    	    return "tratamientos/tratamientosAgregarPideDatos";
   
    }
    
    @PostMapping("tratamientosAgregarProcedimiento")
    public String tratamientosAgregarProcedimiento( 
    		ModelMap modelo,

			@RequestParam(name = "idPaciente", required = true) Integer idPaciente,
			@RequestParam(name = "codTratamiento", required = false) String codTratamiento,
	        @RequestParam(name = "codProcedimiento", required = true) String codProcedimiento,
	        @RequestParam(name = "rutUsuario", required = true) String rutUsuario,
	        @RequestParam(name = "descripcion", required = false) String descripcion
	        
    		) {
    	
    	    logger.info("AGREGA PROCEDIMIENTO");
    	    logger.info("IdPaciente:"+ idPaciente);
    	    logger.info("codProcedimiento:"+ codProcedimiento);
    	    logger.info("rutUsuario:"+ rutUsuario);
    	    logger.info("descripcion:"+ descripcion);
    	    
    	    Paciente  paciente = servicioPaciente.buscarPacientePorId( idPaciente ).getPaciente();
 		    String rutPaciente = paciente.getRut();
 		    
 		    
    	    TratamientoDto tratamientoDto = servicioTratamiento.agregaTratamiento(rutPaciente, rutUsuario, codProcedimiento, descripcion);
    	      
    	    if( tratamientoDto == null ) {
    	    	error = true;
        		msg = "Ocurrio un problema al intentar eliminar procedimiento...";
    	    } else {
    	    	info = true;
        		msg = "Tratamiento eliminado en forma exitosa..!!";
    	    }
    	    // Filtra tratamientos del paciente
    	    List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
  		    List<Tratamiento> tratamientosPcte = new ArrayList<Tratamiento>();
  		    tratamientosPcte = tratamientosTodos.stream().filter( x->x.getPaciente().equals(paciente)).collect(Collectors.toList());
    	    
  		    for( Tratamiento trat: tratamientosPcte ) {
  		    	logger.info( Integer.toString( trat.getId()));
  		    	logger.info( trat.getPaciente().getRut());
  		    	logger.info( trat.getProcedimiento().getCodProcedimiento());
  		    	logger.info( trat.getProcedimiento().getNombre());
  		    	logger.info( trat.getDescripcion());
  		    	logger.info( trat.getFechaIngreso());
  		    }
  		    
  			modelo.put("info", info);
  			modelo.put("error", error);
  			modelo.put("msg", msg );
            modelo.put("paciente", paciente);
            modelo.put("tratamientos", tratamientosPcte);   
            
    	    return "tratamientos/tratamientosProcedimientos";
 
    }
    
    
    
    @GetMapping("/tratamientosEliminar")
    public String tratamientosEliminar(
        ModelMap modelo,
        @RequestParam(name = "idTratamiento") Integer idTratamiento,
        @RequestParam(name = "idPaciente") Integer idPaciente
        
    ) {

    	logger.info("Controller Eliminar idTratamiento:" + idTratamiento);
    	logger.info("Controller Eliminar idPaciente:" + idPaciente);
    	boolean error = false;
    	boolean info  = false;
    	String  msg   = "";
    	
    	Tratamiento tratamiento = servicioTratamiento.buscarTratamientoPorId( idTratamiento ).getTratamiento();
    	Paciente paciente = tratamiento.getPaciente();
    	
    	if( servicioTratamiento.eliminarTratamiento( tratamiento ) )  {
    		info = true;
    		msg = "Tratamiento eliminado en forma exitosa..!!";
    	} else {
    		error = false;
    		msg = "Ocurrio un problema al intentar eliminar";
    	}
    	
    	List<Tratamiento> tratamientosTodos = servicioTratamiento.llenarTratamientos().getTratamientos();
		List<Tratamiento> tratamientosPcte = new ArrayList<Tratamiento>();
		tratamientosPcte = tratamientosTodos.stream().filter( x->x.getPaciente().equals(paciente)).collect(Collectors.toList());
    	
    	
		modelo.put("info", info);
		modelo.put("error", error);
		modelo.put("msg", msg );
		modelo.put("paciente", paciente);
		modelo.put("tratamientos", tratamientosPcte);
		
		return "tratamientos/tratamientosProcedimientos";
    }	
    
    
    @GetMapping("/tratamientosActualizar")
    public String tratamientosActualizar(
    	     ModelMap modelo,
    	     @RequestParam(name = "idTratamiento") Integer idTratamiento
    		) {
    
    	Tratamiento tratamiento = servicioTratamiento.buscarTratamientoPorId( idTratamiento ).getTratamiento();
    	Paciente paciente = tratamiento.getPaciente();
    	
    	
    	//disponibiizar Usuarios
        List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
        
    	// Disponibiliar procedimientos
    	List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();
        	

		
    	modelo.put("procedimientos", procedimientos);
    	modelo.put("usuarios", usuarios);
    	modelo.put("tratamiento", tratamiento);
    	modelo.put("paciente", paciente);
    	return "tratamientos/tratamientoShowEditar";
    }
    
    
    @PostMapping("tratamientosActualizar")
    public String tratamientosActualizar( 
    		ModelMap modelo,

			@RequestParam(name = "idTratamiento", required = true) Integer idTratamiento,
	        @RequestParam(name = "codProcedimiento", required = true) String codProcedimiento,
	        @RequestParam(name = "rutUsuario", required = true) String rutUsuario,
	        @RequestParam(name = "descripcion", required = false) String descripcion
	        
    		) {
    	
    	    logger.info("ACTUALIZAR PROCEDIMIENTO");
    	    logger.info("IdTratamiento:"+ idTratamiento);
    	    logger.info("codProcedimiento:"+ codProcedimiento);
    	    logger.info("rutUsuario:"+ rutUsuario);
    	    logger.info("descripcion:"+ descripcion);
    	    
    	    
    	    Tratamiento tratamiento = servicioTratamiento.buscarTratamientoPorId( idTratamiento ).getTratamiento();
    	    Paciente paciente = tratamiento.getPaciente();
    	    logger.info("Trat x Actualizar idTratamiento:" + tratamiento.getId());
    	    
    	    Procedimiento procedimiento = servicioProcedimiento.buscarProcedimientoPorCodProcedimiento(codProcedimiento).getProcedimiento();
    	    Usuario       usuario = servicioUsuario.buscarUsuarioDtoPorRut(rutUsuario).getUsuario();
    	    
    	    tratamiento.setDescripcion( descripcion );
    	    tratamiento.setProcedimiento( procedimiento );
    	    tratamiento.setUsuario( usuario );
    	     		    
 		    TratamientoDto tratamientoDto = servicioTratamiento.actualizarTratamiento( tratamiento );

    	      
    	    if( tratamientoDto == null ) {
    	    	error = true;
        		msg = "Ocurrio un problema al intentar actualizar ...";
    	    } else {
    	    	info = true;
        		msg = "Tratamiento actualizado en forma exitosa..!!";
    	    }

    	    
    	    
    	  //disponibiizar Usuarios
            List<Usuario> usuarios = servicioUsuario.llenarUsuarios().getUsuarios();
            
        	// Disponibiliar procedimientos
        	List<Procedimiento> procedimientos = servicioProcedimiento.llenarProcedimientos().getProcedimientos();
        	
        	modelo.put("info", info);
    		modelo.put("error", error);
    		modelo.put("msg", msg );
    	    modelo.put("procedimientos", procedimientos);
        	modelo.put("usuarios", usuarios);
        	modelo.put("tratamiento", tratamiento);
        	modelo.put("paciente", paciente);
    	    return "tratamientos/tratamientoShowEditar";
 
    }
    
}

