package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class listas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lista;

    private String nombre_lista;
    private Timestamp creado_desde;
    private Timestamp actualizado_desde;
    private Integer creado_por;
    private Integer actualizado_por;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private usuarios usuario;

    @OneToMany(mappedBy = "lista")
    private List<libros> libros;
}
