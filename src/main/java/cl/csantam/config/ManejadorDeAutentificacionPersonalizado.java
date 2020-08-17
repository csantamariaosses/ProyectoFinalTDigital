package cl.csantam.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import cl.csantam.model.entity.Usuario;
import cl.csantam.service.UsuarioService;

@Configuration
public class ManejadorDeAutentificacionPersonalizado implements AuthenticationSuccessHandler {

	 private static Logger logger = LoggerFactory.getLogger(ManejadorDeAutentificacionPersonalizado.class);
	 
	
	@Autowired
	UsuarioService servicio;
	
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response, 
        Authentication authentication
    ) throws IOException, ServletException {
        // así cargamos los roles.
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
       
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String correo  = auth.getName();
        
        Usuario usuario = servicio.buscarUsuarioDtoPorCorreo(correo).getUsuario();
        logger.info( "AUTH rut:" + usuario.getRut());
        
        HttpSession session= (HttpSession) request.getSession();
        session.setAttribute("id", usuario.getId());
        session.setAttribute("correo", correo );
        session.setAttribute("nombre", usuario.getNombre());
        session.setAttribute("rut", usuario.getRut());
        session.setAttribute("rol", usuario.getRol());
        
        String encodedPassword = usuario.getContrasenia();
        boolean comparaPass= EncoderUtils.passwordEncoder().matches("1234", encodedPassword);
        
        logger.info("FFFFFFFFFFFFFFFFFFFFFFFFF");
        
//        if(roles.contains("ROLE_ADMIN")) {
//            response.sendRedirect("/admin");
//        }else {
//        	response.sendRedirect("/usuario");  
//        }
        
        if(roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        }else {
        	if( comparaPass ) {
            	logger.info( "Pass Iguales ");
            	String id = session.getAttribute("id").toString();
            	logger.info("id:" + id);
            	response.sendRedirect( "usuario/cambioContraseniaInicioLogin?id="+id);
            } else {
            	logger.info( "Pass Distinta");
            	response.sendRedirect("/usuario");   
            }
       		        
        }
    }

}
