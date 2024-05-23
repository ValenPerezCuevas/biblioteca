package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.LibrosListas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrosListasRepository extends JpaRepository<LibrosListas, Long> {

    List<LibrosListas> findByLista_Id_lista(Long id_lista);  // Corregir aquí

    void deleteByLista_Id_lista(Long id);
}
