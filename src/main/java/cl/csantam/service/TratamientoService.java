package cl.csantam.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.csantam.model.dao.PacienteDao;
import cl.csantam.model.dao.ProcedimientoDao;
import cl.csantam.model.dao.TratamientoDao;
import cl.csantam.model.dao.UsuarioDao;
import cl.csantam.model.dto.TratamientoDto;
import cl.csantam.model.entity.Paciente;
import cl.csantam.model.entity.Procedimiento;
import cl.csantam.model.entity.Tratamiento;
import cl.csantam.model.entity.Usuario;

@Service
public class TratamientoService {
	private static Logger logger = LoggerFactory.getLogger( TratamientoService.class);
	
    @Autowired
	private TratamientoDao tratamientoDao;
    
    @Autowired
	private UsuarioDao usuarioDao;
    
    
    @Autowired
   	private PacienteDao pacienteDao;
    
    @Autowired
   	private ProcedimientoDao procedimientoDao;
    
    public TratamientoDto agregaTratamiento(String rutPaciente, String rutUsuario, String codProcedimiento, String descripcion) {
 
    	TratamientoDto tratamientoDto = new TratamientoDto();
    	
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String   fechaHoy = dateFormat.format(new Date());
        

    	Usuario  usuario  = usuarioDao.findByRut(rutUsuario).get();
    	Paciente paciente = pacienteDao.findByRut( rutPaciente).get();
    	Procedimiento procedimiento = procedimientoDao.findByCodProcedimiento(codProcedimiento).get();
    	logger.info( paciente.getRut());
    	logger.info( usuario.getRut());
    	logger.info( procedimiento.getCodProcedimiento());
    	
    	//TratamientoDto tratamientoDto = new TratamientoDto();
    	Tratamiento tratamiento = new Tratamiento();
    	tratamiento.setUsuario( usuario );
    	tratamiento.setPaciente( paciente );
    	tratamiento.setProcedimiento( procedimiento );
    	tratamiento.setCodTratamiento("XXX");
    	tratamiento.setFechaIngreso( fechaHoy );
    	tratamiento.setDescripcion( descripcion );
    	
    	tratamientoDto.setTratamiento( tratamientoDao.save( tratamiento ));

    	return tratamientoDto;
    }
    
    
    public TratamientoDto llenarTratamientos() { 
    	logger.info("Lista Tratamientos");
    	TratamientoDto tratamientoDto = new TratamientoDto( new Tratamiento(), tratamientoDao.findAll());

        return tratamientoDto;
    }
    
    
//    public TratamientoDto buscarTratamientosPorRutPaciente(String rutPaciente) {
//    	TratamientoDto tratamientoDto = new TratamientoDto( new Tratamiento(), tratamientoDao.findByRutPaciente(rutPaciente));
//    	return tratamientoDto;
//    }
    
    
    public TratamientoDto  buscarTratamientoPorId(Integer  id ) {
    	TratamientoDto tratamientoDto = new TratamientoDto(   tratamientoDao.findById(id).get() , tratamientoDao.findAll() );
    	return tratamientoDto; 
    }


    public boolean eliminarTratamiento( Tratamiento tratamiento ) {
    	boolean status = false;
        
    	logger.info("POR ELIMINAR:" + tratamiento.getId());
   	    Tratamiento tratamientoEnBase = tratamientoDao.findById( tratamiento.getId()).orElse( null );

        
        if( tratamientoEnBase != null ) {
        	try {
        		tratamientoDao.delete( tratamiento );
        		status = true;
            	logger.info("ELIMINADO:" );
        	} catch( Exception ex ) {
        		logger.info("Error al intentar eliminar :"+ tratamiento.getId() );
        		status = false;
        	}
        	
        }
        	
        return status;
    }
    
    
    public TratamientoDto actualizarTratamiento( Tratamiento tratamiento ) {
    	TratamientoDto tratamientoDto = new TratamientoDto();
        
   	     Tratamiento tratamientoEnBase = tratamientoDao.findById( tratamiento.getId()).orElse( null );
      
        
        if( tratamientoEnBase != null) {
       	   	 tratamientoDto.setTratamiento( tratamientoDao.save( tratamiento ));
            logger.warn("Tratamiento actualizado ...");
        }else {
       	    logger.warn("ERROR::: Tratamiento no encontrado ");
        }
        tratamientoDto.setTratamiento( tratamientoEnBase );
        return tratamientoDto;
   }
}
