package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.libros_listas;
import biblioteca.biblioteca.entidades.listas;
import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.Libros_listasRepository;
import biblioteca.biblioteca.repositorios.ListasRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
   /* @Autowired
    private HttpSession session;*/

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

    @GetMapping("/descubre/obtenerListas")
    public ResponseEntity<?> obtenerListas() {
        List<listas> todasLasListas = listasRepository.findAll();
        if (!todasLasListas.isEmpty()) {
            List<Map<String, Object>> listasResponse = todasLasListas.stream().map(lista -> {
                Map<String, Object> response = new HashMap<>();
                response.put("id_lista", lista.getId_lista());
                response.put("nombre_lista", lista.getNombre_lista());
                return response;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listasResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron listas disponibles");
        }
    }



}
