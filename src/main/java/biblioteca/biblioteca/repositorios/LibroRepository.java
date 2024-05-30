package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<libros, Long> {
    Page<libros> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    Page<libros> findByAutorContainingIgnoreCase(String autor, Pageable pageable);

    // Método para buscar libros por filtros
    @Query("SELECT l FROM libros l " +
            "WHERE (:titulo IS NULL OR LOWER(l.titulo) LIKE %:titulo%) " +
            "AND (:autor IS NULL OR LOWER(l.autor) LIKE %:autor%) " +
            "AND (:editorial IS NULL OR LOWER(l.editorial) LIKE %:editorial%) " +
            "AND (:genero IS NULL OR l.genero IN :genero)" +
            "AND (:anoDesde IS NULL OR l.anoPublicacion >= :anoDesde) " +
            "AND (:anoHasta IS NULL OR l.anoPublicacion <= :anoHasta)")
    Page<libros> findByFiltros(@Param("titulo") String titulo,
                               @Param("autor") String autor,
                               @Param("editorial") String editorial,
                               @Param("genero") List<String> genero,
                               @Param("anoDesde") Integer anoDesde,
                               @Param("anoHasta") Integer anoHasta,
                               Pageable pageable);

//    List<libros> findByListaId_lista(Long id_lista);
}
