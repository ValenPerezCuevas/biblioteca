package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.libros_listas;
import biblioteca.biblioteca.entidades.listas;
import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.Libros_listasRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class controladorLista {

    private final ListasRepository listasRepository;

    private final Libros_listasRepository librosListasRepository;

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    @Autowired
    public controladorLista(ListasRepository listasRepository,  Libros_listasRepository librosListasRepository) {

        this.listasRepository = listasRepository;
        this.librosListasRepository = librosListasRepository;
    }

    @GetMapping("/listas")
    public String obtenerTodasLasListas(Model model, HttpServletRequest request){
        model.addAttribute("listas", listasRepository.findAll());
        model.addAttribute("listasModificado", new listas());
        model.addAttribute("requestURI", request.getRequestURI());

        return "listas";
    }

    /**********************************************************************************
     * Método para agregar una lista
     * *********************************************************************************/
    @PostMapping("/agregarLista")
    public String guardarLista(@ModelAttribute("lista") listas lista, HttpServletRequest request) {
        usuarios usuarioLogueado = (usuarios) request.getSession().getAttribute("usuario");
        lista.setCreado_por(usuarioLogueado.getId_usuario());
        lista.setActualizado_por(usuarioLogueado.getId_usuario());
        lista.setUsuario(usuarioLogueado);

        listasRepository.save(lista);
        return "redirect:/listas";
    }

    /**********************************************************************************
     * Eliminar datos
     * * *********************************************************************************/
    @PostMapping("/eliminarLista/{id}")
    public String eliminarLista(@PathVariable("id") Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
//        usuarios usuarioLogueado = (usuarios) request.getSession().getAttribute("usuario");
//        listas lista = listasRepository.findById(id).orElse(null);
//
//        if (lista == null) {
//            redirectAttributes.addFlashAttribute("error", "Lista no encontrada.");
//            return "redirect:/listas";
//        }
//
//        if (!lista.getCreado_por().equals(usuarioLogueado.getId_usuario())) {
//            redirectAttributes.addFlashAttribute("error", "Esta lista no te pertenece, no puedes borrarla.");
//            return "redirect:/listas";
//        }

        try {
            listasRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Lista eliminada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar la lista");
        }

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
    @GetMapping("/listas/{id}/libros")
    public ResponseEntity<?> obtenerLibrosPorLista(
            @PathVariable("id") Long idLista,
            HttpServletRequest request) {
        Optional<listas> listaOptional = listasRepository.findById(idLista);
        if (!listaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista no encontrada.");
        }

        listas lista = listaOptional.get();
        List<libros_listas> librosListas = librosListasRepository.findByListaId(idLista);
        List<Map<String, Object>> librosInfo = new ArrayList<>();

        for (libros_listas libroLista : librosListas) {
            Map<String, Object> libroData = new HashMap<>();
            libroData.put("id", libroLista.getLibro().getId_libros());
            libroData.put("titulo", libroLista.getLibro().getTitulo());
            libroData.put("genero", libroLista.getLibro().getGenero());
            libroData.put("autor", libroLista.getLibro().getAutor());
            libroData.put("anoPublicacion", libroLista.getLibro().getAnoPublicacion());
            libroData.put("editorial", libroLista.getLibro().getEditorial());
            librosInfo.add(libroData);
        }

        usuarios usuarioLogueado = (usuarios) request.getSession().getAttribute("usuario");

        Map<String, Object> response = new HashMap<>();
        response.put("nombre_lista", lista.getNombre_lista());
        response.put("libros", librosInfo);

        response.put("creador_lista", lista.getCreado_por());
        response.put("usuario_actual", usuarioLogueado.getId_usuario());

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/listas/{idLista}/libros/{idLibro}")
    public ResponseEntity<?> eliminarLibroDeLista(
            @PathVariable("idLista") Long idLista,
            @PathVariable("idLibro") Long idLibro,
            HttpServletRequest request) {
        Optional<libros_listas> libroLista = librosListasRepository.findByListaIdAndLibroId(idLista, idLibro);
        if (libroLista.isPresent()) {
            librosListasRepository.delete(libroLista.get());
            return ResponseEntity.ok().body("Libro eliminado correctamente de la lista.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado en la lista especificada.");
        }
    }
}


