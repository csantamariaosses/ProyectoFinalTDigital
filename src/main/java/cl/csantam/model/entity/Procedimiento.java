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
public class Procedimiento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String codProcedimiento;
	private String nombre;
	private String descripcion;
	private double precio;
	private int estado;
	
	@OneToMany( mappedBy = "procedimiento",
			    cascade = CascadeType.ALL,
			    orphanRemoval = true)
    private List<Tratamiento> tratamiento =  new ArrayList<>();

}
