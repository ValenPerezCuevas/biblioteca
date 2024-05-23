package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.LibrosListas;
import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.listas;
import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.LibrosListasRepository;
import biblioteca.biblioteca.repositorios.ListasRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class controladorLista {

    private final ListasRepository listasRepository;
    private final LibrosListasRepository librosListasRepository;

    @Autowired
    public controladorLista(ListasRepository listasRepository, LibrosListasRepository librosListasRepository) {
        this.listasRepository = listasRepository;
        this.librosListasRepository = librosListasRepository;
    }

    @GetMapping
    public String obtenerTodasLasListas(Model model, HttpServletRequest request) {
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
    public String eliminarLista(@PathVariable("id") Long id, Model model) {
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

}
