package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class libros_listas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibrosLista;


    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private libros libro;

    @ManyToOne
    @JoinColumn(name = "id_lista", nullable = false)
    private listas lista;


}

