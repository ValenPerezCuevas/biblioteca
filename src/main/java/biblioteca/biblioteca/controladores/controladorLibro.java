package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.LibroRepository;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@Controller
public class controladorLibro {
    private final LibroRepository libroRepository;

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    @Autowired
    public controladorLibro(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    @GetMapping("/listar")
    public String libros() {
        return "libros";
    }

    @GetMapping("/listado")
    public String obtenerTodosLosLibros(
            @RequestParam(name = "pagina", required = false, defaultValue = "0") int pagina,
            @RequestParam(name = "tamanio", required = false, defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "titulo") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(name = "buscarTitulo", required = false) String buscarTitulo,
            @RequestParam(name = "buscarAutor", required = false) String buscarAutor,
            HttpServletRequest request,
            Model model
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanio,
                Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Page<libros> paginaLibros;

        // Código para filtro de búsqueda:
        if ((buscarTitulo != null && !buscarTitulo.isEmpty()) || (buscarAutor != null && !buscarAutor.isEmpty())) {
            if (buscarTitulo != null && !buscarTitulo.isEmpty()) {
                paginaLibros = libroRepository.findByTituloContainingIgnoreCase(buscarTitulo, pageable);
            } else {
                paginaLibros = libroRepository.findByAutorContainingIgnoreCase(buscarAutor, pageable);
            }
        } else {
            paginaLibros = libroRepository.findAll(pageable);
        }

        model.addAttribute("libros", paginaLibros.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaLibros.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("libro", new libros());
        model.addAttribute("libroModificado", new libros());
        model.addAttribute("requestURI", request.getRequestURI());

        return "libros";
    }


    /**********************************************************************************
     * Método para agregar un libro
     * *********************************************************************************/
    @PostMapping("/agregarLibro")
    public String guardarLibro(@ModelAttribute("libro") libros libro) {
        libroRepository.save(libro);
        return "redirect:/listado";
    }

    /**********************************************************************************
     * Método para eliminar un libro
     * *********************************************************************************/
    @PostMapping("/eliminarLibro/{id}")
    public String eliminarLibro(@PathVariable("id") Long id) {
        libroRepository.deleteById(id);
        return "redirect:/listado";
    }
    /**********************************************************************************
     * Modificar datos de libro
     * * *********************************************************************************/
    @GetMapping("/modificarLibro/{id}")
    @ResponseBody
    public ResponseEntity<libros> mostrarFormularioDeModificarLibro(@PathVariable("id") Long id) {
        libros libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return ResponseEntity.ok(libro);
    }

    @PostMapping("/modificarLibro")
    public String modificarLibro(@ModelAttribute("libroModificado") libros libroModificado) {
        libroRepository.save(libroModificado);
        return "redirect:/listado";
    }

    /**********************************************************************************
     * Mostrar portada
     * * *********************************************************************************/
    @GetMapping("/portada/{id_libros}")
    public ResponseEntity<?> getPortada(@PathVariable("id_libros") Integer id_libros) {
        Optional<libros> libro = libroRepository.findById(Long.valueOf(id_libros));
        if (libro.isPresent()) {
            String portadaUrl = "/imagenes/Imagenes_libros/" + id_libros + ".jpg";
            Map<String, String> response = new HashMap<>();
            response.put("url", portadaUrl);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
        }
    }
}
