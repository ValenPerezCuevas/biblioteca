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


    @GetMapping("/descubre/obtenerListas")
    public ResponseEntity<?> obtenerListas() {
        List<listas> todasLasListas = listasRepository.findAll();
        if (!todasLasListas.isEmpty()) {
            List<Map<String, Object>> listasResponse = todasLasListas.stream().map(lista -> {
                Map<String, Object> response = new HashMap<>();
                response.put("id_lista", lista.getId_lista());
                response.put("nombre_lista", lista.getNombre_lista());
                response.put("id_usuario", lista.getUsuario().getId_usuario());
                return response;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listasResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron listas disponibles");
        }
    }



//    @PostMapping("/descubre/anadirLibroALista")
//    public ResponseEntity<?> anadirLibroALista(@RequestParam Integer libroId, @RequestParam Integer listaId, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        usuarios usuario = (usuarios) session.getAttribute("usuarioLogueado"); // Obtén el usuario de la sesión
//
//        if (usuario == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
//        }
//
//        Optional<listas> listaOpt = listasRepository.findByIdAndUsuario(listaId, usuario);
//        if (!listaOpt.isPresent()) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La lista no pertenece al usuario");
//        }
//
//        Optional<libros> libroOpt = libroRepository.findById(libroId.longValue());  // Asegúrate de que el tipo de dato sea correcto
//        if (!libroOpt.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
//        }
//
//        // Verificar si el libro ya está en la lista
//        Optional<libros_listas> librosListasOpt = librosListasRepository.findByListaIdAndLibroId(listaId.longValue(), libroId.longValue());
//        if (librosListasOpt.isPresent()) {
//            return ResponseEntity.ok("El libro ya está en la lista");
//        }
//
//        // Añadir el libro a la lista
//        libros_listas nuevoLibroLista = new libros_listas();
//        nuevoLibroLista.setLibro(libroOpt.get());
//        nuevoLibroLista.setLista(listaOpt.get());
//        librosListasRepository.save(nuevoLibroLista);
//
//        return ResponseEntity.ok("Libro añadido a la lista con éxito");
//    }




}
