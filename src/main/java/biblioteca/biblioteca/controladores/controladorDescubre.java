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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            @RequestParam(name = "tamanio", required = false, defaultValue = "12") int tamanio,
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
        model.addAttribute("libro", new libros());

        return "descubre";
    }


    @GetMapping("/descubre/obtenerInfoLibro/{id}")
    public ResponseEntity<?> obtenerInfoLibro(@PathVariable("id") Long id) {
        Optional<libros> libroOptional = libroRepository.findById(id);
        if (libroOptional.isPresent()) {
            libros libro = libroOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("titulo", libro.getTitulo());
            response.put("autor", libro.getAutor());
            response.put("genero", libro.getGenero());
            response.put("anoPublicacion", libro.getAnoPublicacion());
            response.put("editorial", libro.getEditorial());
            response.put("imagen", libro.getId_libros() + ".jpg");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
        }
    }

    @GetMapping("/descubre/filtrar")
    public String filtrar(
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "autor", required = false) String autor,
            @RequestParam(name = "genero", required = false) List<String> genero,
            @RequestParam(name = "editorial", required = false) String editorial,
            @RequestParam(name = "anoDesde", required = false) Integer anoDesde,
            @RequestParam(name = "anoHasta", required = false) Integer anoHasta,
            @RequestParam(name = "pagina", required = false, defaultValue = "0") int pagina,
            @RequestParam(name = "tamanio", required = false, defaultValue = "12") int tamanio,
            Model model
    ) {
        // Configurar la paginación
        Pageable pageable = PageRequest.of(pagina, tamanio);

        // Filtrar los libros según los criterios especificados
        Page<libros> paginaLibros = libroRepository.findByFiltros
                (titulo, autor, genero, editorial, anoDesde, anoHasta, pageable);

        model.addAttribute("libros", paginaLibros.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaLibros.getTotalPages());

        return "descubre";
    }




}
