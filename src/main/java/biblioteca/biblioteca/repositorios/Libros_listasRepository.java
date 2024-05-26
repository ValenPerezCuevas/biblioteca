package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros_listas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Libros_listasRepository extends JpaRepository<libros_listas, Long> {

    @Query("SELECT ll FROM libros_listas ll WHERE ll.lista.id_lista = :idLista")
    List<libros_listas> findByListaId(@Param("idLista") Long idLista);

}
