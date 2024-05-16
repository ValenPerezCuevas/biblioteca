package biblioteca.biblioteca.controladores;


import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.roles;
import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.RolesRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public String obtenerTodosLosUsuarios(
            Model model,
            @RequestParam(name = "pagina", required = false, defaultValue = "0") int pagina,
            @RequestParam(name = "tamanio", required = false, defaultValue = "10") int tamanio
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<usuarios> paginaUsuarios = usuarioRepository.findAll(pageable);
        model.addAttribute("listaUsuarios", paginaUsuarios.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaUsuarios.getTotalPages());

//        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("nuevoUsuario", new usuarios());
        model.addAttribute("usuarioModificado", new usuarios());
        model.addAttribute("listaRoles", rolesRepository.findAll()); // Agregar lista de roles al modelo

        return "usuarios";
    }


    /**********************************************************************************
     * Mandar formulario de agregar usuario
     * * *********************************************************************************/

    @PostMapping("/agregarUsuario")
    public String guardarUsuario(@ModelAttribute("nuevoUsuario") usuarios usuario, @RequestParam("rol.id_rol") Long id) {
        roles rol = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);
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
    public ResponseEntity<usuarios> mostrarFormularioDeModificar(@PathVariable("id") Long id) {
        usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/modificarUsuario")
    public String modificarUsuario(@ModelAttribute("usuarioModificado") usuarios usuarioModificado) {
        usuarioRepository.save(usuarioModificado);
        return "redirect:/usuarios";
    }


}
