package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.listas;
import biblioteca.biblioteca.entidades.usuarios;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface ListasRepository extends JpaRepository<listas,Long> {
    ArrayList<listas> findByUsuario(usuarios usuarioLogueado);

    @Query("SELECT l FROM listas l WHERE l.id_lista = :idLista AND l.usuario = :usuario")
    Optional<listas> findByIdAndUsuario(@Param("idLista") Integer idLista, @Param("usuario") usuarios usuario);

    @Transactional
    @Modifying
    @Query("DELETE FROM listas l WHERE l.id_lista = :idLista")
    void deleteByIdCustom(Integer idLista);
}
