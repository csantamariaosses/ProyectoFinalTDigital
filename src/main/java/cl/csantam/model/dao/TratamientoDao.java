package cl.csantam.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.csantam.model.entity.Tratamiento;

public interface TratamientoDao extends JpaRepository<Tratamiento, Integer>{
    Optional<Tratamiento> findById(Integer id);
//    List<Tratamiento> findByRutPaciente(String rutPaciente);

}
