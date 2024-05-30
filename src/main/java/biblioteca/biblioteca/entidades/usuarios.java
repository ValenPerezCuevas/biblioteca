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

    @ManyToOne
    @JoinColumn(name = "id_rol")
    // Nombre de la columna en la tabla usuarios que hace referencia al idRol en la tabla roles
    private roles rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<listas> listas;

}
