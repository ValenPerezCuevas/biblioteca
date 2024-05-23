package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LibrosListas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_libros_lista;

    @ManyToOne
    @JoinColumn(name = "id_libros")
    private libros libro;

    @ManyToOne
    @JoinColumn(name = "id_lista")
    private listas lista;
}
