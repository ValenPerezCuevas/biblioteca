package biblioteca.biblioteca.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class usuarios {
    @Id
    private Integer id_usuario;
    private String nombre;
    private String contrasena;
    private Integer id_rol;

}
