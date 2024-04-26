package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LibroRepository extends JpaRepository<Libro, Long> {
}
