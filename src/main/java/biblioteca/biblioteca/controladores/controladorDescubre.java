package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.Libros_listasRepository;
import biblioteca.biblioteca.repositorios.ListasRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controladorDescubre {

    private final LibroRepository libroRepository;
    private final ListasRepository listasRepository;
    private final Libros_listasRepository librosListasRepository;

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    @Autowired
    public controladorDescubre(LibroRepository libroRepository, ListasRepository listasRepository, Libros_listasRepository librosListasRepository){
        this.libroRepository = libroRepository;
        this.listasRepository = listasRepository;
        this.librosListasRepository = librosListasRepository;
    }

    @GetMapping("/descubre")
    public String descubrir(
            @RequestParam(name = "pagina", required = false, defaultValue = "0") int pagina,
            @RequestParam(name = "tamanio", required = false, defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "titulo") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            HttpServletRequest request,
            Model model
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanio,
                Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Page<libros> paginaLibros = libroRepository.findAll(pageable);

        model.addAttribute("libros", paginaLibros.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaLibros.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("requestURI", request.getRequestURI());

        model.addAttribute("listas", listasRepository.findAll());
        model.addAttribute("librosListas", librosListasRepository.findAll());
        return "descubre";
    }
}
