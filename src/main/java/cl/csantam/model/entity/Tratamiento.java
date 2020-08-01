package cl.csantam.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Tratamiento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String descripcion;
	private String fechaIngreso;
	
	@ManyToOne
    @JoinColumn(name="rutUsuario", nullable=false, updatable = false)
    private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name="rutPaciente", nullable=false, updatable = false)
    private Paciente paciente;
	
	@ManyToOne
    @JoinColumn(name="codProcedimiento", nullable=false, updatable = false)
    private Procedimiento procedimiento;
	

}
