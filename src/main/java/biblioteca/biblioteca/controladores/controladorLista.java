package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.listas;
import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.ListasRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class controladorLista {

    private final ListasRepository listasRepository;
    private final LibroRepository libroRepository;

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    @Autowired
    public controladorLista(ListasRepository listasRepository, LibroRepository libroRepository) {

        this.listasRepository = listasRepository;
        this.libroRepository = libroRepository;
    }

    @GetMapping("/listas")
    public String obtenerTodasLasListas(Model model, HttpServletRequest request){
        model.addAttribute("listas", listasRepository.findAll());
        model.addAttribute("listasModificado", new listas());

        return "listas";
    }

    /**********************************************************************************
     * Método para agregar una lista
     * *********************************************************************************/
    @PostMapping("/agregarLista")
    public String guardarLista(@ModelAttribute("lista") listas lista) {
        listasRepository.save(lista);
        return "redirect:/listado";
    }

    /**********************************************************************************
     * Eliminar datos
     * * *********************************************************************************/
    @PostMapping("/eliminarLista/{id}")
    public String eliminarLista(@PathVariable("id") Long id) {
        listasRepository.deleteById(id);
        return "redirect:/listas";
    }
    /**********************************************************************************
     * Modificar datos
     **********************************************************************************/
    @GetMapping("/modificarLista/{id}")
    public String mostrarFormularioModificar(@PathVariable("id") Long id, Model model) {
        listas lista = listasRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lista no encontrada: " + id));
        model.addAttribute("lista", lista);
        return "formularioModificarLista";
    }

    @PostMapping("/modificarLista")
    public String modificarLista(@ModelAttribute("lista") listas listaModificada) {
        listasRepository.save(listaModificada);
        return "redirect:/listas";
    }
    /**********************************************************************************
     * Mostrar libros de listas
     **********************************************************************************/

//    @GetMapping("/controladorLista/{id}/libros")
//    @ResponseBody
//    public ResponseEntity<?> getLibrosPorLista(@PathVariable("id") Long id_lista) {
//        List<libros> librosDeLista = libroRepository.findByListaId_lista(id_lista);
//
//        if (librosDeLista.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron libros para esta lista");
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("nombre_lista", "Nombre de la Lista");  // Obtén el nombre real de la lista según tu lógica
//        response.put("libros", librosDeLista);
//
//        return ResponseEntity.ok(response);
//    }


}
