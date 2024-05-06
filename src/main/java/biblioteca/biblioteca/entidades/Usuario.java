package biblioteca.biblioteca.entidades;

import lombok.Data;

@Data
public class Usuario {
    private Integer id_usuario;
    private String nombre;
    private String contrasena;
    private Integer id_rol;

}
