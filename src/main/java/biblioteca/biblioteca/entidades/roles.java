package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol;
    private String nombre_rol;
}
