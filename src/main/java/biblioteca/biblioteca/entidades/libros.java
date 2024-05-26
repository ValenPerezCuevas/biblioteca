package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class libros {
    @Id
    private int id_libros;
    private String titulo;
    private String genero;
    private String autor;
    private Integer anoPublicacion;
    private String editorial;

    @ManyToOne
    @JoinColumn(name = "id_lista")
    private listas lista;
}
