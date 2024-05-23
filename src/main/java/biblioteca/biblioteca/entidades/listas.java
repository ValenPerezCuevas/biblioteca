package biblioteca.biblioteca.entidades;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class listas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lista;
    private Integer id_usuario;
    private String nombre_lista;
    private Timestamp creado_desde;
    private Timestamp actualizado_desde;
    private Integer creado_por;
    private Integer actualizado_por;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    // Nombre de la columna en la tabla usuarios que hace referencia al idRol en la tabla roles
    private usuarios usuario;



}
