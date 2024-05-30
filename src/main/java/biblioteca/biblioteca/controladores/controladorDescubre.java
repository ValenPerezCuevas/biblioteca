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


    @GetMapping("/descubre/obtenerListas")
    public ResponseEntity<?> obtenerListas() {
        List<listas> todasLasListas = listasRepository.findAll();
        if (!todasLasListas.isEmpty()) {
            List<Map<String, Object>> listasResponse = todasLasListas.stream().map(lista -> {
                Map<String, Object> response = new HashMap<>();
                response.put("id_lista", lista.getId_lista());
                response.put("nombre_lista", lista.getNombre_lista());
                usuarios usuario = lista.getUsuario();
                if (usuario != null) {
                    response.put("id_usuario", usuario.getId_usuario());
                } else {
                    // Esto no debería suceder si todas las listas tienen un usuario
                    response.put("id_usuario", "Usuario no disponible");
                }
                return response;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listasResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron listas disponibles");
        }
    }


    @PostMapping("/descubre/anadirLibroALista")
    public ResponseEntity<?> anadirLibroALista(@RequestParam Long libroId, @RequestParam Long listaId) {
        Optional<libros> libroOptional = libroRepository.findById(libroId);
        Optional<listas> listaOptional = listasRepository.findById(listaId);

        if (libroOptional.isPresent() && listaOptional.isPresent()) {
            libros_listas nuevoLibroLista = new libros_listas();
            nuevoLibroLista.setLibro(libroOptional.get());
            nuevoLibroLista.setLista(listaOptional.get());
            librosListasRepository.save(nuevoLibroLista);
            return ResponseEntity.ok("Libro añadido a la lista con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro o Lista no encontrado");
        }
    }






    @GetMapping("/descubre/filtrar")
    public String filtrar(
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "autor", required = false) String autor,
            @RequestParam(name = "editorial", required = false) String editorial,
            @RequestParam(name = "genero", required = false) List<String> genero,
            @RequestParam(name = "anoDesde", required = false) Integer anoDesde,
            @RequestParam(name = "anoHasta", required = false) Integer anoHasta,
            @RequestParam(name = "pagina", required = false, defaultValue = "0") int pagina,
            @RequestParam(name = "tamanio", required = false, defaultValue = "12") int tamanio,
            HttpServletRequest request,
            Model model
    ) {
        // Configurar la paginación
        Pageable pageable = PageRequest.of(pagina, tamanio);

        // Filtrar los libros según los criterios especificados
        Page<libros> paginaLibros = libroRepository.findByFiltros
                (titulo, autor, editorial, genero, anoDesde, anoHasta, pageable);

        // Verificar si la lista de libros está vacía
        if (paginaLibros.getContent().isEmpty()) {
            model.addAttribute("noResultados", "No se encontraron libros con los filtros seleccionados.");
        }

        model.addAttribute("libros", paginaLibros.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaLibros.getTotalPages());
        model.addAttribute("requestURI", request.getRequestURI());

        return "descubre";
    }

}
