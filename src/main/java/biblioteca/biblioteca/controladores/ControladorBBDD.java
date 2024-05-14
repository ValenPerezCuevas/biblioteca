package biblioteca.biblioteca.controladores;


import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.ListasRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBBDD {

    private final LibroRepository libroRepository;
    private final ListasRepository listasRepository;

    @Autowired
    public ControladorBBDD(LibroRepository libroRepository, UsuarioRepository usuarioRepository, ListasRepository listasRepository) {
        this.libroRepository = libroRepository;
        this.listasRepository = listasRepository;
    }


    @GetMapping("/listas")
    public String obtenerTodasLasListas(Model model){
        model.addAttribute("listas", listasRepository.findAll());
        return "listas";
    }


}
