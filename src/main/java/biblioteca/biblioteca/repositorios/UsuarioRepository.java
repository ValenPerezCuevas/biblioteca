package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<usuarios,Long> {

}
