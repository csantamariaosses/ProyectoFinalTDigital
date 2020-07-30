package cl.csantam.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.csantam.model.dao.ProcedimientoDao;
import cl.csantam.model.dto.PacienteDto;
import cl.csantam.model.dto.ProcedimientoDto;
import cl.csantam.model.entity.Procedimiento;


@Service
public class ProcedimientoService {
   private static Logger logger = LoggerFactory.getLogger(ProcedimientoService.class);
    
    
    @Autowired
    private ProcedimientoDao dao;
    
    public ProcedimientoDto llenarProcedimientos() {
        ProcedimientoDto procedimientoDto = new ProcedimientoDto(new Procedimiento(), dao.findAll());
        return procedimientoDto;
    }

    
    public ProcedimientoDto registrarProcedimiento(Procedimiento procedimiento) {
    	ProcedimientoDto procedimientoDto = new ProcedimientoDto();
        
        Procedimiento  procedimientoEnBase = dao.findByCodProcedimiento( procedimiento.getCodProcedimiento()).orElse(null);
        
        if( procedimientoEnBase != null) {
            procedimientoDto.setProcedimiento(procedimientoEnBase);
            logger.warn("El procedimiento que desea ingresar ya existe");
        }else {
        	//usuario.setContrasenia( EncoderUtils.passwordEncoder().encode(usuario.getContrasenia()));
            //usuarioDto.setUsuario(dao.save(usuario));
        	procedimiento.setEstado( 1 );
            procedimientoDto.setProcedimiento(  dao.save( procedimiento ));
        }
        
        return procedimientoDto; 
    }
    
    
    public ProcedimientoDto actualizarProcedimiento( Procedimiento procedimiento ) {
    	ProcedimientoDto procedimientoDto = new ProcedimientoDto();
        
   	     Procedimiento procedimientoEnBase = dao.findById( procedimiento.getId()).orElse( null );
      
        
        if( procedimientoEnBase != null) {
       	   	 procedimientoDto.setProcedimiento( dao.save( procedimiento ));
            logger.warn("Procedimiento actualizado");
        }else {
       	    logger.warn("ERROR::: Procedimiento no encontrado ");
        }
        procedimientoDto.setProcedimiento( procedimientoEnBase );
        return procedimientoDto;
   }
    
    
    public ProcedimientoDto  buscarProcedimientoPorId(Integer  id ) {
    	ProcedimientoDto procedimientoDto = new ProcedimientoDto(   dao.findById(id).get() , dao.findAll() );
    	return procedimientoDto; 
    }
}
