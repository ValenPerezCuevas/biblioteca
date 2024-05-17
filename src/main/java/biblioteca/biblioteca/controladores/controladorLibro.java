package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class controladorLibro {
    private final LibroRepository libroRepository;

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
        model.addAttribute("libro", new libros());
        model.addAttribute("libroModificado", new libros());

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

}
