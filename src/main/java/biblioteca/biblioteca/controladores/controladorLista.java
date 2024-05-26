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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class controladorLista {

    private final ListasRepository listasRepository;
    private final LibroRepository libroRepository;
    private final Libros_listasRepository librosListasRepository;

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    @Autowired
    public controladorLista(ListasRepository listasRepository, LibroRepository libroRepository, Libros_listasRepository librosListasRepository) {

        this.listasRepository = listasRepository;
        this.libroRepository = libroRepository;
        this.librosListasRepository = librosListasRepository;
    }

    @GetMapping("/listas")
    public String obtenerTodasLasListas(Model model, HttpServletRequest request){
        model.addAttribute("listas", listasRepository.findAll());
        model.addAttribute("listasModificado", new listas());
//        List<libros_listas> librosListas = librosListasRepository.findAll();




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
        usuarios usuarioLogueado = (usuarios) request.getSession().getAttribute("usuario");
        listas lista = listasRepository.findById(id).orElse(null);

        if (lista == null) {
            redirectAttributes.addFlashAttribute("error", "Lista no encontrada.");
            return "redirect:/listas";
        }

        if (!lista.getCreado_por().equals(usuarioLogueado.getId_usuario())) {
            redirectAttributes.addFlashAttribute("error", "Esta lista no te pertenece, no puedes borrarla.");
            return "redirect:/listas";
        }

        listasRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Lista eliminada con éxito.");
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

//    @GetMapping("/listas/{id}/libros")
//    public String obtenerLibrosPorLista(@PathVariable("id") Long idLista, Model model) {
//        List<libros_listas> librosListas = librosListasRepository.findByIdLista(idLista);
//        model.addAttribute("librosListas", librosListas);
//        model.addAttribute("idLista", idLista);
//        return "listas";
//    }



}
