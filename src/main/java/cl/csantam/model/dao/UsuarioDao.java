package cl.csantam.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.csantam.model.entity.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByRut(String rut );
}
