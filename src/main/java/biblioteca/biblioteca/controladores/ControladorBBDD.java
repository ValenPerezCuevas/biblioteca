package biblioteca.biblioteca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBBDD {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ControladorBBDD(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/listado")
    public String obtenerTodosLosLibros(Model model){
        String sql = "SELECT * FROM libros";
        model.addAttribute("libros", jdbcTemplate.queryForList(sql));
        return "listar";
    }
}
