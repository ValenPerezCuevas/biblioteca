package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros_listas;
import biblioteca.biblioteca.entidades.listas;
import biblioteca.biblioteca.entidades.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ListasRepository extends JpaRepository<listas,Long> {
    ArrayList<listas> findByUsuario(usuarios usuario);


}
