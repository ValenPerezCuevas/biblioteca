package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<libros, Long> {




}
