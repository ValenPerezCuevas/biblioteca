package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.Libros;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LibroRepository extends JpaRepository<Libros, Long> {
}
