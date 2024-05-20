package biblioteca.biblioteca.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class libros {
    @Id
    private int id_libros;
    private String titulo;
    private String genero;
    private String autor;
    @Column(name = "ano_Publicacion")
    private Integer anoPublicacion;
    private String editorial;
}
