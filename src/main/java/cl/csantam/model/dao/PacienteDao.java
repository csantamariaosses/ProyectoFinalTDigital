package cl.csantam.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.csantam.model.entity.Paciente;

public interface PacienteDao extends JpaRepository<Paciente, Integer>{
    Optional<Paciente> findByRut(String rut);

}
