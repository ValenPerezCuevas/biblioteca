package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<roles, Long> {




}