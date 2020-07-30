package cl.csantam.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.csantam.model.entity.Procedimiento;

public interface ProcedimientoDao extends JpaRepository<Procedimiento, Integer>{
    Optional<Procedimiento> findById(Integer id);
    Optional<Procedimiento> findByCodProcedimiento(String codProcedimiento );

}
