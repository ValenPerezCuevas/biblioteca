package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import java.util.List;

@Entity
@Data
public class usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    private String nombre;
    private String contrasena;
    private Integer id_rol;


}
