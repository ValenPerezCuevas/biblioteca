package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros_listas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Libros_listasRepository extends JpaRepository<libros_listas, Long> {
//    List<libros_listas> findByIdLista(Long idLista);

}
