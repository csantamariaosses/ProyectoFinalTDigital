package cl.csantam.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter private Integer id;
    @Setter @Getter private String rut;
    @Setter @Getter private String nombre;
    @Setter @Getter private String correo;
    @Setter @Getter private String contrasenia;
    @Setter @Getter private Rol rol;
    
    @OneToMany( mappedBy="usuario")
    private List<Tratamiento> tratamiento;

    
}
