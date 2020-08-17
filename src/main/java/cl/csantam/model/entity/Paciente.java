package cl.csantam.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private Sexo  sexo;
	private Prevision prevision;
	
	@OneToMany( mappedBy="paciente",
			    cascade = CascadeType.ALL,
			    orphanRemoval = true)
    private List<Tratamiento> tratamiento = new ArrayList<>();

}
