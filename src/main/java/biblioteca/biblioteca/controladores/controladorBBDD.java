package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controladorBBDD {
    private final LibroRepository libroRepository;
    @Autowired
    public controladorBBDD(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }



    @GetMapping("/listado")
    public String obtenerTodosLosLibros(Model model){
        model.addAttribute("libros", libroRepository.findAll());

        return "listar";


    }

}
