package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<libros, Long> {
    Page<libros> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    Page<libros> findByAutorContainingIgnoreCase(String autor, Pageable pageable);





//    List<libros> findByListaId_lista(Long id_lista);
}
