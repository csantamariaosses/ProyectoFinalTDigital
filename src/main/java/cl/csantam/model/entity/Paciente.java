package cl.csantam.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Paciente {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String rut;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String movil;
	private String correo;
	private String fechaNacimiento;
	private String sexo;
	private String prevision;

}
