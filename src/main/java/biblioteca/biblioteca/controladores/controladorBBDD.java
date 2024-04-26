package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.Libro;
import biblioteca.biblioteca.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class controladorBBDD {
    private final LibroRepository libroRepository;
    @Autowired
    public controladorBBDD(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }



    @GetMapping("/bbdd")
    public List<Libro> obtenerTodosLosLibros(){

        return
                libroRepository.findAll();

    }

}
