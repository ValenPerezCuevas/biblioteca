package biblioteca.biblioteca.controladores;


import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBBDD {

    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ControladorBBDD(LibroRepository libroRepository, UsuarioRepository usuarioRepository) {
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/listado")
    public String obtenerTodosLosLibros(Model model){
       model.addAttribute("libros", libroRepository.findAll());
        return "listar";
    }

    @GetMapping("/usuarios")
    public String obtenerTodosLosUsuarios(Model model){
       model.addAttribute("usuarios",usuarioRepository.findAll());
        return "usuarios";
    }


}
