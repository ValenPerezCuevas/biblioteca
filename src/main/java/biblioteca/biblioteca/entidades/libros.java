package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<libros_listas> librosListas;

//    @ManyToOne
//    @JoinColumn(name = "id_lista")
//    private listas lista;
}
