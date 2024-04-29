package biblioteca.biblioteca.entidades;

import lombok.Data;

@Data
public class Libro {
    private int idLibros;
    private String titulo;
    private String genero;
    private String autor;
    private Integer anoPublicacion;
    private String editorial;
}
