package cl.csantam.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Data
@ToString
@Entity
public class Tratamiento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String  codTratamiento;
	/*
	private String  rutUsuario;
	private String  rutPaciente;
	private String  codProcedimiento;
	private String  nombre;
	*/
	private String  descripcion;
	private String  fechaIngreso;
	
	@ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="rutUsuario")
    private Usuario usuario;
	
	@ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="rutPaciente")
    private Paciente paciente;
	
	@ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="codProcedimiento")
    private Procedimiento procedimiento;
	

}
