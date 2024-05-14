package biblioteca.biblioteca.controladores;


import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.RolesRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class controladorUsuario {


    private final UsuarioRepository usuarioRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public controladorUsuario(UsuarioRepository usuarioRepository, RolesRepository rolesRepository) {

        this.usuarioRepository = usuarioRepository;

        this.rolesRepository = rolesRepository;
    }

    /**********************************************************************************
     * Mostrar datos de los usuarios
     * *********************************************************************************/
    @GetMapping("/usuarios")
    public String obtenerTodosLosUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("nuevoUsuario", new usuarios());
        model.addAttribute("usuarioModificado", new usuarios());
        return "usuarios";
    }


    /**********************************************************************************
     * Mandar formulario de agregar usuario
     * * *********************************************************************************/

    @PostMapping("/agregarUsuario")
    public String guardarUsuario(@ModelAttribute("nuevoUsuario") usuarios usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    /**********************************************************************************
     * Eliminar datos
     * * *********************************************************************************/
    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    /**********************************************************************************
     * Modificar datos
     * * *********************************************************************************/
    @GetMapping("/modificarUsuario/{id}")
    @ResponseBody
    public usuarios mostrarFormularioDeModificar(@PathVariable("id") Integer id, Model model) {
        // como lo puso valen con id_rol
//        usuarios usuario = usuarioRepository.findById(Long.valueOf(id))
//                .orElseThrow(() -> new IllegalArgumentException("Usuario inválido con ID: " + id));

        // cambio arancha con nombre_rol
        usuarios usuario = usuarioRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Usuario inválido con ID: " + id));
        model.addAttribute("usuario", usuario);
        model.addAttribute("listaRoles", rolesRepository.findAll()); // Agregar la lista de roles al modelo

        return usuario;
    }

    @PostMapping("/modificarUsuario")
    public String modificarUsuario(@ModelAttribute("usuarioModificado") usuarios usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }


}
