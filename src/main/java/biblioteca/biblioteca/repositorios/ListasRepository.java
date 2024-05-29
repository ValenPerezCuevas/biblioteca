package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros_listas;
import biblioteca.biblioteca.entidades.listas;
import biblioteca.biblioteca.entidades.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ListasRepository extends JpaRepository<listas,Long> {
    ArrayList<listas> findByUsuario(usuarios usuario);


    @Query("SELECT l FROM listas l WHERE l.id_lista = :idLista AND l.usuario = :usuario")
    Optional<listas> findByIdAndUsuario(@Param("idLista") Integer idLista, @Param("usuario") usuarios usuario);
}
