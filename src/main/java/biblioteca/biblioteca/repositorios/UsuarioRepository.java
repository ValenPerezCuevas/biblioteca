package biblioteca.biblioteca.repositorios;

import biblioteca.biblioteca.entidades.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Usuario> findAll(){
        String sql = "SELECT * FROM usuarios";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class));
    }
}
