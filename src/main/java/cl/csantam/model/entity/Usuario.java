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
@Data
@ToString
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String rut;
    private String nombre;
    private String correo;
    private String contrasenia;
    private Rol rol;
    
    @OneToMany( mappedBy = "usuario", 
    		    cascade  = CascadeType.ALL,
    		    orphanRemoval = true)
    private List<Tratamiento> tratamiento = new ArrayList<>();

    
}
