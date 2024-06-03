package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros_listas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Libros_listasRepository extends JpaRepository<libros_listas, Long> {

    @Modifying
    @Query("DELETE FROM libros_listas ll WHERE ll.lista.id_lista = ?1")
    void deleteByListaId(Integer idLista);

    @Query("SELECT ll FROM libros_listas ll WHERE ll.lista.id_lista = :idLista")
    List<libros_listas> findByListaId(@Param("idLista") Long idLista);

    @Query("SELECT ll FROM libros_listas ll WHERE ll.lista.id_lista = :idLista AND ll.libro.id_libros = :idLibro")
    Optional<libros_listas> findByListaIdAndLibroId(@Param("idLista") Long idLista, @Param("idLibro") Long idLibro);
}
