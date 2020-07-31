package cl.csantam.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.csantam.model.dao.PacienteDao;
import cl.csantam.model.dto.PacienteDto;
import cl.csantam.model.dto.UsuarioDto;
import cl.csantam.model.entity.Paciente;

@Service
public class PacienteService {
	 private static Logger logger = LoggerFactory.getLogger(PacienteService.class);
	    
	    @Autowired
	    private PacienteDao dao;
	    
	    
	    public PacienteDto llenarPacientes() {
	    	logger.info("Lista Pacientes");
	    	PacienteDto pacienteDto = new PacienteDto(new Paciente(), dao.findAll());

	        return pacienteDto;
	    }
	    
	    
	    public PacienteDto registrarPaciente(Paciente paciente) {
	        PacienteDto pacienteDto = new PacienteDto();
	        
	        Paciente pacienteEnBase = dao.findByRut(   paciente.getRut() ).orElse(null);
	        
	        if( pacienteEnBase != null ) {
	            pacienteDto.setPaciente( pacienteEnBase );
	            logger.warn("El paciente que desea ingresar ya existe");
	        }else {
	        	
	            pacienteDto.setPaciente( dao.save( paciente ));
	        }
	        
	        return pacienteDto;
	    }
	    
	    
	    public PacienteDto actualizarPaciente( Paciente paciente ) {
	    	PacienteDto pacienteDto = new PacienteDto();
	         
	    	 //Paciente pacienteEnBase = dao.findById( usuario.getId()).orElse( null );
	    	 Paciente pacienteEnBase = dao.findByRut(  paciente.getRut()).orElse( null );
	         
	         if( pacienteEnBase  != null ) {
	        	 pacienteDto.setPaciente( dao.save( paciente ));
	             logger.warn("Paciente actualizado");
	         }else {
	        	 logger.warn("ERROR::: Paciente no encontrado ");
	         }
	         pacienteDto.setPaciente( pacienteEnBase );
	         return pacienteDto;
	    }
	    
	    
	    public PacienteDto  buscarPacientePorId(Integer  id ) {
	    	PacienteDto pacienteDto = new PacienteDto(   dao.findById(id).get() , dao.findAll() );
	    	return pacienteDto; 
	    }
	    
	    public PacienteDto buscarPorRut( String rut ) {
	    	PacienteDto pacienteDto = new PacienteDto(   dao.findByRut( rut ).get() , dao.findAll() );
	    	return pacienteDto; 
	    }
	    
	    
}
