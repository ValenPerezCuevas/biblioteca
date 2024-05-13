package biblioteca.biblioteca.controladores;


import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class controladorUsuario {


    private final UsuarioRepository usuarioRepository;


    @Autowired
    public controladorUsuario(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;

    }

    /*
    Mostrar todos los usuarios
     */
    @GetMapping("/usuarios")
    public String obtenerTodosLosUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("nuevoUsuario", new usuarios());

        return "usuarios";
    }



    /*
Mandar formulario de agregar usuario
 */

    @PostMapping("/agregarUsuario")
    public String guardarUsuario(@ModelAttribute("nuevoUsuario") usuarios usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    /*
Eliminar datos
 */
    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    /*
Modificar datos
*/



}
