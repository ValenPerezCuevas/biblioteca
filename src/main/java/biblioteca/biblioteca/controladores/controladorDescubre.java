package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.repositorios.LibroRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class controladorDescubre {

    private final LibroRepository libroRepository;

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    @Autowired
    public controladorDescubre(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }




    @GetMapping("/descubre")
    public String descubrir(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "descubre";
    }



}
