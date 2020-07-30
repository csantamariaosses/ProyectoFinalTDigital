package cl.csantam.model.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.csantam.model.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Data
public class PacienteDto {
    private Paciente paciente;
    private List<Paciente> pacientes;
}


