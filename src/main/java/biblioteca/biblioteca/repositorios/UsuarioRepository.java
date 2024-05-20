package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<usuarios, Long> {
    usuarios save(usuarios usuario);
    Optional<usuarios> findByNombre(String nombre);

}
