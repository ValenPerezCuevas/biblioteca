package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.Libro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibroRepository {

    private final JdbcTemplate jdbcTemplate;

    public LibroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
