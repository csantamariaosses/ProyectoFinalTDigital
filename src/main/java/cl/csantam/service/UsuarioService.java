package cl.csantam.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.csantam.config.EncoderUtils;
import cl.csantam.model.dao.UsuarioDao;
import cl.csantam.model.dto.UsuarioDto;
import cl.csantam.model.entity.Usuario;

@Service
public class UsuarioService {
    
    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    
    @Autowired
    private UsuarioDao dao;

    public UsuarioDto registrarUsuario(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        
        Usuario usuarioEnBase = dao.findByCorreo(usuario.getCorreo()).orElse(null);
        
        if(usuarioEnBase != null) {
            usuarioDto.setUsuario(usuarioEnBase);
            logger.warn("El usuario que desea ingresar ya existe");
        }else {
        	usuario.setContrasenia( EncoderUtils.passwordEncoder().encode(usuario.getContrasenia()));
            usuarioDto.setUsuario(dao.save(usuario));
        }
        
        return usuarioDto;
    }
    
    public UsuarioDto llenarUsuarios() {
        UsuarioDto usuarioDto = new UsuarioDto(new Usuario(), dao.findAll());

        return usuarioDto;
    }

  
    public String  buscarUsuarioPorCorreo(String  correo ) {
    	Optional<Usuario> usuarioDto = Optional.empty();
    	usuarioDto = dao.findByCorreo( correo );
    	String nombre = usuarioDto.get().getNombre();
    	return nombre; 
    }
    
    public UsuarioDto  buscarUsuarioPorId(Integer  id ) {
    	UsuarioDto usuarioDto = new UsuarioDto(   dao.findById(id).get() , dao.findAll() );
    	return usuarioDto; 
    }
    
    
    public UsuarioDto actualizarUsuario( Usuario usuario ) {
    	 UsuarioDto usuarioDto = new UsuarioDto();
         
    	 Usuario usuarioEnBase = dao.findById( usuario.getId()).orElse( null );
         //Usuario usuarioEnBase = dao.findByCorreo(usuario.getCorreo()).orElse(null);
         
         if(usuarioEnBase != null) {
        	 String pass_old = usuarioEnBase.getContrasenia();
        	 String pass_new = usuario.getContrasenia();
        	 if( ! pass_old.equals( pass_new ) ) {
        		 usuario.setContrasenia( EncoderUtils.passwordEncoder().encode(usuario.getContrasenia()));
        	 }
        	 usuarioDto.setUsuario(dao.save(usuario));
             logger.warn("El usuario actualizado");
         }else {
        	 logger.warn("ERROR::: Usuario noo encontrado ");
         }
         usuarioDto.setUsuario(usuarioEnBase);
         return usuarioDto;
    }
	
    
}




















