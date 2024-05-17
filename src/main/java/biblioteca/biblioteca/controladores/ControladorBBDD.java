package biblioteca.biblioteca.controladores;


import biblioteca.biblioteca.repositorios.LibroRepository;
import biblioteca.biblioteca.repositorios.ListasRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import biblioteca.biblioteca.entidades.listas;
@Controller
public class ControladorBBDD {

    private final LibroRepository libroRepository;
    private final ListasRepository listasRepository;

    @Autowired
    public ControladorBBDD(LibroRepository libroRepository, UsuarioRepository usuarioRepository, ListasRepository listasRepository) {
        this.libroRepository = libroRepository;
        this.listasRepository = listasRepository;
    }

    @GetMapping("/listas")
    public String obtenerTodasLasListas(Model model){
        model.addAttribute("listas", listasRepository.findAll());
        return "listas";
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
}
